// Project Title: Password Manager
// Language: Java
// Author: Iniya S
// Description:
// This project securely stores and retrieves passwords using
// a master password and file handling.
import java.io.*;
import java.io.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String masterPassword = "aishu06"; // set your master password here

        // Ask for master password
        System.out.print("Enter Master Password: ");
        String inputPassword = sc.nextLine();

        if (!inputPassword.equals(masterPassword)) {
            System.out.println("Incorrect Master Password. Exiting...");
            sc.close();
            return;
        }

        ArrayList<String> passwords = new ArrayList<>();
        String fileName = "passwords.txt";

        // Load saved passwords from file if exists
        try {
            File file = new File(fileName);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    passwords.add(line);
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading passwords: " + e.getMessage());
        }

        int choice;

        while (true) {
            // Menu
            System.out.println("\n--- PASSWORD MANAGER ---");
            System.out.println("1. Add Password");
            System.out.println("2. View Passwords");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                // Add password
                System.out.print("Website: ");
                String website = sc.nextLine();
                System.out.print("Username: ");
                String username = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();

                String entry = website + " | " + username + " | " + password;
                passwords.add(entry);

                // Save to file immediately
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));
                    bw.write(entry);
                    bw.newLine();
                    bw.close();
                } catch (IOException e) {
                    System.out.println("Error saving password: " + e.getMessage());
                }

                System.out.println("Password Saved Successfully!");
            } else if (choice == 2) {
                // View passwords
                if (passwords.isEmpty()) {
                    System.out.println("No passwords saved yet!");
                } else {
                    System.out.println("\nSaved Passwords:");
                    for (String p : passwords) {
                        System.out.println(p);
                    }
                }
            } else if (choice == 3) {
                System.out.println("Exiting Password Manager. Bye!");
                break; // exit loop
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        sc.close();
    }
}
