package org.kataBank.Model;

import java.util.Date;

public class Transaction {

    private Date date;
    private int amount;
    private int balance;

    public Transaction() {

    }

    public Transaction(Date date, int amount, int balance) {
        this.date=date;
        this.amount=amount;
        this.balance=balance;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
