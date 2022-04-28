package com.revature.dao;

import com.revature.ConnectionManager;
import com.revature.models.ReimbursementCompleted;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementCompletedDao extends ReimbursementDao{
    public ReimbursementCompleted read(int authorId){
        ReimbursementCompleted model = new ReimbursementCompleted();
        boolean hasRows = false;
        try{
            String SQL = "SELECT reimb_id, reimb_description, reimb_submitted, u.user_first_name, " +
                    "author_last_name, reimb_type, reimb_amount, reimb_vendor, reimb_invoice, reimb_status, " +
                    "eu.user_first_name AS Resolver_First_Name, eu.user_last_name AS Resolver_Last_Name " +
                    "FROM ERS_REIMBURSEMENT r " +
                    "JOIN ers_users u " +
                    "ON r.reimb_author_id = u.ers_users_id " +
                    "JOIN ers_reimbursement_type t " +
                    "ON r.reimb_type_id = t.reimb_type_id " +
                    "JOIN ERS_REIMBURSEMENT_STATUS s " +
                    "ON r.reimb_status_id = s.reimb_status_id " +
                    "JOIN ers_users eu " +
                    "ON r.reimb_resolver_id = eu.ers_users_id " +
                    "WHERE r.reimb_status_id = 5 AND r.REIMB_AUTHOR_ID = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, authorId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hasRows = true;
                model.setId(rs.getInt("REIMB_ID"));
                model.setDescription("reimb_description");
                model.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
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

    public static List<ReimbursementCompleted> ReadCompletedReimbursements(int authorId, int statusId){
        boolean hasRows = false;
        List<ReimbursementCompleted> reimbursementsCompleted = new ArrayList<>();
        try{
            String SQL = "SELECT reimb_id, reimb_description, reimb_submitted, u.user_first_name, " +
                    "u.user_last_name, reimb_type, reimb_amount, reimb_vendor, reimb_invoice, reimb_status, " +
                    "eu.user_first_name AS Resolver_First_Name, eu.user_last_name AS Resolver_Last_Name " +
                    "FROM ERS_REIMBURSEMENT r " +
                    "JOIN ers_users u " +
                    "ON r.reimb_author_id = u.ers_users_id " +
                    "JOIN ers_reimbursement_type t " +
                    "ON r.reimb_type_id = t.reimb_type_id " +
                    "JOIN ERS_REIMBURSEMENT_STATUS s " +
                    "ON r.reimb_status_id = s.reimb_status_id " +
                    "JOIN ers_users eu " +
                    "ON r.reimb_resolver_id = eu.ers_users_id " +
                    "WHERE r.reimb_status_id = ? AND r.REIMB_AUTHOR_ID = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, statusId);
            pstmt.setInt(2, authorId);
            ResultSet rs = pstmt.executeQuery();
/*											*/
            while (rs.next()) {
                hasRows = true;
                ReimbursementCompleted model = new ReimbursementCompleted();
                model.setId(rs.getInt("reimb_id"));
                model.setDescription(rs.getString("reimb_description"));
                model.setSubmitted(rs.getTimestamp("reimb_submitted"));
                model.setAuthorFirst(rs.getString("user_first_name"));
                model.setAuthorLast(rs.getString("user_last_name"));
                model.setType(rs.getString("reimb_type"));
                model.setAmount(rs.getBigDecimal("reimb_amount"));
                model.setVendor(rs.getString("reimb_vendor"));
                model.setInvoice(rs.getString("reimb_invoice"));
                model.setStatus(rs.getString("reimb_status"));
                model.setResolverFirst(rs.getString("resolver_first_name"));
                model.setResolverLast(rs.getString("resolver_last_name"));
                reimbursementsCompleted.add(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reimbursementsCompleted;
    }
}
