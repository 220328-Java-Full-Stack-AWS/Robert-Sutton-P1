package com.revature.dao;

import com.revature.ConnectionManager;
import com.revature.models.Reimbursement;
import com.revature.models.User;

import java.sql.*;
import java.util.List;

public interface ReimbursementDao extends CRUDInterface<Reimbursement>{

    @Override
    public default Reimbursement create(Reimbursement model) {
        String SQL = "INSERT INTO ERS_REIMBURSEMENT (REIMB_AUTHOR_ID, REIMB_TYPE_ID, REIMB_AMOUNT, REIMB_VENDOR, REIMB_INVOICE, REIMB_STATUS_ID)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        try {
            System.out.println("ReimbursementDAO, Line 16, create");
            System.out.println("Author" + model.getAuthorId());
            System.out.println("Reimbursement status: " + model.getStatus());
            System.out.println(SQL);
            System.out.println(model.getAuthorId() + " " + model.getStatusId());
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, model.getAuthorId());
            pstmt.setInt(2, model.getTypeId());
            pstmt.setBigDecimal(3, model.getAmount());
            pstmt.setString(4, model.getVendor());
            pstmt.setString(5, model.getInvoice());
            pstmt.setInt(6, model.getStatusId());
            pstmt.executeUpdate();
            ResultSet keys = pstmt.getGeneratedKeys();
            if(keys.next()) {
                int key = keys.getInt(1);
                model.setStatusId(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    public static Reimbursement read(User author){
        Reimbursement model = new Reimbursement();
        boolean hasRows = false;
        try{
            String SQL = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR_ID = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,author.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hasRows = true;
                model.setId(rs.getInt("REIMB_ID"));
                model.setSubmitted(rs.getDate("REIMB_SUBMITTED"));
                model.setAuthorId(rs.getInt("REIMB_AUTHOR_ID"));
                model.setTypeId(rs.getInt("REIMB_TYPE_ID"));
                model.setAmount(rs.getBigDecimal("REIMB_AMOUNT"));
                model.setVendor(rs.getString("REIMB_VENDOR"));
                model.setInvoice(rs.getString("REIMB_INVOICE"));
                model.setStatusId(rs.getInt("REIMB_STATUS_ID"));
                model.setResolverId(rs.getInt("REIMB_RESOLVER_ID"));

                /*REIMB_ID    		serial PRIMARY KEY,
	REIMB_DESCRIPTION	VARCHAR(500),
	REIMB_SUBMITTED		TIMESTAMP,
	REIMB_AUTHOR_ID		INT,
	REIMB_TYPE_ID		INT,
	REIMB_AMOUNT		DECIMAL(10, 2),
	REIMB_VENDOR		VARCHAR(200),
	REIMB_INVOICE		VARCHAR(50),
	--REIMB_RECEIPT		BLOB,
	REIMB_STATUS_ID		INT,
	REIMB_RESOLVER_ID	INT,
	CONSTRAINT ers_reimbursement_author_fkey FOREIGN KEY (REIMB_AUTHOR_ID) REFERENCES ERS_USERS (ERS_USERS_ID),
	CONSTRAINT ers_reimbursement_resolver_fkey FOREIGN KEY (REIMB_RESOLVER_ID) REFERENCES ERS_USERS (ERS_USERS_ID),
	CONSTRAINT ers_reimbursement_status_fkey FOREIGN KEY (REIMB_STATUS_ID) REFERENCES ERS_REIMBURSEMENT_STATUS (REIMB_STATUS_ID),
	CONSTRAINT ers_reimbursement_type_fkey FOREIGN KEY (REIMB_TYPE_ID) REFERENCES ERS_REIMBURSEMENT_TYPE (REIMB_TYPE_ID)
);*/




            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public static User read(String username) {
        User model = new User();
        boolean hasRows = false;
        try{
            String SQL = "SELECT * FROM ers_users WHERE ers_username = ?";
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











    @Override
    void update(Reimbursement model);
















    @Override
    void delete(int id);

    @Override
    void delete(Reimbursement model);

    @Override
    List<Reimbursement> getAll();
}