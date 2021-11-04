package com.revature;

import com.revature.dao.BookDAO;
import com.revature.dao.UserDAO;
import com.revature.model.User;
import com.revature.service.UserService;

import java.util.Scanner;

public class Driver {

    public static boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean userLoginSubMenu(User user) {


//        3. User can select category to get the list of books belong to that category
//        4. Display list of book (like title, author, ISBN number)
//        5. User can choose a book to get more information (like title, author, price, category, description)

        // Submenus
            // 6. There should to option (1-buy, 2-cancel)
            // 7. when select buy add book into cart and cancel go back to previous menu
            // 8. View cart and place the order Instruction

        System.out.println("Press 1: Show all books");
        System.out.println("Press 2: Show books genres");
        System.out.println("Press 3: Select Book");

        System.out.println("Press 4: Exit");

        return true;
    }


    public static void main(String[] args) {

        // Need Daos: BookDao, UserDao, OrdersDao

        //OrdersDao ordersDao

        Scanner scanner = new Scanner(System.in);

        boolean menu = true;
        boolean run = true;
//        1. User need to register and login first
//        2. Show Categories like (Technology, Literature, Fiction etc)
//        3. User can select category to get the list of books belong to that category
//        4. Display list of book (like title, author, ISBN number)
//        5. User can choose a book to get more information (like title, author, price, category, description)
//        6. There should to option (1-buy, 2-cancel)
//        7. when select buy add book into cart and cancel go back to previous menu
//        8. View cart and place the order Instruction


        UserService userService = new UserService();

        String name;
        String username;
        String password;

        while (run) {
            if (menu) {
                System.out.println("*******************************************");
                System.out.println("******* Welcome to The Bookstore! *********");
                System.out.println("*******************************************");
                System.out.println();
                System.out.println("Press 1: Register for an account.");
                System.out.println("Press 2: User Login");
                System.out.println("Press 3: Exit");
            }

            String input = scanner.next();
            if (isStringInt(input)) {
                switch (Integer.parseInt(input)) {
                    case 1:
                        // Register
                        break;
                    case 2:
                        // Login
                        System.out.println("Enter your username");
                        username = scanner.next();
                        System.out.println("Enter your password");
                        password = scanner.next();
                        User user = new User();
                        user.setName("");
                        user.setUsername(username);
                        user.setPassword(password);
                        if (userService.userLogin(user)) {
                            userLoginSubMenu(user);
                        } else {
                            System.out.println("Check your username and password.");
                        }
                        break;
                    case 3:
                        // Exit
                        run = false;
                        break;
                    default:
                        System.out.println("Choose option 1 or 2");
                        break;
                }
            }
        }

    }

}
