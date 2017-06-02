package com.intexsoft.model;

/**
 * 
 */
public class User
{
	public long id;
	public String userName;
	public String address;
	public String email;

	public User()
	{
		id = 0;
	}

	public User(long id, String username, String address, String email)
	{
		this.id = id;
		this.userName = username;
		this.address = address;
		this.email = email;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "User [id=" + id + ", username=" + userName + ", address=" + address + ", email=" + email + "]";
	}
}
