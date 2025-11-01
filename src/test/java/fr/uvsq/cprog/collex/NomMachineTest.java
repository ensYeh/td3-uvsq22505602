package fr.uvsq.cprog.collex;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NomMachineTest {

    @Test
    void testNomValide() {
        NomMachine nm = new NomMachine("serveur.uvsq.fr");
        assertEquals("serveur.uvsq.fr", nm.toString());

        NomMachine nm2 = new NomMachine("dns.google.com");
        assertEquals("dns.google.com", nm2.toString());
    }

    @Test
    void testNomInvalide() {
        // Trop court : seulement 1 ou 2 parties
        assertThrows(IllegalArgumentException.class, () -> new NomMachine("serveur"));
        assertThrows(IllegalArgumentException.class, () -> new NomMachine("serveur.uvsq"));

        // Trop long : plus de 3 parties
        assertThrows(IllegalArgumentException.class, () -> new NomMachine("serveur.uvsq.fr.extra"));

        // Partie vide
        assertThrows(IllegalArgumentException.class, () -> new NomMachine("serveur..fr"));

        // Commence ou se termine par un point
        assertThrows(IllegalArgumentException.class, () -> new NomMachine(".uvsq.fr"));
        assertThrows(IllegalArgumentException.class, () -> new NomMachine("serveur.uvsq."));

        // Null ou vide
        assertThrows(IllegalArgumentException.class, () -> new NomMachine(null));
        assertThrows(IllegalArgumentException.class, () -> new NomMachine(""));
    }
}
