package com.intexsoft.tests;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 */
public class WebTest
{
	@Autowired
	protected WebApplicationContext webApplicationContext;
	protected MockMvc mockMvc;
	protected ObjectMapper mapper;

	@Before
	public void setUp() throws Exception
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mapper = new ObjectMapper();
	}
}
