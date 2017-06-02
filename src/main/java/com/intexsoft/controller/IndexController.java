package com.intexsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 */
@Controller
@RequestMapping("/index")
public class IndexController
{
	private static final String INDEX_VIEW = "UserManagement"; 
	
	@RequestMapping(method = RequestMethod.GET)
	public String getIndexPage()
	{
		return INDEX_VIEW;
	}
}
