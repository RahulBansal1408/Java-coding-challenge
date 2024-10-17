package main;

import dao.InsuranceServiceImpl;
import entity.Policy;
import exception.PolicyNotFoundException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        InsuranceServiceImpl service = new InsuranceServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Insurance Management System!");

        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Create a new policy");
            System.out.println("2. Retrieve a policy by ID");
            System.out.println("3. View all policies");
            System.out.println("4. Update an existing policy");
            System.out.println("5. Delete a policy");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        createPolicy(service, scanner);
                        break;
                    case 2:
                        retrievePolicy(service, scanner);
                        break;
                    case 3:
                        viewAllPolicies(service);
                        break;
                    case 4:
                        updatePolicy(service, scanner);
                        break;
                    case 5:
                        deletePolicy(service, scanner);
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        scanner.close();
    }

    // Method to create a new policy
    private static void createPolicy(InsuranceServiceImpl service, Scanner scanner) {
        System.out.print("Enter Policy ID: ");
        int policyId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Policy Name: ");
        String policyName = scanner.nextLine();
        System.out.print("Enter Policy Details: ");
        String policyDetails = scanner.nextLine();

        Policy policy = new Policy(policyId, policyName, policyDetails);
        boolean isCreated = service.createPolicy(policy);

        if (isCreated) {
            System.out.println("Policy created successfully!");
        } else {
            System.out.println("Failed to create policy.");
        }
    }

    // Method to retrieve a policy by ID
    private static void retrievePolicy(InsuranceServiceImpl service, Scanner scanner) {
        System.out.print("Enter Policy ID to retrieve: ");
        int policyId = scanner.nextInt();

        try {
            Policy policy = service.getPolicy(policyId);
            System.out.println("Policy Details: " + policy);
        } catch (PolicyNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to view all policies
    private static void viewAllPolicies(InsuranceServiceImpl service) {
        List<Policy> policies = service.getAllPolicies();
        if (policies.isEmpty()) {
            System.out.println("No policies found.");
        } else {
            System.out.println("All Policies:");
            for (Policy policy : policies) {
                System.out.println(policy);
            }
        }
    }

    // Method to update an existing policy
    private static void updatePolicy(InsuranceServiceImpl service, Scanner scanner) {
        System.out.print("Enter Policy ID to update: ");
        int policyId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        try {
            Policy existingPolicy = service.getPolicy(policyId);
            System.out.println("Current Policy Details: " + existingPolicy);

            System.out.print("Enter new Policy Name: ");
            String policyName = scanner.nextLine();
            System.out.print("Enter new Policy Details: ");
            String policyDetails = scanner.nextLine();

            existingPolicy.setPolicyName(policyName);
            existingPolicy.setPolicyDetails(policyDetails);

            boolean isUpdated = service.updatePolicy(existingPolicy);
            if (isUpdated) {
                System.out.println("Policy updated successfully!");
            } else {
                System.out.println("Failed to update policy.");
            }
        } catch (PolicyNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to delete a policy by ID
    private static void deletePolicy(InsuranceServiceImpl service, Scanner scanner) {
        System.out.print("Enter Policy ID to delete: ");
        int policyId = scanner.nextInt();

        try {
            boolean isDeleted = service.deletePolicy(policyId);
            if (isDeleted) {
                System.out.println("Policy deleted successfully!");
            } else {
                System.out.println("Failed to delete policy.");
            }
        } catch (PolicyNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
