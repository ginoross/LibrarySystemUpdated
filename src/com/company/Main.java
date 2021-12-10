package com.company;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static ArrayList<String> books = new ArrayList<>();
    private static File logins = new File("userLogins.txt"); //Change to something sensible

    public static void main(String[] args) {
        // write your code here
        boolean quit = false;
        CreateFile();
        getLoginDetails();
        while (!quit) {
            if (anotherBook().equals("n")) {
                quit = true;
            } else {
                books.add(getBookDetails());
            }
        }

        getBookData(0);


    }

    public static String getBookDetails() {
        // write your code here
        String bookTitle = getInput("Please enter the book title");
        int ISBN = getIsbn();
        String authorName = getInput("Please enter the author");
        String bookGenre = getInput("Please enter the book genre");
        return (bookTitle + "," + ISBN + "," + authorName + "," + bookGenre);
    }

    private static int getIsbn() {
        // write your code here
        int ISBN;
        while (true) {
            try {
                ISBN = Integer.parseInt(getInput("Please enter the ISBN of the book"));
                break;
            } catch (Exception e) {
                System.out.println("Please enter an integer");
            }
        }
        return ISBN;
    }

    public static String getInput(String prompt) {
        // write your code here
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void getBookData(int dataPart) {
        // write your code here
        for (String book : books) {
            String[] bookDetails = book.split(",");
            System.out.println(bookDetails[dataPart]);
        }
    }


    public static String anotherBook() {
        // write your code here
        String enterBook;
        do {
            enterBook = getInput("Would you like to add another book? PLease enter y or n: ");
        } while (!enterBook.equals("y") && (!enterBook.equals("n")));
        return enterBook;
    }

    public static void CreateFile() {
        // write your code here
        try {
            if (logins.createNewFile()) {
                System.out.println("File created: " + logins.getName());
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void getLoginDetails() {
        String getUsername = getInput("Please enter your username ");
        String getPassword = getInput("Please enter your password: ");
        String userData = getUsername + "," + getPassword;
        if (!checkLogin(userData)) {
            registerUser();
        }

    }

    public static boolean checkLogin(String userData) {
        boolean registeredUser = false;
        try {
            Scanner myReader = new Scanner(logins);
            while (myReader.hasNextLine() || !registeredUser) {
                String data = myReader.nextLine();
                if (data.equals(userData)) {
                    registeredUser = true;
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return registeredUser;
    }

    public static void registerUser() {
        System.out.println("You are registering now as you did not have login details in our database");
        String getUsername = getInput("Please enter your username ");
        String getPassword = getInput("Please enter your password: ");
        String userData = getUsername + "," + getPassword;
        System.out.println(userData);
        WriteToFile(userData);
    }

    public static void WriteToFile(String userData) {
        try {
            FileWriter myWriter = new FileWriter(logins.getName(), true); //True means append to file contents, False means overwrite
            myWriter.write(userData + "\n"); // Appends everything in the file
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
