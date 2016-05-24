package gofive.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("myajax.do")
public class ControllerTest {
	@RequestMapping(params="method=test1",method=RequestMethod.GET)
	  public @ResponseBody List<User> test1(String uname) throws Exception{ 
	   
	    List<User> list = new ArrayList<User>();
	    list.add(new User("潘玮柏","123"));
	    list.add(new User("蔡依林","456"));
	   
        return list;  
	    
	    //return list;
	  }

}
