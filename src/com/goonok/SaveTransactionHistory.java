package com.goonok;

import java.io.*;

public class SaveTransactionHistory {
    private BankAccount bank;
    private String filePath="C:\\BankingApplication\\Data\\transaction";

    private final File transactionFile = new File(filePath);

    public SaveTransactionHistory(){
        File folder = new File("C:\\BankingApplication\\Data");
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!transactionFile.exists()){
            try {
                transactionFile.createNewFile();
            } catch (java.io.IOException e) {
                throw new RuntimeException(e);
            }
        }

        bank = new BankAccount();
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


        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(transactionFile));
            String s1;
            while ((s1 = bufferedReader.readLine()) != null){
                System.out.println(s1);
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

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(transactionFile, true));
            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
