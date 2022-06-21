package com.example.sample;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  @Autowired
  private UserService service;  
  
	@GetMapping("/")
	public String loginCheck() {
		
		return "test1";
	}
	
	@GetMapping("/loginCheck")
	@ResponseBody//비동기 통신에서 반드시써야함
//@GetMapping(value = "/findID", produces ="application/json;charset=utf-8")
	                              //여기있는 produce는 return 값을 다른걸로 받고 싶을때 씀
	public String loginCheck(@RequestParam Map<String,String> map  ) {
	  
	  //Map<String,String> map = new HashMap<String,String>();
	  log.info("map:"+map);
	  int cnt = service.loginCheck(map);
	  String name = null;
	  if(cnt==1) {
	    name = service.getName(map.get("id"));
	  }
	  return name +"님 login ok Status Success";
	  
	}
}
