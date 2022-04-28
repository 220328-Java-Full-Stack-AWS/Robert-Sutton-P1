package com.revature.dao;

import com.revature.ConnectionManager;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementPending;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementPendingDao extends ReimbursementDao{
    public static List<ReimbursementPending> ReadPendingReimbursements() {

        List<ReimbursementPending> reimbursementsPending = new ArrayList<>();
        boolean hasRows = false;
        try {
            String SQL = "SELECT reimb_id, reimb_description, reimb_submitted, user_first_name, user_last_name, " +
                    "reimb_type, reimb_amount, reimb_vendor, reimb_invoice, reimb_status " +
                    "FROM ERS_REIMBURSEMENT r " +
                    "JOIN ers_users u " +
                    "ON r.reimb_author_id = u.ers_users_id " +
                    "JOIN ers_reimbursement_type t " +
                    "ON r.reimb_type_id = t.reimb_type_id " +
                    "JOIN ERS_REIMBURSEMENT_STATUS s " +
                    "ON r.reimb_status_id = s.reimb_status_id " +
                    "WHERE r.reimb_status_id = 1";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ReimbursementPending model = new ReimbursementPending();
                hasRows = true;
                model.setId(rs.getInt("REIMB_ID"));
                model.setDescription(rs.getString("reimb_description"));
                model.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
                model.setAuthorFirst(rs.getString("user_first_name"));
                model.setAuthorLast(rs.getString("user_last_name"));
                model.setType(rs.getString("reimb_type"));
                model.setAmount(rs.getBigDecimal("REIMB_AMOUNT"));
                model.setVendor(rs.getString("REIMB_VENDOR"));
                model.setInvoice(rs.getString("REIMB_INVOICE"));
                model.setStatus(rs.getString("reimb_status"));
                reimbursementsPending.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reimbursementsPending;
    }

    public ReimbursementPending read(int authorId){
        ReimbursementPending model = new ReimbursementPending();
        boolean hasRows = false;
        try{
            String SQL = "SELECT reimb_id, reimb_description, reimb_submitted, user_first_name, user_last_name, " +
                    "reimb_type, reimb_amount, reimb_vendor, reimb_invoice, reimb_status " +
                    "FROM ERS_REIMBURSEMENT r " +
                    "JOIN ers_users u " +
                    "ON r.reimb_author_id = u.ers_users_id " +
                    "JOIN ers_reimbursement_type t " +
                    "ON r.reimb_type_id = t.reimb_type_id " +
                    "JOIN ERS_REIMBURSEMENT_STATUS s" +
                    "ON r.reimb_status_id = s.reimb_status_id " +
                    "WHERE r.reimb_status_id = 1 AND r.REIMB_AUTHOR_ID = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hasRows = true;
                model.setId(rs.getInt("REIMB_ID"));
                model.setDescription(rs.getString("reimb_description"));
                model.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
                //model.setAuthorId((rs.getInt("REIMB_AUTHOR_ID")));
                model.setAuthorFirst(rs.getString("user_first_name"));
                model.setAuthorLast(rs.getString("user_last_name"));
                //model.setTypeId(rs.getInt("REIMB_TYPE_ID"));
                model.setType(rs.getString("reimb_type"));
                model.setAmount(rs.getBigDecimal("REIMB_AMOUNT"));
                model.setVendor(rs.getString("REIMB_VENDOR"));
                model.setInvoice(rs.getString("REIMB_INVOICE"));
                model.setStatus(rs.getString("reimb_status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }


    public static List<ReimbursementPending> ReadPendingReimbursements(int authorId){
        boolean hasRows = false;
        List<ReimbursementPending> reimbursementsPending = new ArrayList<>();
        try{
            //String SQL = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR_ID = ? AND REIMB_STATUS_ID = ?";
            String SQL = "SELECT reimb_id, reimb_description, reimb_submitted, user_first_name, user_last_name, " +
                    "reimb_type, reimb_amount, reimb_vendor, reimb_invoice, reimb_status " +
                    "FROM ERS_REIMBURSEMENT r " +
                    "JOIN ers_users u " +
                    "ON r.reimb_author_id = u.ers_users_id " +
                    "JOIN ers_reimbursement_type t " +
                    "ON r.reimb_type_id = t.reimb_type_id " +
                    "JOIN ERS_REIMBURSEMENT_STATUS s " +
                    "ON r.reimb_status_id = s.reimb_status_id " +
                    "WHERE r.REIMB_AUTHOR_ID = ? AND r.REIMB_STATUS_ID = 1";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                hasRows = true;
                ReimbursementPending model = new ReimbursementPending();
                model.setId(rs.getInt("REIMB_ID"));
                model.setDescription(rs.getString("reimb_description"));
                model.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
                model.setAuthorFirst(rs.getString("user_first_name"));
                model.setAuthorLast(rs.getString("user_last_name"));
                model.setType(rs.getString("reimb_type"));
                model.setAmount(rs.getBigDecimal("REIMB_AMOUNT"));
                model.setVendor(rs.getString("REIMB_VENDOR"));
                model.setInvoice(rs.getString("REIMB_INVOICE"));
                model.setStatus(rs.getString("reimb_status"));
                reimbursementsPending.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reimbursementsPending;
    }
}
