package com.spring.temp.dome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.spring.temp.domain.User;


@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	//localhost:8080/id?id=111
	@RequestMapping(value = "/{id}")
	public String show(ModelMap model,Long id){
		userService.create(new User("ttt",22));
		User findUser = userService.findById(id);
		model.addAttribute("user", findUser);
		return "user:"+JSON.toJSONString(model.get("user"));
	}
	
	@RequestMapping("/find")
	public User find(){
		return userService.findById(1L);
	}
	
	@RequestMapping("/update")
	public String update(){
		User user = new User("ttt",1111);
		user.setId(1L);
		if(userService.update(user)!=null)
			return "OK";
		return "error";
	}
	
	@RequestMapping("/addconfig")
	public String addconfig(){
		User user = new User("config",45);
		if(userService.createConfig(user)!=null)
			return "OK";
		return "error";
	}
}





