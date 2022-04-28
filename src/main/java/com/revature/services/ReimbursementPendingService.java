package com.revature.services;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementPendingDao;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementPending;
import com.revature.models.Status;
import com.revature.models.User;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ReimbursementPendingService {
    private ReimbursementPendingDao rd;

    //Dependency injection, it is considered good practice because we can swapout any type of UserDao implementation we want
    public ReimbursementPendingService(ReimbursementPendingDao rd){
        this.rd = rd;
    }

    public ReimbursementPendingService() {

    }

    public ReimbursementPending process(ReimbursementPending unprocessedReimbursement, Status finalStatus, User resolver) {
        return null;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<ReimbursementPending> getReimbursementsPendingByStatus(Status status) {
        return Collections.emptyList();
    }

    public static List<ReimbursementPending> getReimbursementsPendingByUserId(int userId) {
        return ReimbursementPendingDao.ReadPendingReimbursements(userId);
    }

    public static List<ReimbursementPending>getReimbursementsPending() {
        return ReimbursementPendingDao.ReadPendingReimbursements();
    }

    public static Reimbursement create(String description, int authorId, int typeId, BigDecimal amount, String vendor, String invoice) {
        Reimbursement r = new Reimbursement(description, authorId, typeId, amount, vendor, invoice);

        //From the service, we would make our database call to actually store this reimbursement away
        ReimbursementDao dao = new ReimbursementDao();

        int statusId = 1;
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
}
