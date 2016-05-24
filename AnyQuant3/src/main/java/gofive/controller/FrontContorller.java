package gofive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(".html")
public class FrontContorller {
	@RequestMapping("/html/index.html")
    public String index(){        
        return "index";
    }
}
