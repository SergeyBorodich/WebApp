package com.intexsoft.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.intexsoft.model.User;
import com.intexsoft.services.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * 
 */
@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest
{
	@Mock
	User user1;
	@Mock
	User user2;
	@Mock
	User user3;
	@Mock
	UserService userService;
	private List<User> users = new ArrayList<User>();

	@Before
	public void initData()
	{
		user1 = new User(1, "User1", "Address1", "user1@abc.com");
		user2 = new User(2, "User2", "Address2", "user2@abc.com");
		user3 = new User(3, "User3", "Address3", "user3@abc.com");
		users.add(user1);
		users.add(user2);
	}

	@Test
	public final void test2FindById() throws Exception
	{
		when(userService.findById(user1.id)).thenReturn(user1);
		assertEquals(userService.findById(user1.id), user1);
	}

	@Test
	public final void test1FindAllUsers() throws Exception
	{
		when(userService.findAllUsers()).thenReturn(users);
		assertEquals(userService.findAllUsers(), users);
	}

	@Test
	public final void test3FindByName() throws Exception
	{
		when(userService.findByName(user1.userName)).thenReturn(user1);
		assertEquals(userService.findByName(user1.userName), user1);
	}

	@Test
	public final void test4SaveUser() throws NullPointerException
	{
		doThrow(new NullPointerException()).when(userService).saveUser(user3);
	}

	@Test
	public final void test5UpdateUser() throws NullPointerException
	{
		user1.userName = "updated_user";
		doThrow(new NullPointerException()).when(userService).updateUser(user1);
	}

	@Test
	public final void test6DeleteUserById() throws IllegalStateException
	{
		user1.userName = "updated_user";
		doThrow(new IllegalStateException()).when(userService).deleteUserById(user1.id);
	}

	@Test
	public final void test7IsUserExists() throws Exception
	{
		when(userService.isUserExist(user2)).thenReturn(true);
		assertTrue(userService.isUserExist(user2));
	}

	@Test
	public final void test4DeleteAllUsers() throws UnsupportedOperationException
	{
		doThrow(new UnsupportedOperationException()).when(userService).deleteAllUsers();
	}
}
