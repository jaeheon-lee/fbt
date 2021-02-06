package com.biomans.fbt.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin(origins= {"*"}, maxAge=6000)
public class RootController implements ErrorController {

	@GetMapping("/error")
	public String redirectRoot() {
		return "index.html";
	}
	
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
