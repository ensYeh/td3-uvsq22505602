package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandeAddTest {

    private Dns dns;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = Files.createTempFile("dns", ".txt");
        List<String> lignes = List.of(
            "192.168.0.1 serveur1.uvsq.fr",
            "10.0.0.2 serveur2.uvsq.fr"
        );
        Files.write(tempFile, lignes);
        dns = new Dns(tempFile.toString());
    }

    @Test
    public void testAjoutValide() throws IOException {
        CommandeAdd cmd = new CommandeAdd("8.8.8.8 google.dns.test");
        cmd.execute(dns, null);

        DnsItem item = dns.getItem(new NomMachine("google.dns.test"));
        assertNotNull(item, "L’élément ajouté doit exister dans le DNS");
        assertEquals("8.8.8.8", item.getAdresseIP().toString());

        List<String> contenu = Files.readAllLines(tempFile);
        assertTrue(contenu.stream().anyMatch(l -> l.contains("8.8.8.8")),
            "Le fichier doit contenir la nouvelle adresse");
    }

    @Test
    public void testAjoutNomDejaExistant() {
        CommandeAdd cmd = new CommandeAdd("192.168.0.1 serveur1.uvsq.fr");
        assertThrows(IllegalArgumentException.class, () -> cmd.execute(dns, null),
            "Doit lever une exception si le nom existe déjà");
    }

    @Test
    public void testAjoutSyntaxeIncorrecte() {
        CommandeAdd cmd = new CommandeAdd("8.8.8.8");
        cmd.execute(dns, null);  // ne doit pas planter
        assertEquals(2, dns.getItems().size(), "Aucun ajout ne doit être fait si la commande est mal formée");
    }
}
