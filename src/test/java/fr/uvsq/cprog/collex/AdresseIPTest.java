package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdresseIPTest {

    @Test
    void testAdresseValide() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", ip.toString());
    }

    @Test
    void testAdresseInvalide() {
        assertThrows(IllegalArgumentException.class, () -> new AdresseIP("999.0.0.1"));
    }

    @Test
    void testEgalite() {
        AdresseIP a = new AdresseIP("10.0.0.1");
        AdresseIP b = new AdresseIP("10.0.0.1");
        assertEquals(a, b);
    }
}
