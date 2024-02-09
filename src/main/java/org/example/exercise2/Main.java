package org.example.exercise2;

import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        String filepath = "C:\\Users\\kalas\\IdeaProjects\\Assignment2\\src\\main\\java\\org\\example\\exercise2\\account.json";

        Customer[] customers = readingJsonCOnvetingIntoObject();
        AccountDto[] customerDTOS = arrayCustomerToArrayDTO(customers);

        printAccounts(customerDTOS);



    }
    public static Customer[] readingJsonCOnvetingIntoObject() {
        Customer[] arr = null;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("C:\\Users\\kalas\\IdeaProjects\\Assignment2\\src\\main\\java\\org\\example\\exercise2\\account.json")) {
            arr = gson.fromJson(reader, Customer[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return arr;
    }
    public static AccountDto[] arrayCustomerToArrayDTO(Customer[] arrayCustomer) {
        AccountDto[] arrayOfDTO = new AccountDto[arrayCustomer.length];
        String fullName = null;

        for (int i = 0; i < arrayCustomer.length; i++) {
            Customer c = arrayCustomer[i];
             fullName = c.getFirstName() + " " + c.getLastName();
            arrayOfDTO[i] = new AccountDto(fullName, c.getAddress().getCity(), c.getAddress().getZipCode(), c.getAccount().getIsActive());
        }

        return arrayOfDTO;
    }

    public static void printAccounts(AccountDto[] accounts) {
        System.out.println("Accounts:");
        for (AccountDto account : accounts) {
            System.out.println("Full Name: " + account.getFullname());
            System.out.println("City: " + account.getCity());
            System.out.println("Zip Code: " + account.getZipCode());
            System.out.println("Is Active: " + account.isActive);
            System.out.println();
        }
    }

}



