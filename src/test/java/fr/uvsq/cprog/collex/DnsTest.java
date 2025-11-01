package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DnsTest {

    @Test
    public void testChargementDepuisFichier() throws IOException {
        Path tempFile = Files.createTempFile("dns", ".txt");
        List<String> lignes = List.of(
            "192.168.0.1 serveur1.uvsq.fr",
            "10.0.0.2 serveur2.uvsq.fr",
            "193.51.68.1 dns.uvsq.fr"
        );
        Files.write(tempFile, lignes);

        Dns dns = new Dns(tempFile.toString());
        assertEquals(3, dns.getItems().size(), "Le fichier doit contenir 3 éléments");
    }

    @Test
    public void testGetItemParAdresse() throws IOException {
        Path tempFile = Files.createTempFile("dns", ".txt");
        List<String> lignes = List.of(
            "192.168.0.1 serveur1.uvsq.fr",
            "10.0.0.2 serveur2.uvsq.fr",
            "193.51.68.1 dns.uvsq.fr"
        );
        Files.write(tempFile, lignes);

        Dns dns = new Dns(tempFile.toString());
        DnsItem item = dns.getItem(new AdresseIP("10.0.0.2"));
        assertNotNull(item, "L’adresse 10.0.0.2 doit être trouvée");
        assertEquals("serveur2.uvsq.fr", item.getNomMachine().toString());
    }

    @Test
    public void testGetItemParNom() throws IOException {
        Path tempFile = Files.createTempFile("dns", ".txt");
        List<String> lignes = List.of(
            "192.168.0.1 serveur1.uvsq.fr",
            "10.0.0.2 serveur2.uvsq.fr",
            "193.51.68.1 dns.uvsq.fr"
        );
        Files.write(tempFile, lignes);

        Dns dns = new Dns(tempFile.toString());
        DnsItem item = dns.getItem(new NomMachine("dns.uvsq.fr"));
        assertNotNull(item, "Le nom dns.uvsq.fr doit être trouvé");
        assertEquals("193.51.68.1", item.getAdresseIP().toString());
    }

    @Test
    public void testAjoutEtSauvegarde() throws IOException {
        Path tempFile = Files.createTempFile("dns", ".txt");
        Dns dns = new Dns(tempFile.toString());

        DnsItem item = new DnsItem(
            new AdresseIP("8.8.8.8"),
            new NomMachine("google.dns.test")
        );
        dns.addItem(item);
        dns.sauvegarderFichier();

        List<String> contenu = Files.readAllLines(tempFile);
        assertTrue(contenu.stream().anyMatch(l -> l.contains("8.8.8.8")), 
            "Le fichier doit contenir la nouvelle adresse IP ajoutée");
    }
}
