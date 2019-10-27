package com.voting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: ll
 * @description: 路由controller
 * @create: 2019-10-23 16:42
 **/
@Controller
public class routingController {
    
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("toRegist")
    public String toRegist(){
        return "regist";
    }
    @RequestMapping("toVoteList")
    public String toVoteList(){
        return "voteList";
    }
    @RequestMapping("toAdd")
    public String toAdd(){
        
        return "add";
    }
    @RequestMapping("toVote")
    public String toVote(){
        return "vote";
    }
    @RequestMapping("toVoteView")
    public String toVoteView(){
        return "voteView";
    }
    @RequestMapping("toUpdate")
    public String toUpdate(){
        return "update";
    }
}
