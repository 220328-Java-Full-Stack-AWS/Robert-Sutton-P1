package com.revature.driver;

import com.revature.dao.*;
import com.revature.models.*;
import com.revature.services.PostService;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import java.math.BigDecimal;
import java.util.*;

public class Driver {

    private static UserDao uDao = new UserDaoMockImp(); //After you connect to your database, you can swap UserDaoMockImp with your actual db logic
    //private static UserDao uDao = new UserDao();
    private static UserService uServ = new UserService(uDao);
    private static PostDao pDao = new PostDaoMockImpl();
    private static PostService pServ = new PostService(pDao);
    private static Scanner scan = new Scanner(System.in);


    public static void main(String[] args){

        System.out.println("Driver is driving.");


        /*
        *
        * User Stories
          Requirements:
          Guest:
          As a guest, I can register for a new account
          */

        System.out.println("Enter your first name below:");
        String first = scan.nextLine();
        System.out.println("Enter your last name below:");
        String last = scan.nextLine();

        // Is user already registered?
        //User u = UserDao.read(first, last);
        String userName = UserService.getUserName(first, last);
        User u = User.User(first, last);
        //String userName = u.getUsername();

        boolean mayRegister = false;

        if(u == null) {
            System.out.println("You may register.");
            mayRegister = true;
        } else {
            System.out.println("There is already a registered user, " + first + " "+ last);
            System.out.println(u.toString());
        }

        if(mayRegister){
            System.out.println("Enter your Password below:");
            String psw = scan.nextLine();
            System.out.println("Enter your E-Mail below:");
            String email = scan.nextLine();
            u = UserService.register(first, last, email, psw);
            System.out.println(u.getFirst() + " " + u.getLast() + ", you are now registered:  UserName:  " + u.getUsername());
        }

        // As a guest, I can log into my account
        //String userName;
        System.out.println("Enter username below:");
        userName = scan.nextLine();
        System.out.println("Enter your Password below:");
        String psw = scan.nextLine();

        u = UserDao.read(userName);

        if(u == null) {
            System.out.println("Invalid UserName or Password!");
            System.out.println("Forgot password, enter 'F'");
            System.out.println("Register, enter 'R'");
            String action = scan.nextLine();
        }
        else {
            boolean loggedIn = true;
            while(loggedIn){

                Reimbursement r = new Reimbursement();




                /*

                User:
                As a user, I can submit a request for reimbursement
                As a user, I can cancel a pending request for reimbursement
                As a user, I can view my pending and completed past requests for reimbursement
                As a user, I can edit my pending requests for reimbursement

                */

                System.out.println("Please enter your choice (1 .. 4.");
                System.out.println("1. Submit a request for reimbursement");
                System.out.println("2. Cancel a pending request for reimbursement");
                System.out.println("3. View my pending and completed past requests for reimbursement");
                System.out.println("4. Edit my pending requests for reimbursement");
                int iReimb = Integer.parseInt(scan.nextLine());
                switch (iReimb){
                    case 1:
                        System.out.println("Describe the expense.");
                        String description = scan.nextLine();
                        System.out.println("Enter type.");
                        String[] type = {"Travel-Gas",
                                "Travel-Fares",
                                "Travel-Lodging",
                                "Travel-Food",
                                "Travel-Entertain",
                                "Travel-Other",
                                "Emp-Dev-Tuition",
                                "Emp-Dev-License",
                                "Emp-Dev-Other"} ;
                        for(int i = 0; i < 9; i++) {
                            System.out.println(i+1 + ". " + type[i]);
                        }
                        int typeId = Integer.parseInt(scan.nextLine());
                        System.out.println("Enter expense amount ($)");
                        BigDecimal amount = new BigDecimal(scan.nextLine());
                        System.out.println("Enter Vendor");
                        String vendor = scan.nextLine();
                        System.out.println("Enter invoice number");
                        String invoice = scan.nextLine();
                        System.out.println("DEBUG: " + description + " " + u + " " + typeId + " " + amount );
                        r = ReimbursementService.create(description, u, typeId, amount, vendor, invoice);

                        System.out.println(r.toString());



                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }


                Role r1 = Role.USER;
                System.out.println(r1);

                int roleNum = u.getRole();

                if(roleNum > 1){


                    /*
                    Admin/Finance Manager:
                    As an admin, I can approve expense reimbursements
                    As an admin, I can deny expense reimbursements
                    As an admin, I can filter requests by status
                    Stretch Goals:
                    As an admin, I can change a user's role between admin and user
                    *
                    * */





                }








                System.out.println("Do you wish to log out (Y/N)?");
                String yn = scan.nextLine();
                yn = yn.toUpperCase();
                if(yn.startsWith("Y")) {
                    loggedIn = false;
                }



            }










        }























        //User u = uServ.register("Ethan", "McGill", "ethan.mcgill@revature.com", "password");
        //User u2 = uServ.register("Giorgi", "Amirajibi", "giorgi@mail.com", "password");
        //List<User> users = uDao.getAllUsers();
        //for(int i=0; i< users.size(); i++){
        //    System.out.println(users.get(i));
        //    System.out.println("Followers: " + users.get(i).getFollowers());
        //    System.out.println("Following: " + users.get(i).getFollowing());
        //}
        //uServ.followUser(u, u2.getUsername());
        //System.out.println(uDao.getAllUsers());
        //for(int i=0; i< users.size(); i++){
        //    System.out.println(users.get(i));
        //    System.out.println("Followers: " + users.get(i).getFollowers());
        //    System.out.println("Following: " + users.get(i).getFollowing());
        //}
        //System.out.println(uServ.topRankedUsers());


        //MockUserDB.getInstance().populateUsers();
        //MockPostDB.getInstance().populatePosts();

        //User loggedIn = null;
        //boolean done = false;
        //We probably need user input
        //Scanner scan = new Scanner(System.in);

        //We want to loop forever until the user decides they are done

        //while(!done){
            //Do our logic
            //Check to see if we have a logged in user
            //if(loggedIn == null){
                //Not being logged in, we have two options
                //Register a user
                //Login
                //Prompt the user
                //System.out.println("Do you want to login or register? Choose 1 for register, 2 for login");
                //Get their input
                //int choice = scan.nextInt();
                //scan.nextLine();
                //Do the logic based on their input
                //if(choice == 1){
                    //Prompt them for their name, email, password
                    //System.out.println("Enter your first name below:");
                    //String first = scan.nextLine();
                    //System.out.println("Enter your last name below:");
                    //String last = scan.nextLine();
                    //System.out.println("Enter your email below:");
                    //String email = scan.nextLine();
                    //System.out.println("Enter your password below");
                    //String password = scan.nextLine();
                    //uServ.register(first, last, email, password);
                    //System.out.println(uDao.getAllUsers());
                //}
                //else{
                //    //Prompt for username and password
                //    System.out.println("Enter your username below");
                //    String username = scan.nextLine();
                //    System.out.println("Enter your password below");
                //    String password = scan.nextLine();
                //    loggedIn = uServ.login(username, password);
                //    System.out.println(loggedIn);
                //}

            //}
            //else{
            //    //Do our other logic
            //    System.out.println("Welcome to the lamest social media app!");
            //    System.out.println("What would you like to do today?");
            //    System.out.println("Choose 1 to view feed, Choose 2 to follow users, Choose 3 to create a post");
            //    int choice = scan.nextInt();
            //    scan.nextLine();
            //    switch(choice){
            //        case 1:
            //            Set<Post> feed = pServ.getUserFeed(loggedIn);
            //            Iterator<Post> pIt = feed.iterator();
            //            while(pIt.hasNext()){
             //              Post p = pIt.next();
             //              System.out.println(p.getUser().getUsername() + "\t\t\t\t" + p.getTimestamp().toString());
             //              System.out.println(p.getContent());
             //              System.out.println();
             //          }
             //          break;
             //      case 2:
             //          System.out.println("Here are our top users you may want to follow");
             //          List<User> topUsers = uServ.topRankedUsers();
             //          //We can use an iterator to loop through our users
             //          System.out.println("Username\t\t\t\tFollowers");
             //          Iterator<User> userIterator = topUsers.iterator();
             //          //This is how we can loop through the entire list of users with an iterator
             //          while(userIterator.hasNext()){
             //              u = userIterator.next();
             //              System.out.println(u.getUsername() +"\t\t\t\t" + u.getFollowers().size());
             //          }
             //          System.out.println("Please input a user you would like to follow");
             //          String username = scan.nextLine();
             //         uServ.followUser(loggedIn, username);
             //         System.out.println(loggedIn);
             //         break;
             //     case 3:
             //         System.out.println("Please enter the text of your post");
             //         String content = scan.nextLine();

             //         pServ.createPost(loggedIn, content);

             //         //Just to check and make sure that the users post got put in the database
             //         System.out.println(pDao.getPostsByUser(loggedIn.getUsername()));

             //         break;
             //     default:
             //         System.out.println("I didn't quite catch that. Please reenter your choice");
             // }

               // System.out.println("Are you tired of using this garbage app (Y/N)?");
               // String s = scan.nextLine();
               // s = s.toUpperCase(Locale.ROOT);
               // if(s.startsWith("Y")){
               //     done = true;
               // }
            //}
        }

        //Tree set and Comparator in action today
        //Set<Post> postFeed = new TreeSet<>(new PostComparator());
        //Date d1 = new Date(1649360546000l);
        //Date d2 = new Date(1653048000000l);
        //Date d3 = new Date(1645981200000l);
        //Date d4 = new Date(1671926400000l);
        //Date d5 = new Date(1649408400000l);
        //Post p1 = new Post("What a great day, P1", new User(), d1);
        //Post p2 = new Post("What a random day, P2", new User(), d2);
        //Post p3 = new Post("I don't even know what day this is, P3", new User(), d3);
        //Post p4 = new Post("I think its Christmas, P4", new User(), d4);
        //Post p5 = new Post("Welcome to tomorrow, P5", new User(), d5);
        //postFeed.add(p1);
        //postFeed.add(p2);
        //postFeed.add(p3);
        //postFeed.add(p4);
        //postFeed.add(p5);
        //postFeed.add(p5);
        //System.out.println(postFeed);
//
        //System.out.println("Goodbye");
    //}

}
