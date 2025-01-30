import org.junit.jupiter.api.Test;
import org.kataBank.Model.Transaction;
import org.kataBank.Service.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountServiceWithdrawalTest {

    @Test
    void testWithdrawAmountIsNegative(){
        int withdrawAmount=1500;

        Account account=new Account();

        Exception exception = assertThrows(Exception.class, () -> {
            account.withdraw(withdrawAmount);
        });


        assertEquals("Withdraw amount must be negative.", exception.getMessage());
    }



    @Test
    void testWithdrawIfAccountIsEmpty(){
        int withdrawAmount=-1000;
        Account account=new Account();
        Exception exception = assertThrows(Exception.class, () -> {
            account.withdraw(withdrawAmount);
        });
        assertEquals("The account is empty", exception.getMessage());
    }

    @Test
    void testIfWithdrawalAmountGreaterThanBalance(){

        Account account=new Account();

        List<Transaction> transactions=new ArrayList<>();
        Transaction transaction=new Transaction(new Date(),1000,1000);
        transactions.add(transaction);
        account.setTransactions(transactions);

        int withdrawAmount=-1500;


        Exception exception = assertThrows(Exception.class, () -> {
            account.withdraw(withdrawAmount);
        });
        assertEquals("The balance does not match the amount.", exception.getMessage());
    }

    @Test
    void testWithdrawCheckBalance(){
        int withdrawalAmount=-1000;
        List<Transaction> transactions=new ArrayList<>();
        Transaction transaction=new Transaction(new Date(),1000,1000);
        transactions.add(transaction);
        Account account=new Account();
        account.setTransactions(transactions);
        int lastBalance=account.getTransactions().get(account.getTransactions().size()-1).getBalance();
        account.withdraw(withdrawalAmount);

        int newBalance=account.getTransactions().getLast().getBalance();
        int balanceExpected=lastBalance+withdrawalAmount;
        assertEquals(balanceExpected,newBalance);
    }
}
