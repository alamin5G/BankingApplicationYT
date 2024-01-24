package com.goonok;

import java.util.Scanner;

public class BankAccount {
    private int balance;
    private int previousTransaction;
    private String customerName;
    private String customerAccountNo;
    private SaveTransactionHistory history;

    public BankAccount(){

    }
    public BankAccount(String customerName, String customerAccountNo) {
        this.customerName = customerName;
        this.customerAccountNo = customerAccountNo;
        history = new SaveTransactionHistory();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPreviousTransaction() {

        return previousTransaction;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAccountNo() {
        return customerAccountNo;
    }

    public void setCustomerAccountNo(String customerAccountNo) {
        this.customerAccountNo = customerAccountNo;
    }

    private void deposit(int amount){
        if (amount != 0){
            balance = balance + amount;
            previousTransaction = amount;
            history.saveTransaction();
        }
    }

    private void withdraw(int amount){
        if (amount<=500000 && amount>=500 && balance>500){
            balance -= amount;
            previousTransaction = -amount;
            history.saveTransaction();
        }
    }

    public void showMenu(){
        int option;
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome "+ this.customerName);
        System.out.println("Your Account No " + this.customerAccountNo);
        System.out.println("\n1. Check Balance");
        System.out.println("2. Deposit\n3.Withdraw\n4. Previous Transaction");
        System.out.println("0. Exit");

        do{
            System.out.println("=======================================");
            System.out.println("Enter Your Choice Number");
            System.out.println("========================================");
            option = input.nextInt();

            switch (option){
                case 0:
                    System.exit(0);
                case 1:
                    System.out.println("Balance = " + this.balance);
                    break;
                case 2:
                    System.out.print("Enter amount for Deposit: ");
                    int dep = input.nextInt();
                    deposit(dep);
                    System.out.println("Your deposit has been completed!");
                    break;
                case 3:
                    System.out.print("Enter amount for withdraw: max=5Lac min=500");
                    int wit = input.nextInt();
                    withdraw(wit);
                    System.out.println("Your withdraw has been completed!");
                    break;
                case 4:
                    history.getTransaction();
                    break;
                default:
                    System.out.println("Invalid option! Try again the correct one!");
                    break;
            }
            System.out.println(getPreviousTransaction());

        } while (option!=0);

    }

}
