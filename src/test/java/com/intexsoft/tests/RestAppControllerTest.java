package com.intexsoft.tests;

import java.nio.charset.Charset;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import com.intexsoft.configuration.AppConfiguration;
import com.intexsoft.model.User;
import com.intexsoft.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RestAppControllerTest extends WebTest
{
	private static final String URL = "/user";
	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
		MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Autowired
	UserService userService;

	@Test
	public final void test1FindAll() throws Exception
	{
		ResultActions perform = mockMvc.perform(get(getUrl()));
		perform.andExpect(status().isOk());
	}

	@Test
	public final void test2GetUser() throws Exception
	{
		ResultActions perform = mockMvc.perform(get(getUrl()).param("id", "1"));
		perform.andExpect(status().isOk());
	}

	@Test
	public final void test3CreateUser() throws Exception
	{
		User newUser = new User(4, "User4", "Address4", "user4@abc.com");
		String json = mapper.writeValueAsString(newUser);
		ResultActions perform = mockMvc.perform(post(getUrl()).contentType(APPLICATION_JSON_UTF8).content(json));
		perform.andExpect(status().isCreated());
	}

	@Test
	public final void test4UpdateUser() throws Exception
	{
		User updateUser = userService.findById(2);
		updateUser.userName = "User2_update";
		updateUser.address = "Address2_update";
		String json = mapper.writeValueAsString(updateUser);
		ResultActions perform = mockMvc.perform(put(getUrl() + "/2").contentType(APPLICATION_JSON_UTF8).content(json));
		perform.andExpect(status().isOk());
	}

	@Test
	public final void test5DeleteUser() throws Exception
	{
		ResultActions perform = mockMvc.perform(delete(getUrl() + "/3"));
		perform.andExpect(status().isNoContent());
	}

	@Test
	public final void test6DleteAllUsers() throws Exception
	{
		ResultActions perform = mockMvc.perform(delete(getUrl()));
		perform.andExpect(status().isNoContent());
	}

	private String getUrl()
	{
		return URL;
	}
}
