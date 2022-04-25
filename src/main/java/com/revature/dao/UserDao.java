package com.revature.dao;

import com.revature.ConnectionManager;
import com.revature.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface UserDao extends CRUDInterface<User> {

    @Override
    public default User create(User model) {
        String SQL = "INSERT INTO ers_users (ERS_USERNAME, ers_password, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            System.out.println("user role fk value: " + model.getRole());
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, model.getUsername());
            pstmt.setString(2, model.getPassword());
            pstmt.setString(3, model.getFirst());
            pstmt.setString(4, model.getLast());
            pstmt.setString(5, model.getEmail());
            pstmt.setInt(6, model.getRole());
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                int key = keys.getInt(1);
                model.setRole(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }



    public static User read(String username) {
        User model = new User();
        boolean hasRows = false;
        try{
            String SQL = "SELECT * FROM p1.ers_users WHERE ers_username = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hasRows = true;
                model.setId(rs.getInt("ERS_USERS_ID"));
                model.setFirst(rs.getString("USER_FIRST_NAME"));
                model.setLast(rs.getString("USER_LAST_NAME"));
                model.setUsername(rs.getString("ERS_USERNAME"));
                model.setPassword(rs.getString("ers_password"));
                model.setEmail(rs.getString("USER_EMAIL"));
                model.setRole(rs.getInt("USER_ROLE_ID"));
            }
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        if(hasRows){
            return model;
        } else {
            return null;
        }
    }


    public static List<User> getAllUsers(){
        User model = new User();
        List<User> users = new ArrayList<User>();
        try{
            String SQL = "SELECT * FROM p1.ers_users";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                model.setFirst(rs.getString("USER_FIRST_NAME"));
                model.setLast(rs.getString("USER_LAST_NAME"));
                model.setUsername(rs.getString("ERS_USERNAME"));
                model.setPassword(rs.getString("ers_password"));
                model.setEmail(rs.getString("USER_EMAIL"));
                model.setRole(rs.getInt("USER_ROLE_ID"));
                users.add(model);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }






    // Update



    // Delete

    /*
    public static void saveUser(User u){
        String SQL = "INSERT INTO ers_users (ERS_USERNAME, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID) VALUES (?, ?, ?, ?, ?, 1)";
        try{

        } catch (SQLException e) {

        }
    }

    @Override
    public ToDoItemModel create(ToDoItemModel model) {
        String sql = "INSERT INTO to_do_Items (task,due,completed,user_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, model.getTask());
            pstmt.setDate(2, Date.valueOf(model.getDate()));
            pstmt.setBoolean(3, model.isCompleted());
            pstmt.setInt(4, model.getUserId());
            pstmt.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }



    */










    //User getUserByFirstLast(String first, String last);

    /*public static User read(String first, String last){
        User model = new User();
        boolean hasRows = false;
        try{
            String SQL = "SELECT * FROM p1.ers_users WHERE USER_FIRST_NAME = ? AND USER_LAST_NAME = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,first);
            pstmt.setString(2,last);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hasRows = true;
                model.setFirst(rs.getString("USER_FIRST_NAME"));
                model.setLast(rs.getString("USER_LAST_NAME"));
                model.setUsername(rs.getString("ERS_USERNAME"));
                model.setPassword(rs.getString("ers_password"));
                model.setEmail(rs.getString("USER_EMAIL"));
                model.setRole(rs.getInt("USER_ROLE_ID"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        if(hasRows){
            return model;
        } else {
            return null;
        }
    }
*/


    //void updateUser(User u);
/*
    public User read(int id) {
        User model = new User();
        try {
            String SQL = "SELECT * FROM to_do_items WHERE item_id = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                //model.setItemId(rs.getInt("item_id"));
                //model.setTask(rs.getString("task"));
                //model.setDate(rs.getString("due"));
                //model.setCompleted(rs.getBoolean("completed"));
                //model.setUserId(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

*/


}
