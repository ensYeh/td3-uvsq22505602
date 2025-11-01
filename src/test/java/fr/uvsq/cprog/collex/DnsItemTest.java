package fr.uvsq.cprog.collex;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DnsItemTest {

    @Test
    void testCreationValide() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom = new NomMachine("serveur.uvsq.fr");
        DnsItem item = new DnsItem(ip, nom);

        assertEquals("192.168.0.1", item.getAdresseIP().toString());
        assertEquals("serveur.uvsq.fr", item.getNomMachine().toString());
        // Vérifie le toString() avec adresse avant nom
        assertEquals("192.168.0.1 serveur.uvsq.fr", item.toString());
    }

    @Test
    void testEgalite() {
        DnsItem item1 = new DnsItem(new AdresseIP("10.0.0.1"), new NomMachine("dns.uvsq.fr"));
        DnsItem item2 = new DnsItem(new AdresseIP("10.0.0.1"), new NomMachine("dns.uvsq.fr"));
        DnsItem item3 = new DnsItem(new AdresseIP("10.0.0.2"), new NomMachine("dns.uvsq.fr"));

        assertEquals(item1, item2, "Les deux DnsItem identiques doivent être égaux");
        assertNotEquals(item1, item3, "Les DnsItem différents doivent être considérés comme distincts");
    }

    @Test
    void testToStringFormat() {
        DnsItem item = new DnsItem(new AdresseIP("8.8.8.8"), new NomMachine("google.dns.com"));
        String attendu = "8.8.8.8 google.dns.com";
        assertEquals(attendu, item.toString(), "Le format de toString() doit être <adresse> <nom>");
    }
}

