package packagee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TopUpTest {

    private TopUp topUp;
    @Test
    public void testTransactionCreation() {
        TopUp tp= new TopUp(12345, 500.0,"INWI","0655555555");

        assertEquals(1, tp.getId());
        assertEquals(12345, tp.getAccount());
        assertEquals(500.0, tp.getAmount());
        assertTrue(tp.getTimestamp() <= new Date().getTime());
        assertEquals("INWI", tp.getOperator());
        assertEquals("0655555555", tp.getPhone());
    }


    @Test
    void testGetType() {
        topUp= new TopUp(12345, 500.0,"INWI","0655555555");;
        assertEquals("TOPUP", topUp.getType(), "Le type de la transaction doit être TOPUP");
    }

    @Test
    void testGetOperator() {
        topUp= new TopUp(12345, 500.0,"INWI","0655555555");;
        assertEquals("INWI", topUp.getOperator(), "L'opérateur de top-up doit être INWI");
    }

    @Test
    void testGetPhone() {
        topUp= new TopUp(12345, 500.0,"INWI","0665294168");;
        assertEquals("0665294168", topUp.getPhone(), "Le numéro de téléphone doit être correct");
    }

    @Test
    void testToString() {
        topUp= new TopUp(12345, 500.0,"INWI","0665294168");;
        // Tester la méthode toString()
        String result = topUp.toString();
        assertTrue(result.contains("Type: Top-Up"), "La méthode toString() doit contenir 'Type: Top-Up'");
        assertTrue(result.contains("Operator : INWI"), "La méthode toString() doit contenir 'Operator : INWI'");
        assertTrue(result.contains("Phone: 0665294168"), "La méthode toString() doit contenir 'Phone: 0665294168'");
    }

    @Test
    void testConstructor() {
        topUp= new TopUp(2200, 50.0,"INWI","0665294168");;
        // Tester le constructeur et les valeurs
        assertEquals(2200, topUp.getAccount(), "Le numéro de compte doit être correct");
        assertEquals(50.0, topUp.getAmount(), "Le montant du top-up doit être correct");
        assertEquals("INWI", topUp.getOperator(), "L'opérateur du top-up doit être INWI");
        assertEquals("0665294168", topUp.getPhone(), "Le numéro de téléphone doit être correct");
    }

    @Test
    void testConstructorWithTimestamp() {
        // Tester le constructeur avec un timestamp
        long timestamp = System.currentTimeMillis();
        TopUp topUpWithTimestamp = new TopUp(1, 2200, 50.0, timestamp, "ORANGE", "0665294168");

        assertEquals(2200, topUpWithTimestamp.getAccount(), "Le numéro de compte doit être correct");
        assertEquals(50.0, topUpWithTimestamp.getAmount(), "Le montant du top-up doit être correct");
        assertEquals("ORANGE", topUpWithTimestamp.getOperator(), "L'opérateur du top-up doit être ORANGE");
        assertEquals("0665294168", topUpWithTimestamp.getPhone(), "Le numéro de téléphone doit être correct");
        assertTrue(topUpWithTimestamp.getTimestamp() <= System.currentTimeMillis(), "Le timestamp doit être avant ou égal à l'heure actuelle");
    }
}
