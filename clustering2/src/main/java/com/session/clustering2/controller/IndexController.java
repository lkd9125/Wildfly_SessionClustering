package com.session.clustering2.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
    
    private ModelAndView mv;

    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public ModelAndView root(){
        log.info("session -> {}", session.getAttribute("session"));


        mv = new ModelAndView("login");

        return mv;
    }
    
    @PostMapping("/login")
    public ModelAndView loginProc(String id, String pass){

        if(id.equals("lkd9125") && pass.equals("1234")){
            log.info("로긩 성공");
            session.setAttribute("session", "loginCheck");
        }

        log.info("session -> {}", session.getAttribute("session"));

        mv = new ModelAndView("redirect:/");

        return mv;
    }
    
    @GetMapping("/logout")
    public ModelAndView logout(){
        log.info("IndexController - logout()");

        session.removeAttribute("session");

        mv = new ModelAndView("redirect:/");

        return mv;
    }

    @GetMapping("/checksession")
    @ResponseBody
    public HashMap<String, Object> testSession(){        
        HashMap<String, Object> result = new HashMap<>();

        boolean sessionCheck = (String)session.getAttribute("session") != null ? true : false;

        if(sessionCheck) {
            result.put("result","세션있음"); 
            log.info("세션 있음ㅋ");
        }
        else {
            result.put("result","세션없음"); 
            log.info("세션 없음;");
        }

        return result;
    }
    
}
