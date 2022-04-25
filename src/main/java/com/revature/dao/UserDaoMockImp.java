package com.revature.dao;

import com.revature.models.User;

import java.util.List;

public class UserDaoMockImp implements UserDao {
    @Override
    public User create(User model) {
        return null;
    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(User model) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

  // @Override
  // public void saveUser(User u) {

  // }

    //@Override
    //public List<User> getAllUsers() {
    //    return null;
    //}

    //@Override
    //public User getUserByUserName(String username) {
    //    return null;
    //}

    //@Override
    //public User getUserByFirstLast(String first, String last) {
      //  return null;
    //}

    //@Override
    //public User read(String first, String last) {
    //    return null;
    //}

/*
    //We have our connection to the database
    private MockUserDB db = MockUserDB.getInstance();

    @Override
    public void saveUser(User u) {
        //To store data in a hashmap, we use put(Key, Value)
        db.getUdb().put(u.getUsername(), u);
    }

    public List<User> getAllUsers(){
        return new ArrayList<User>(db.getUdb().values());
    }

    public User getUserByUserName(String username){
        return db.getUdb().get(username);
    }

    public void updateUser(User u){
        db.getUdb().put(u.getUsername(), u);
    }
*/
}
