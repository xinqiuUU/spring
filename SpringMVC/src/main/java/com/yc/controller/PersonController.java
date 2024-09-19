package com.yc.controller;

import com.yc.model.JsonModel;
import com.yc.model.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/test")
public class PersonController {

    @RequestMapping(value = "/person", produces = "text/html;charset=UTF-8")
    public String person( int id, String name , int age){
        return "姓名:"+name+"年龄:"+(++age);
    }
    @RequestMapping(value = "/person1", produces = "text/html;charset=UTF-8")
    public Person person1( Person p){
        return p;
    }
    //能否将返回的对象自动转为json字符串
    @PostMapping("/person2")
    public JsonModel person2( Person p){
        JsonModel jm = new JsonModel();
        jm.setCode(1);
        jm.setObj( p );
        return jm;
    }
    @PostMapping("/person3")
    public Person person3(Person p , HttpServletRequest request , HttpSession session){

        if ( session.getAttribute("p") != null){
            p = (Person) session.getAttribute("p");
            p.setAge(p.getAge()+1);
        }else {
            session.setAttribute("p", p);
        }
        return p;
    }

    @PostMapping( value = "/person4" ,
            consumes = "application/json;charset=UTF-8",//请求的内容类型
            produces = "application/json;charset=UTF-8")//响应的内容类型
    public Person person4( @RequestBody Person p ){

        p.setAge(p.getAge()+15);

        return p;
    }



}
