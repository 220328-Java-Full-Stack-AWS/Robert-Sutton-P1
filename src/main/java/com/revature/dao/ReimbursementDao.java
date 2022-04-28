package com.revature.dao;

import com.revature.ConnectionManager;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementPending;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReimbursementDao implements CRUDInterface<Reimbursement>{

    @Override
    public Reimbursement create(Reimbursement model) {
        String SQL = "INSERT INTO ERS_REIMBURSEMENT (REIMB_DESCRIPTION, REIMB_SUBMITTED, REIMB_AUTHOR_ID, REIMB_TYPE_ID, REIMB_AMOUNT, REIMB_VENDOR, REIMB_INVOICE, REIMB_STATUS_ID)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            System.out.println("ReimbursementDAO, Line 18, create");
            System.out.println("Line 19. Author:  " + model.getAuthorId()); //This is how we want to pull the author ID
            System.out.println("Line 20. Reimbursement status: " + model.getStatusId());
            System.out.println("Line 21. " + SQL);
            System.out.println("Line 22. Reimbursement type = " + model.getTypeId());
            Date submitted = Date.valueOf(LocalDate.now());
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, model.getDescription());
            pstmt.setDate(2, submitted);
            pstmt.setInt(3, model.getAuthorId());
            pstmt.setInt(4, model.getTypeId());
            pstmt.setBigDecimal(5, model.getAmount());
            pstmt.setString(6, model.getVendor());
            pstmt.setString(7, model.getInvoice());
            pstmt.setInt(8, model.getStatusId());
            System.out.println("Line 31:  " + pstmt.toString());
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

    public Reimbursement read(int authorId){
        Reimbursement model = new Reimbursement();
        boolean hasRows = false;
        try{
            String SQL = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR_ID = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hasRows = true;
                model.setId(rs.getInt("REIMB_ID"));
                model.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
                model.setAuthorId((rs.getInt("REIMB_AUTHOR_ID")));
                model.setTypeId(rs.getInt("REIMB_TYPE_ID"));
                model.setAmount(rs.getBigDecimal("REIMB_AMOUNT"));
                model.setVendor(rs.getString("REIMB_VENDOR"));
                model.setInvoice(rs.getString("REIMB_INVOICE"));
                model.setStatusId(rs.getInt("REIMB_STATUS_ID"));
                model.setResolverId(rs.getInt("REIMB_RESOLVER_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public static List<Reimbursement> read(int authorId, int statusId){
        boolean hasRows = false;
        List<Reimbursement> reimbursements = new ArrayList<>();
        try{
            String SQL = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR_ID = ? AND REIMB_STATUS_ID = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, authorId);
            pstmt.setInt(2, statusId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                hasRows = true;
                Reimbursement model = new Reimbursement();
                model.setId(rs.getInt("REIMB_ID"));
                model.setDescription(rs.getString("REIMB_DESCRIPTION"));
                model.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
                model.setAuthorId((rs.getInt("REIMB_AUTHOR_ID")));
                model.setTypeId(rs.getInt("REIMB_TYPE_ID"));
                model.setAmount(rs.getBigDecimal("REIMB_AMOUNT"));
                model.setVendor(rs.getString("REIMB_VENDOR"));
                model.setInvoice(rs.getString("REIMB_INVOICE"));
                model.setStatusId(rs.getInt("REIMB_STATUS_ID"));
                model.setResolverId(rs.getInt("REIMB_RESOLVER_ID"));
                reimbursements.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    public static List<Reimbursement>getReimbursementsByStatusId(int statusId) {
        boolean hasRows = false;
        List<Reimbursement> reimbursements = new ArrayList<>();
        try{
            String SQL = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, statusId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                hasRows = true;
                Reimbursement model = new Reimbursement();
                model.setId(rs.getInt("REIMB_ID"));
                model.setDescription(rs.getString("REIMB_DESCRIPTION"));
                model.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
                model.setAuthorId((rs.getInt("REIMB_AUTHOR_ID")));
                model.setTypeId(rs.getInt("REIMB_TYPE_ID"));
                model.setAmount(rs.getBigDecimal("REIMB_AMOUNT"));
                model.setVendor(rs.getString("REIMB_VENDOR"));
                model.setInvoice(rs.getString("REIMB_INVOICE"));
                model.setStatusId(rs.getInt("REIMB_STATUS_ID"));
                model.setResolverId(rs.getInt("REIMB_RESOLVER_ID"));
                reimbursements.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    public static void cancel(int id){
        try {
            update( id, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void approve(int id){
        try {
            update( id, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deny(int id) {
        try {
            update( id, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(int id, int statusId){
        try {
            String SQL = "UPDATE ers_reimbursement SET reimb_status_id = ? WHERE reimb_id = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, statusId);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void update(int id, String desc, int type_id, String vend, String invc, BigDecimal amnt) {
        try {
            String SQL = "UPDATE ers_reimbursement SET reimb_description = ?, reimb_type_id = ?, " +
                    "reimb_amount = ?, reimb_vendor = ?, reimb_invoice = ? WHERE reimb_id = ?;";

            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, desc);
            pstmt.setInt(2, type_id);
            pstmt.setBigDecimal(3, amnt);
            pstmt.setString(4, vend);
            pstmt.setString(5, invc);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Reimbursement model){
        return;
    }


    @Override
    public void delete(int id){

    }

    @Override
    public void delete(Reimbursement model){

    }

    @Override
    public List<Reimbursement> getAll(){
        List<Reimbursement> list = new LinkedList<>();
        return list;
    }
}
