package com.revature;

import com.revature.dao.BookDAO;
import com.revature.dao.OrderDAO;
import com.revature.dao.UserDAO;
import com.revature.model.Book;
import com.revature.model.Order;
import com.revature.model.User;
import com.revature.service.BookService;
import com.revature.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    public static boolean userLoggedIn(User user, Scanner scanner, BookService bookService) {

//        3. User can select category to get the list of books belong to that category
//        4. Display list of book (like title, author, ISBN number)
//        5. User can choose a book to get more information (like title, author, price, category, description)

                // Submenus
                // 6. There should to option (1-buy, 2-cancel)
                // 7. when select buy add book into cart and cancel go back to previous menu
                // 8. View cart and place the order Instruction

        System.out.println("Welcome back " + user.getName() + "!");
        List<Book> cart = new ArrayList<>();

        boolean run = true;
        while (run) {
            System.out.println("Press 1: Show all books");
            System.out.println("Press 2: Show books genres");
            System.out.println("Press 3: Select Book");
            System.out.println("Press 4: Show Cart");
            System.out.println("Press 5: Logout");

            String input = scanner.next();
            System.out.println();

            if (isStringInt(input)) {
                switch (Integer.parseInt(input)) {
                    case 1:
                        // Show all books
                        List<Book> booksList = bookService.getAllBooks();
                        for (Book book : booksList) {
                            System.out.println(book.getIsbnNumber() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getGenre() + ", $" + book.getPrice() + ", " + book.getDesc());
                        }
                        System.out.println();
                        break;
                    case 2:
                        // Show books genres
                        List<String> genreList = bookService.getAllGenres();
                        for (String genre : genreList)
                            System.out.println(genre);
                        break;
                    case 3:
                        // Select Book
                        System.out.println("Enter book ISBN number");
                        int isbn = scanner.nextInt();
                        Book book = bookService.getSingleBook(isbn);
                        System.out.println(book.getIsbnNumber() + ", " + book.getTitle() + ", " + book.getAuthor() + ", " + book.getGenre() + ", $" + book.getPrice() + ", " + book.getDesc());
                        System.out.println("1) Add to Cart 2) Cancel");
                        String in = scanner.next();
                        if(in.equals("1")){
                            cart.add(book);
                            System.out.println("Book Added to Cart");
                        }
                        break;
                    case 4:
                        int i = 0;
                        double price = 0;
                        for(Book a : cart){
                            i++;
                            price += a.getPrice();
                            System.out.println(a.getIsbnNumber() + ", " + a.getTitle() + ", " + a.getAuthor() + ", " + a.getGenre() + ", $" + a.getPrice() + ", " + a.getDesc());
                        }
                        System.out.println("1) Place Order 2) Back");
                        if(scanner.next().equals("1")){
                            Order order = new Order();
                            order.setUsername(user.getUsername());
                            order.setDesc("Number Of Books : " + i);
                            order.setTotal(price);
                            bookService.addOrder(order,cart);
                        }
                        break;
                    case 5:
                        // Logout
                        run = false;
                        break;
                    default:
                        System.out.println("Choose an option from 1 - 2");
                        break;
                }
            }
        }

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
        BookService bookService = new BookService();

        String name;
        String username;
        String password;
        User user;

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
                        user = new User();
                        System.out.println("Enter your name");
                        name = scanner.next();
                        System.out.println("Enter your email");
                        username = scanner.next();
                        System.out.println("Create a password");
                        password = scanner.next();
                        user.setName(name);
                        user.setUsername(username);
                        user.setPassword(password);
                        if (userService.userRegister(user)) {
                            System.out.println("You have successfully created an account!");
                        }
                        else {
                            System.out.println("Please try again.");
                        }
                        break;
                    case 2:
                        // Login
                        System.out.println("Enter your username");
                        username = scanner.next();
                        System.out.println("Enter your password");
                        password = scanner.next();
                        user = new User();
                        user.setName("");
                        user.setUsername(username);
                        user.setPassword(password);
                        User userLoggedIn = userService.userLogin(user);
                        if (userLoggedIn.getUsername().equals(user.getUsername())) {
                            userLoggedIn(userLoggedIn, scanner, bookService);
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
