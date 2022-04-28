package com.revature.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ReimbursementCompleted extends Reimbursement {
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
    private String resolverFirst;
    private String resolverLast;

    public ReimbursementCompleted(int id, Status status, User author, User resolver, BigDecimal amount) {
        //super(id, status, author, resolver, amount);
    }

    public ReimbursementCompleted(int id, Status status, int authorId, BigDecimal amount) {
        this.id = id;
        //this.status = status;
        //this.authorId = authorId;
        this.amount = amount;
    }

    public ReimbursementCompleted() {
        this.id = id;
        this.description = description;
        this.submitted = submitted;
        //this.author = author;
        //this.authorId = authorId;
        //this.typeId = typeId;
        //this.authorFirst = authorFirst;
        //this.authorLast = authorLast;
        this.type = type;
        this.amount = amount;
        this.vendor = vendor;
        this.invoice = invoice;
        this.status = status;
        this.resolverFirst = resolverFirst;
        this.resolverLast = resolverLast;
        //this.resolver = resolver;
    }

    public ReimbursementCompleted(String description, int authorId, int typeId, BigDecimal amount, String vendor, String invoice) {
        super();
    }

    public BigDecimal getAmount() { return amount; }

    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Timestamp getSubmitted() { return submitted; }

    public void setSubmitted(Timestamp submitted) { this.submitted = submitted; }

    public String getVendor() { return vendor; }

    public void setVendor(String vendor) { this.vendor = vendor; }

    public void setAuthorFirst(String user_first_name) { this.authorFirst = authorFirst; }

    public void setAuthorLast(String user_last_name) {
        this.authorLast = authorLast;
    }

    public void setType(String reimb_type) {
        this.type = reimb_type;
    }

    public void setStatus(String reimb_status) {
        this.status = reimb_status;
    }

    public String getInvoice() { return invoice; }

    public void setInvoice(String invoice) {this.invoice = invoice; }

    public void setResolverFirst(String resolver_first_name) {
        this.resolverFirst = resolver_first_name;
    }

    public void setResolverLast(String resolver_last_name) {
        this.resolverLast = resolver_last_name;
    }

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
                ", resolver = " + resolverFirst + " " + resolverLast + '\'' +
                '}';
    }


}
