import org.junit.jupiter.api.Test;
import org.kataBank.Service.Account;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceDepositTest {

    @Test
    void testDepositAmountIsPositive(){
        int depositAmount=-1500;
        Account account=new Account();
        Exception exception = assertThrows(Exception.class, () -> {
            account.deposit(depositAmount);
        });

        assertEquals("Deposit amount must be positive.", exception.getMessage());
    }



    @Test
    void testDepositCheckAmountRecorded(){
        int depositAmountExpected=1000;
        Account account=new Account();
        account.deposit(depositAmountExpected);
        System.out.println(account.getTransactions());

        int depositAmount=account.getTransactions().getLast().getAmount();


        assertEquals(depositAmountExpected,depositAmount);
    }

    @Test
    void testDepositCheckbBalance(){
        int depositAmountExpected=1000;
        Account account=new Account();

        int lastBalance=0;

        if(!account.getTransactions().isEmpty()) {
            lastBalance = account.getTransactions().get(account.getTransactions().size() - 1).getBalance();
        }

        account.deposit(depositAmountExpected);


        int newBalance=account.getTransactions().getLast().getBalance();
        int balanceExpected=lastBalance+depositAmountExpected;
        assertEquals(balanceExpected,newBalance);
    }


}
