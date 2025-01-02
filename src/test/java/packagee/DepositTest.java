package packagee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepositTest {

    private Deposit deposit;



    @Test
    void testGetType() {
        Deposit deposit = new Deposit(1, 2222, 500.0, System.currentTimeMillis());

        // Tester la méthode getType()
        assertEquals("DEPOSIT", deposit.getType(), "Le type de la transaction doit être DEPOSIT");
    }

    @Test
    void testToString() {
        // Tester la méthode toString()
        Deposit deposit = new Deposit(1, 2222, 500.0, System.currentTimeMillis());
        String result = deposit.toString();
        assertTrue(result.contains("Type: Deposit"), "La méthode toString() doit contenir 'Type: Deposit'");
        assertTrue(result.contains("account 2222"), "La méthode toString() doit contenir le numéro de compte");
    }

    @Test
    void testConstructor() {
        Deposit deposit = new Deposit(1, 2222, 500.0, System.currentTimeMillis());
        // Tester le constructeur et les valeurs
        assertEquals(2222, deposit.getAccount(), "Le numéro de compte doit être correct");
        assertEquals(500.0, deposit.getAmount(), "Le montant du dépôt doit être correct");
    }

    @Test
    void testConstructorWithTimestamp() {
        // Tester le constructeur avec un timestamp
        long timestamp = System.currentTimeMillis();
        Deposit depWithTimestamp = new Deposit(1, 2222, 500.0, timestamp);

        assertEquals(2222, depWithTimestamp.getAccount(), "Le numéro de compte doit être correct");
        assertEquals(500.0, depWithTimestamp.getAmount(), "Le montant du dépôt doit être correct");
        assertTrue(depWithTimestamp.getTimestamp() <= System.currentTimeMillis(), "Le timestamp doit être avant ou égal à l'heure actuelle");
    }
}
