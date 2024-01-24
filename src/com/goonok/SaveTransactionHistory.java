package com.goonok;

import java.io.*;

public class SaveTransactionHistory {
    BankAccount bank = new BankAccount();

    private final File transactionFile = new File("C:\\BankingApplication\\Data\\transaction");

    public SaveTransactionHistory(){
        File folder = new File("C:\\BankingApplication\\Data");
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!transactionFile.exists()){
            try {
                transactionFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        saveTransaction();
        getTransaction();
    }

    private String transactionType(){
        int transaction = bank.getPreviousTransaction();
        String st="";
        if (transaction>0){
           st += "Deposit : " + String.valueOf(transaction) + "\n";
        }else if (transaction<0){
            st += "Withdraw : " + String.valueOf(transaction)+ "\n";
        }
        return st;
    }

    protected void getTransaction(){
        StringBuilder s = new StringBuilder();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(transactionFile));
            String s1;
            while ((s1 = bufferedReader.readLine()) != null){
                s.append(s1);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void saveTransaction(){
        String s = transactionType();

        try{
            PrintWriter pw = new PrintWriter(transactionFile);
            pw.print(s);
            pw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
