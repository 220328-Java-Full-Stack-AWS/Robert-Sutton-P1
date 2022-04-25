package com.revature.models;

import com.revature.dao.UserDao;

import java.util.*;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 *
 *
 * <!-- RJS : April     08, 2022 -- moving on from the lame social media app, let's consider ERS application ->
 *
 *     <li>UserName</li>
 *     <li>Role</li> <!-- Guest, Registered User, Admin/Finance Manager ->
 *
 *
 *
 * </ul>
 *
 *
 */
//public class User extends AbstractUser {
public class User extends Model implements Comparable<User>{

    public static List<User> getAllUsers;

    /*
        - String Email
        - String First
        - String Last
        - String Username
        - String Password
        - List Posts
        - Set Followers
        - Set Following
     */

    private int id;
    private String email;
    private String first;
    private String last;
    private String username;
    private String password;
    private int role;
    private List<Post> posts;
    private Set<User> followers;
    private Set<User> following;

    public User() {

        posts = new ArrayList<>();
        followers = new HashSet<>();
        following = new HashSet<>();

    }

    public static User User(String username) {
        return UserDao.read(username);
    }

    public static User User(String first, String last) {return UserDao.read(first, last); }

    public User(String first, String last, String username, String email, String password, List<Post> posts, Set<User> followers, Set<User> following) {
        this.email = email;
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        this.role = role;
        this.posts = posts;
        this.followers = followers;
        this.following = following;
    }

    public User(String first, String last, String username, String email, String password){
        this.email = email;
        this.first = first;
        this.last = last;
        this.username = username;
        this.password = password;
        if(role == 0){
            role = 1; // Default is "User".
        }
        this.role = role;
        //this.posts = new ArrayList<>();
        //this.followers = new HashSet<>();
        //this.following = new HashSet<>();
    }

    public User(int id) {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {return role; }

    public void setRole(int role) {this.role = role; }

    public List<User> getAllUsers(){
        return UserDao.getAllUsers();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public int getId() {return id; }

    public void setId(int id) {this.id = id; }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                //", posts=" + posts.size() +
                //", followers=" + followers.size() +
                //", following=" + following.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(first, user.first) && Objects.equals(last, user.last) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(posts, user.posts) && Objects.equals(followers, user.followers) && Objects.equals(following, user.following);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, first, last, username, password);
    }

    @Override
    public int compareTo(User o) {
        if(this.followers.size() > o.followers.size()){
            return -1;
        } else if(this.followers.size() < o.followers.size()){
            return 1;
        }
        else {
            return 0;
        }
    }
}
