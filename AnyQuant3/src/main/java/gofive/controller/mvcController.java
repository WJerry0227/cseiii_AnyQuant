package gofive.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/html")
public class mvcController {
	
	@RequestMapping("/test.do")
    public String test(){        
        return "SearchingResult";
    }
 
    @RequestMapping("/hello.do")
    public String hello(){        
        return "ajax";
    }
    @RequestMapping("/index.do")
    public String index(){        
        return "index";
    }
    @RequestMapping("/person")
    public String person(String name,String age){
    	System.out.println(name+" "+age);
    	return "hello";
    }
    @RequestMapping("/getPerson")
    public void getPerson(String name,PrintWriter pw){
        pw.write("hello,"+name);        
    }
    @RequestMapping("/name")
    public String sayHello(){
        return "name";
    }
//    @RequestMapping("/addUserJson")  
//    public String addUserJson(User user,HttpServletRequest request,HttpServletResponse response){  
//    	String result = "{\"userName\":\""+ user.getUserName() +"\",\"age\":\" "+ user.getAge()+" \"}"; 
//    	System.out.println(result);
//        PrintWriter out = null;  
//        response.setContentType("application/json");  
//          
//        try {  
//            out = response.getWriter();  
//            out.write(result);  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//        return "";  
    }  
