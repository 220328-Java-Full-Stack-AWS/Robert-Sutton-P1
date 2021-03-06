package com.revature.services;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     *
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */

    private ReimbursementDao rd;

    //Dependency injection, it is considered good practice because we can swapout any type of UserDao implementation we want
    public ReimbursementService(ReimbursementDao rd){
        this.rd = rd;
    }

    public ReimbursementService() {

    }


    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
        return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(Status status) {
        return Collections.emptyList();
    }

    public static List<Reimbursement>getReimbursementsByStatusId(int statusId) {
        return ReimbursementDao.getReimbursementsByStatusId(statusId);
    }

    public static List<Reimbursement> getReimbursementsByUserIdStatusId(int userId, int statusId) {
        return ReimbursementDao.read(userId, statusId);
    }

    public static Reimbursement create(String description, int authorId, int typeId, BigDecimal amount, String vendor, String invoice) {
        Reimbursement r = new Reimbursement(description, authorId, typeId, amount, vendor, invoice);

        //From the service, we would make our database call to actually store this reimbursement away
        ReimbursementDao dao = new ReimbursementDao();

        int statusId = 1;
        System.out.println("ReimbursementService Line 80:  Author = " + authorId + ", Status = " + statusId);
        r.setDescription(description);
        r.setAuthorId(authorId);
        r.setTypeId(typeId);
        r.setAmount(amount);
        r.setVendor(vendor);
        r.setInvoice(invoice);
        r.setStatusId(statusId);
        dao.create(r);
        return r;
    }


    //public static void update(int id) { ReimbursementDao.update(id); }

    public static void update(int id, String desc, int type_id, String vend, String invc, BigDecimal amnt){
        ReimbursementDao.update(id, desc, type_id, vend, invc, amnt);
    }

    public static void cancel(int id) {
        ReimbursementDao.cancel(id);
    }

    public static void approve(int id) { ReimbursementDao.approve(id); }

    public static void deny(int id) { ReimbursementDao.deny(id); }





}
