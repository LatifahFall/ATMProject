package packagee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class TransactionTest {

    @Test
    public void testTransactionCreation() {
        Transaction transaction = new Transaction(12345, 500.0);

        assertEquals(0, transaction.getId());
        assertEquals(12345, transaction.getAccount());
        assertEquals(500.0, transaction.getAmount());
        assertTrue(transaction.getTimestamp() <= new Date().getTime());
    }

    @Test
    public void testTransactionWithCustomTimestamp() {
        long customTimestamp = 1700000000000L; // Exemple de timestamp
        Transaction transaction = new Transaction(1, 67890, 1000.0, customTimestamp);

        assertEquals(1, transaction.getId());
        assertEquals(67890, transaction.getAccount());
        assertEquals(1000.0, transaction.getAmount());
        assertEquals(customTimestamp, transaction.getTimestamp());
        assertTrue(transaction.getDate().startsWith("2023-11-14"));
    }

    @Test
    public void testGetSQLTimestamp() {
        Transaction transaction = new Transaction(12345, 200.0);
        String sqlTimestamp = transaction.getSQLTimestamp();

        assertNotNull(sqlTimestamp);
        assertTrue(sqlTimestamp.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    public void testToString() {
        Transaction transaction = new Transaction(12345, 300.0);
        String output = transaction.toString();

        assertTrue(output.contains("Transaction of id"));
        assertTrue(output.contains("account 12345"));
        assertTrue(output.contains("amount 300.0"));
    }

    @Test
    public void testGetType() {
        Transaction transaction = new Transaction(12345, 400.0);
        assertEquals("TRANSACTION", transaction.getType());
    }
}