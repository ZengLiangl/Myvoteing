package com.voting.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.voting.common.Constant;
import com.voting.pojo.Users;
import com.voting.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ll
 * @description: 登录controller
 * @create: 2019-10-23 17:31
 **/
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("login")
    public JSONObject login(String loginName, String password, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("loginName", loginName);
        Users user = usersService.getOne(wrapper);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                ServletContext application = session.getServletContext();
                @SuppressWarnings("unchecked")
                Map<String, Object> loginMap = (Map<String, Object>) application.getAttribute("loginMap");
                if (loginMap == null) {
                    loginMap = new HashMap<String, Object>();
                }
                boolean ck = true;
                for (String key : loginMap.keySet()) {
                    if (user.getUserId().toString().equals(key)) {
                        if (session.getId().equals(loginMap.get(key))) {
                            //"在同一地点重复登录"
                            jsonObject.put("result", Constant.REPETITION_LOGIN);
                            ck = false;
                            break;
                        } else {
                            //"异地已登录，请先退出登录"
                            jsonObject.put("result", Constant.DISTANCE_LOGIN);
                            ck = false;
                            break;
                        }
                    }
                }
                if (ck) {
                    loginMap.put(user.getUserId().toString(), session.getId());
                    application.setAttribute("loginMap", loginMap);
                    // 将用户保存在session当中
                    session.setAttribute("user", user);
                    // session 销毁时间
                    session.setMaxInactiveInterval(30 * 60);
                    jsonObject.put("result", Constant.SUCCESS);
                }
            } else {
                //密码错误
                jsonObject.put("result", Constant.PWD_MISTAKE);
            }
        } else {
            jsonObject.put("result", Constant.USER_MISTAKE);
        }
        return jsonObject;
    }
    @RequestMapping("registUser")
    public JSONObject registUser(Users user){
        user.setCreateTime(new Date());
        user.setIsAdmin(Constant.USER_TYPE_NORMAL);
        System.out.println("user = " + user);
        JSONObject jsonObject=new JSONObject();
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("loginName",user.getLoginName());
        Users users = usersService.getOne(wrapper);
        if (users!=null){
            jsonObject.put("result", Constant.USER_MISTAKE);
        }else {
            if (usersService.save(user)){
                jsonObject.put("result", Constant.SUCCESS);
            }else {
                jsonObject.put("result", Constant.FAILURE);
            }
        }
        return  jsonObject;
    }

    @RequestMapping("loginOut")
    public JSONObject loginOut(HttpServletRequest request, HttpServletResponse response,String userId) {
        JSONObject jsonObject=new JSONObject();
        HttpSession session = request.getSession();
        if (session != null) {
            try {
                session.removeAttribute("user");
                if (session.getAttribute(userId) != null) {
                    session.removeAttribute(userId);
                }
                ServletContext application = session.getServletContext();
                @SuppressWarnings("unchecked")
                Map<String, Object> loginMap = (Map<String, Object>) application.getAttribute("loginMap");
                if (loginMap!=null && loginMap.size()>0){
                    loginMap.remove(userId);
                    application.setAttribute("loginMap", loginMap);
                }else {
                    loginMap=new HashMap<>();
                }
                jsonObject.put("result", Constant.SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("result", Constant.FAILURE);
            }
        }
        return jsonObject;
    }
}
