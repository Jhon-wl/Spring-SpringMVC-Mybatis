package com.controller;

import com.alibaba.fastjson.JSON;
import com.entity.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * Create by mr.wl on 2017/8/8
 * 总结:
 *  转发:页面URL不会发生改变,就像a请求b,b向c请求,然后b再把数据返回给a
 *  重定向:页面url会发生改变,a请求b,b说c可以完成,然后a再请求c完成操作
 *  get请求数据   localhost:8080/user/test/{id}/{name}   用@PathVariable("") 注解  标记形参,如果注解里面注明了变量,
 *      则形参可以不同于原变量名,反之必须相同
 *  post请求数据   form表单提交,可以用HttpServletRequest  的getParameter("");方法 获取参数;
 *      也可以使用与from表单数据类型匹配的vo类来接收数据   User
 *
 */
@Controller
@RequestMapping("user")
public class UserController {


    @RequestMapping("/test")
    public String testUser(ModelMap map,User user){
        //modelAndView 返回数据
//        ModelAndView model = new ModelAndView();
//        model.setViewName("demo");
//        User user = new User();
//        user.setId(111);
//        user.setName("wanghaha");
//        model.addObject("user",user);
//
//        return model;

        //post请求,用request接收数据   HttpServletRequest request
//        String id = request.getParameter("id");
//        String name = request.getParameter("name");
//        System.out.println(id+"  "+name);
//        User user = new User();
//        user.setName("aa");
//        user.setId(12);
//        map.addAttribute("user",user);
//        return "redirect:/user/demo";


//        使用User接收数据
        map.addAttribute("user",user);
        return "redirect:/user/demo";
    }

//    返回JSON数据格式
    @RequestMapping("/do") // method = RequestMethod.POST  可以限制使用post或者get请求
    public String doUser(@RequestBody User user){
//        User user = new User();
        user.setId(112);
       user.setName("enen");


        return "user";
    }



    @RequestMapping("/demo")
    public String demoUser(){

        return "demo";
    }
//文件上传
    @RequestMapping("/upload")
    public String upload(MultipartFile file , HttpServletRequest request) throws IOException {
        String name = file.getOriginalFilename();
        File uploadf = new File("/"+name);
        file.transferTo(uploadf);

        return "demo";
    }
//文件下载
        @RequestMapping("/download")
    public ResponseEntity<byte[]> download() throws IOException {
        //设置下载地址
        String path = "D:/1.png";
        File file = new File(path);
        HttpHeaders headers = new HttpHeaders();
        //添加http请求附件
        headers.setContentDispositionFormData("attachment",path);
        //设置文件的格式
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}
