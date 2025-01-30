import org.junit.jupiter.api.Test;
import org.kataBank.Model.Transaction;
import org.kataBank.Service.Account;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class printStatementTest {


    @Test
    void printTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        List<Transaction> transactions = List.of(
                new Transaction(formatter.parse("14/01/2012"), -500, 2500),
                new Transaction(formatter.parse("13/01/2012"), 2000, 3000),
                new Transaction(formatter.parse("10/01/2012"), 1000, 1000)
        );

        String expectedStatement = getExpectedStatement(transactions);

        Account account = new Account();
        account.setTransactions(transactions);



        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        account.printStatement();

        System.setOut(originalOut);

        assertEquals(outputStream.toString(), expectedStatement);
    }

    private String getExpectedStatement(List<Transaction> transactions) {
        StringBuilder statement = new StringBuilder();
        statement.append(String.format("%-12s || %-6s || %-6s%n", "Date", "Amount", "Balance"));
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for (Transaction transaction : transactions) {
            statement.append(String.format("%-12s || %6d || %6d%n",
                    formatter.format(transaction.getDate()),
                    transaction.getAmount(),
                    transaction.getBalance()));
        }
        statement.append("\n");
        return statement.toString();
    }
}
