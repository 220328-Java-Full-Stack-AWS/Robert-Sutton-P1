package com.revature.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ReimbursementPending extends Reimbursement {
    public static List<Reimbursement> getAllReimbursements;

    private int id;
    private String description;
    private Timestamp submitted;
    private String authorFirst;
    private String authorLast;
    private String type;
    private BigDecimal amount;
    private String vendor;
    private String invoice;
    private String status;

    public ReimbursementPending(int id, Status status, User author, User resolver, BigDecimal amount) {
        //super(id, status, author, resolver, amount);
    }

    public ReimbursementPending(int id, Status status, int authorId, BigDecimal amount) {
        this.id = id;
        //this.status = status;
        //this.authorId = authorId;
        this.amount = amount;
    }

    public ReimbursementPending() {
        this.id = id;
        this.description = description;
        this.submitted = submitted;
        //this.author = author;
        //this.authorId = authorId;
        //this.typeId = typeId;
        this.amount = amount;
        this.vendor = vendor;
        this.invoice = invoice;
        this.status = status;
        //this.resolver = resolver;
    }

    public ReimbursementPending(String description, int authorId, int typeId, BigDecimal amount, String vendor, String invoice) {
        super();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    //public String getStatus() {
    //    return status;
    //}

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setAuthorFirst(String user_first_name) {
        this.authorFirst = user_first_name;
    }

    public void setAuthorLast(String user_last_name) {
        this.authorLast = user_last_name;
    }

    public String getType() { return type; }

    public void setType(String reimb_type) {
        this.type = reimb_type;
    }

    public void setStatus(String reimb_status) {
        this.status = reimb_status;
    }

    public String getInvoice() { return invoice; }

    public void setInvoice(String invoice) { this.invoice = invoice; }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "Description = '" + description + '\'' +
                ", author = '" + authorFirst + " " + authorLast + '\'' +
                ", type = '" + type + '\'' +
                ", amount = '" + amount + '\'' +
                ", vendor = '" + vendor + '\'' +
                ", invoice = '" + invoice + '\'' +
                ", submitted = '" + submitted + '\'' +
                ", status = " + status +
                //", resolver = " + resolverId +
                '}';
    }
}
