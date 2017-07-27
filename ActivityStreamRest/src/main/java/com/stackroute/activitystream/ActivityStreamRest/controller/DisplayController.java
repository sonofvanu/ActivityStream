package com.stackroute.activitystream.ActivityStreamRest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayController {
	
	@RequestMapping("/")
	public String gotoindex()
	{
		return "index";
	}

}
