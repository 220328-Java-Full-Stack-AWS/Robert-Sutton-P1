package com.revature.services;

import com.revature.dao.ReimbursementCompletedDao;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementPendingDao;
import com.revature.models.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ReimbursementCompletedService {
    private ReimbursementCompletedDao cd;


    //Dependency injection, it is considered good practice because we can swapout any type of UserDao implementation we want
    public ReimbursementCompletedService(ReimbursementCompletedDao cd) { this.cd = cd; }

    public ReimbursementCompletedService() {

    }

    public ReimbursementCompleted process(ReimbursementCompleted unprocessedReimbursement, Status finalStatus, User resolver) {
        return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<ReimbursementCompleted> getReimbursementsCompletedByStatus(Status status) {
        return Collections.emptyList();
    }

    public static List<ReimbursementCompleted> getReimbursementsCompletedByUserIdStatusId(int userId, int statusId) {
        return ReimbursementCompletedDao.ReadCompletedReimbursements(userId, statusId);
    }

    public static Reimbursement create(String description, int authorId, int typeId, BigDecimal amount, String vendor, String invoice, int resolverId) {
        Reimbursement r = new Reimbursement(description, authorId, typeId, amount, vendor, invoice, resolverId);

        //From the service, we would make our database call to actually store this reimbursement away
        ReimbursementDao dao = new ReimbursementDao();

        int statusId = 5;
        r.setDescription(description);
        r.setAuthorId(authorId);
        r.setTypeId(typeId);
        r.setAmount(amount);
        r.setVendor(vendor);
        r.setInvoice(invoice);
        r.setStatusId(statusId);
        r.setResolverId(resolverId);
        dao.create(r);
        return r;
    }

}
