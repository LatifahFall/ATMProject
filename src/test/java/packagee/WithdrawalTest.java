package packagee;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawalTest {

    private Withdrawal withdrawal=new Withdrawal(2222,200.0);


    @Test
    void testGetType() {
        assertEquals("WITHDRAWAL", withdrawal.getType(), "Le type de la transaction doit être WITHDRAWAL");
    }

    @Test
    void testToString() {
        String result = withdrawal.toString();
        assertTrue(result.contains("Type: Withdrawal"), "La méthode toString() doit contenir 'Type: Withdrawal'");
        assertTrue(result.contains("account 2222"), "La méthode toString() doit contenir le numéro de compte");
    }

    @Test
    void testConstructor() {
        assertEquals(2222, withdrawal.getAccount(), "Le numéro de compte doit être correct");
        assertEquals(200.0, withdrawal.getAmount(), "Le montant du retrait doit être correct");
    }

    @Test
    void testConstructorWithTimestamp() {
        // Tester le constructeur avec un timestamp
        long timestamp = System.currentTimeMillis();
        Withdrawal witWithTimestamp = new Withdrawal(1, 2222, 200.0, timestamp);

        assertEquals(2222, witWithTimestamp.getAccount(), "Le numéro de compte doit être correct");
        assertEquals(200.0, witWithTimestamp.getAmount(), "Le montant du retrait doit être correct");
        assertTrue(witWithTimestamp.getTimestamp() <= System.currentTimeMillis(), "Le timestamp doit être avant ou égal à l'heure actuelle");
    }
}
