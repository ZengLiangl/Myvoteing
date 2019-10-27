package com.voting.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.voting.common.BaseEntity;
import com.voting.common.Constant;
import com.voting.pojo.Item;
import com.voting.pojo.Options;
import com.voting.pojo.Subject;
import com.voting.pojo.Users;
import com.voting.service.ItemService;
import com.voting.service.OptionsService;
import com.voting.service.SubjectService;
import org.apache.catalina.startup.RealmRuleSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ll
 * @since 2019-10-23
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private ItemService itemService;

    @RequestMapping("queryAllSubjectInfo")
    public PageInfo<Map<String, Object>> queryAllSubjectInfo(Subject subject, Integer curr, Integer page, HttpSession session) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        System.out.println("curr = " + curr);
        System.out.println("page = " + page);
        PageHelper.startPage(curr != null ? curr : 1, page != null ? page : 5);
        List<Map<String, Object>> maps = subjectService.queryAllSubjectInfo(subject);
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(maps);
        session.setAttribute("info", mapPageInfo);
        return mapPageInfo;
    }

    @RequestMapping("insertSubject")
    public JSONObject insertSubject(Subject subject, String[] ocontent) {
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Subject> tWrapper = new QueryWrapper<>();
        tWrapper.eq("title",subject.getTitle());
        List<Subject> list = subjectService.list(tWrapper);
        if (list != null&&list.size()>0) {
            jsonObject.put("result", Constant.TITLE_MISTAKE);
        } else {
            subject.setCreateTime(new Date());
            boolean save = subjectService.save(subject);
            boolean save1 = false;
            if (ocontent != null && ocontent.length != 0) {
                for (String str : ocontent) {
                    Options options = new Options();
                    options.setOsid(subject.getSid());
                    options.setOcontent(str);
                    save1 = optionsService.save(options);
                }
            }
            if (save && save1) {
                jsonObject.put("result", Constant.SUCCESS);
            } else {
                jsonObject.put("result", Constant.FAILURE);
            }
        }
        return jsonObject;
    }

    @RequestMapping("isHaveItem")
    public JSONObject isHaveItem(HttpSession session, Integer sid) {
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<Item> tWrapper = new QueryWrapper<>();
        Users users = (Users) session.getAttribute("user");
        tWrapper.eq("uid", users.getUserId());
        tWrapper.eq("sid", sid);
        List<Item> list = itemService.list(tWrapper);
        if (list != null && list.size() != 0) {
            jsonObject.put("result", Constant.SUCCESS);
        } else {
            jsonObject.put("result", Constant.FAILURE);
        }
        return jsonObject;
    }

    @RequestMapping("queryVote")
    public JSONObject queryVote(Subject subject) {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> map = subjectService.queryAllSubjectInfo(subject).get(0);
        QueryWrapper<Options> tWrapper = new QueryWrapper<>();
        tWrapper.eq("osid", subject.getSid());
        List<Options> options = optionsService.list(tWrapper);
        jsonObject.put("map", map);
        jsonObject.put("options", options);
        return jsonObject;
    }

    @RequestMapping("saveItem")
    public JSONObject saveItem(Integer sid, Integer[] oid, HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        if (oid != null && oid.length > 0) {
            for (Integer o : oid) {
                Users users = (Users) session.getAttribute("user");
                Item item = new Item(null, users.getUserId(), sid, o);
                if (itemService.save(item)) {
                    jsonObject.put("result", Constant.SUCCESS);
                } else {
                    jsonObject.put("result", Constant.FAILURE);
                }
            }
        }
        return jsonObject;
    }

    @RequestMapping("queryVoteView")
    public JSONObject queryVoteView(Subject subject) {
        JSONObject jsonObject = new JSONObject();
        Map<String, Object> map = subjectService.queryAllSubjectInfo(subject).get(0);
        List<Map<String, Object>> items = itemService.queryItemStatistical(subject.getSid());
        QueryWrapper<Item> tWrapper = new QueryWrapper<>();
        tWrapper.eq("sid", subject.getSid());
        Double count = itemService.count(tWrapper) * 1.0;
        for (Map<String, Object> item : items) {
            Integer itemCount = Integer.valueOf(item.get("itemCount").toString());
            Double percentage = (itemCount / count) * 1.0;
            item.put("percentage", percentage * 100.0);
        }
        jsonObject.put("map", map);
        jsonObject.put("items", items);
        System.out.println("items = " + items);
        return jsonObject;
    }

    @RequestMapping("loadItemPie")
    public List<BaseEntity> loadItemPie(Integer sid) {
        List<Map<String, Object>> items = itemService.queryItemStatistical(sid);
        List<BaseEntity> list = new ArrayList<>();
        for (Map<String, Object> item : items) {
            list.add(new BaseEntity(item.get("ocontent").toString(), Double.parseDouble(item.get("itemCount").toString())));
        }
        return list;
    }


    @RequestMapping("updateSubject")
    public JSONObject updateSubject(Subject subject, String[] ocontent, Integer[] oid) {
        JSONObject jsonObject = new JSONObject();
        boolean save = subjectService.updateById(subject);
        boolean save1 = false;
        if (ocontent != null && ocontent.length != 0) {
            for (int i = 0; i < oid.length; i++) {
                Options options = new Options();
                options.setOcontent(ocontent[i]);
                options.setOid(oid[i]);
                save1 = optionsService.updateById(options);
            }
            if (ocontent.length > oid.length) {
                for (int i = ocontent.length - oid.length; i < ocontent.length; i++) {
                    Options options = new Options();
                    options.setOsid(subject.getSid());
                    options.setOcontent(ocontent[i]);
                    save1 = optionsService.save(options);
                }
            }
        }
        if (save && save1) {
            jsonObject.put("result", Constant.SUCCESS);
        } else {
            jsonObject.put("result", Constant.FAILURE);
        }
        return jsonObject;
    }
}
