package org.kataBank.Service;

import org.kataBank.Exception.InvalidWithdrawalException;
import org.kataBank.Model.Transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.kataBank.Exception.InvalidDepositException;

public class Account implements AccountService {

    // List of transactions associated with the account
    List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public void deposit(int amount) {
        // Check if the deposit amount is positive
        if (amount > 0) {
            // Create a new transaction
            Transaction transaction = new Transaction();
            transaction.setDate(new Date());
            transaction.setAmount(amount);

            // If it's the first transaction then the balance == deposit amount
            if (transactions.isEmpty()) {
                transaction.setBalance(amount);
            }
            // If it's already exist a transaction then the balance == the last balance + deposit amount
            else {
                // the last transaction
                Transaction lastTransaction = transactions.get(transactions.size() - 1);
                transaction.setBalance(lastTransaction.getBalance() + amount);
            }

            transactions.add(transaction);
            System.out.println(transactions);
        } else {
            throw new InvalidDepositException("Deposit amount must be positive.");
        }
    }

    @Override
    public void withdraw(int amount) {
        // Check if the withdrawal amount is negative
        if (amount < 0) {
            // Create a new transaction
            Transaction transaction = new Transaction();
            transaction.setDate(new Date());
            transaction.setAmount(amount);

            // Check if the account is empty
            if (transactions.isEmpty()) {
                throw new InvalidWithdrawalException("The account is empty");
            } else {
                // the last transaction
                Transaction lastTransaction = transactions.getLast();


                if (lastTransaction.getBalance() + amount < 0) {
                    throw new InvalidWithdrawalException("The balance does not match the amount.");
                }

                // the balance = last transaction balance - the widthdrawal amount
                transaction.setBalance(lastTransaction.getBalance() + amount);
            }


            transactions.add(transaction);
            System.out.println(transactions);
        } else {

            throw new InvalidWithdrawalException("Withdraw amount must be negative.");
        }
    }

    @Override
    public void printStatement() {

        StringBuilder statement = new StringBuilder();
        statement.append(String.format("%-12s || %-6s || %-6s%n", "Date", "Amount", "Balance"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");


        for (Transaction transaction : transactions) {
            statement.append(String.format("%-12s || %6d || %6d%n",
                    formatter.format(transaction.getDate()),
                    transaction.getAmount(),
                    transaction.getBalance()));
        }

        // Print the account statement
        statement.append("\n");
        System.out.print(statement);
    }

    // Getter for transactions list
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // Setter for transactions list
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
