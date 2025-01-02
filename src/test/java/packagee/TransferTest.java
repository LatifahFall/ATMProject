package packagee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class TransferTest {

    private Transfer transfer;

    @BeforeEach
    void setUp() {
        transfer = new Transfer(2, 34, "0665294168", "7664545", "6555");
    }

    @Test
    void testGetType() {
        assertEquals("TRANSFER", transfer.getType(), "Le type de la transaction doit être TRANSFER");
    }

    @Test
    void testGetPincode() {
        assertEquals("6555", transfer.getPincode(), "Le code PIN doit être correct");
    }

    @Test
    void testGetIden() {
        assertEquals("7664545", transfer.getIden(), "L'identifiant doit être correct");
    }

    @Test
    void testGetPhone() {
        assertEquals("0665294168", transfer.getPhone(), "Le téléphone doit être correct");
    }

    @Test
    void testGetKilltime() {
        assertTrue(transfer.getKilltime() > 0, "Le temps de killtime doit être valide");
    }

    @Test
    void testGetDateKilltime() {
        assertNotNull(transfer.getDateKilltime(), "La date de killtime ne doit pas être nulle");
    }

    @Test
    void testGetTimeKilltime() {
        assertNotNull(transfer.getTimeKilltime(), "L'heure de killtime ne doit pas être nulle");
    }

    @Test
    void testGetSQLKilltime() {
        assertNotNull(transfer.getSQLKilltime(), "Le SQL killtime ne doit pas être nul");
    }

    @Test
    void testToString() {
        String result = transfer.toString();
        assertTrue(result.contains("Type: Transfer"), "La méthode toString() doit contenir 'Type: Transfer'");
    }
}
