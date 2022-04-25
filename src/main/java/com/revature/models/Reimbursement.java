package com.revature.models;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import sun.security.krb5.internal.crypto.EType;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This concrete Reimbursement class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>Description</li>
 *     <li>Creation Date</li>
 *     <li>Resolution Date</li>
 *     <li>Receipt Image</li>
 * </ul>
 *
 */
public class Reimbursement extends com.revature.models.Model {

   //public Reimbursement() {
   //    super();
   //}

    public static List<Reimbursement> getAllReimbursements;

    private int id;
    private String description;
    private Date submitted;
    private User author;
    //private int authorId;
    private int type;
    private int typeId;
    private BigDecimal amount;
    private String vendor;
    private String invoice;
    private Status status;
    private int statusId;
    private User resolver;
    private int resolverId;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

//public Reimbursement() {
    //    getAllReimbursements = new ArrayList<>();
    //}

   //public  Reimbursement Reimbursement(int id) {
   //    return ReimbursementDao.read(id);
   //}

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractReimbursement} class.
     * If other fields are needed, please create additional constructors.
     */
    public Reimbursement(int id, Status status, User author, User resolver, double amount) {
        //super(id, status, author, resolver, amount);
    }


    public Reimbursement(int id, Status status, User author, BigDecimal amount) {
        this.id = id;
        this.status = status;
        this.author = author;
        this.amount = amount;

    }



    public Reimbursement() {
        this.id = id;
        this.description = description;
        this.submitted = submitted;
        this.author = author;
        //this.authorId = authorId;
        this.type = type;
        this.amount = amount;
        this.vendor = vendor;
        this.invoice = invoice;
        this.status = status;
        this.resolver = resolver;
    }

    public Reimbursement(String description, User author, int typeId, BigDecimal amount, String vendor, String invoice) {
        super();
        System.out.println("Super Constructor: " + author.getId() + " Type: " + typeId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public java.util.Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Status getStatus() {
        return status;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String email) {
        this.vendor = email;
    }

//    public int getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(int authorId) {
//        this.authorId = authorId;
//    }

    public int getTypeId() {
        return type;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    /*
    public void setLast(String last) {
        this.last = last;
    }
*/
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /*
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    */
    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getResolverId() {
        return resolverId;
    }

    public void setResolverId(int resolverId) {
        this.resolverId = resolverId;
    }








    /*




    public int getRole() {return role; }

    public void setRole(int role) {this.role = role; }

    public List<User> getAllUsers(){
        return UserDao.getAllUsers();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }




    @Override
    public String toString() {
        return "Reimbursement{" +
                "email='" + author.getEmail() + '\'' +
                ", first='" + author.getFirst() + '\'' +
                ", last='" + author.getLast() + '\'' +
                ", expense='" + amount + '\'' +
                ", type Id='" + typeId + '\'' +
                ", status'" + statusId + '\'' +
                //", posts=" + posts.size() +
                //", followers=" + followers.size() +
                //", following=" + following.size() +
                '}';
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(first, user.first) && Objects.equals(last, user.last)
        && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(posts, user.posts)
        && Objects.equals(followers, user.followers) && Objects.equals(following, user.following);
    }
    */








}


