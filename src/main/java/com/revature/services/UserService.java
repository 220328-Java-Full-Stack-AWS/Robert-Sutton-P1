package com.revature.services;

import java.util.*;

import com.revature.dao.UserDao;
import com.revature.exceptions.UsernameOrPasswordIncorrectException;
import com.revature.models.User;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */


	private UserDao ud;

	//Dependency injection, it is considered good practice because we can swapout any type of UserDao implementation we want
	public UserService(UserDao ud){
		this.ud = ud;
	}

	public User login(String username, String password){
		//Search the db for a user with the specified username, then check to see if the password matches
		if(ud.getUserByUserName(username) != null && ud.getUserByUserName(username).getPassword().equals(password)){
			return ud.getUserByUserName(username);
		} else {
			throw new UsernameOrPasswordIncorrectException();
		}
	}

	public User register(String first, String last, String email, String password){
		Random rand = new Random();
		String username = first + last + (int)(1000 + (Math.random() * 10000));
		User u = new User(first, last, username, email, password);
		//From the service, we would make our database call to actually store this user away
		ud.saveUser(u);
		return u;
	}

	public void followUser(User current, String username){
		//When we follow a user
		//Put the user in our following set
		//Put our user object in their followers
		User u = ud.getUserByUserName(username);
		Set following = current.getFollowing();
		following.add(u);
		current.setFollowing(following);
		Set followers = u.getFollowers();
		followers.add(current);
		u.setFollowers(followers);
		ud.saveUser(u);
		ud.saveUser(current);
	}

	public List<User> topRankedUsers(){
		//The Collections Utility class has methods to do things like sorting collections
		List<User> uList = ud.getAllUsers();
		//The Collections.sort(list) is going to sort our list of users based off of the compareTo method we created early
		Collections.sort(uList);
		return uList;
	}

	public Optional<User> getByUsername(String username) {
		return Optional.empty();
	}
}
