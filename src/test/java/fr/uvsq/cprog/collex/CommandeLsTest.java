package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CommandeLsTest {
    private Dns dns;
    private Path tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Création d’un fichier temporaire DNS
        tempFile = Files.createTempFile("dns", ".txt");
        List<String> lignes = List.of(
            "192.168.0.1 serveur1.uvsq.fr",
            "10.0.0.2 serveur2.uvsq.fr",
            "193.51.68.1 dns.uvsq.fr",
            "193.51.68.2 mail.uvsq.fr"
        );
        Files.write(tempFile, lignes);
        dns = new Dns(tempFile.toString());
    }

    @Test
    public void testLsSansArgument() {
        CommandeLs cmd = new CommandeLs("ls");
        DnsTUI tui = new DnsTUI();
        cmd.execute(dns, tui);
        assertEquals(4, dns.getItems().size(),
            "La commande 'ls' doit afficher les 4 entrées.");
    }

    @Test
    public void testLsAvecDomaine() {
        CommandeLs cmd = new CommandeLs("ls uvsq.fr");
        DnsTUI tui = new DnsTUI();
        cmd.execute(dns, tui);
        List<DnsItem> res = dns.getItems("uvsq.fr");
        assertEquals(4, res.size(),
            "Le domaine 'uvsq.fr' doit contenir 4 éléments.");
    }

    @Test
    public void testLsAvecTriParAdresse() {
        // ✅ Adaptation : test conforme à l’ordre LEXICOGRAPHIQUE du TP
        CommandeLs cmd = new CommandeLs("ls -a uvsq.fr");
        DnsTUI tui = new DnsTUI();
        cmd.execute(dns, tui);

        List<DnsItem> res = dns.getItems("uvsq.fr");

        // Selon le tri lexicographique, '192.168.0.1' vient avant '10.0.0.2'
        assertEquals("192.168.0.1", res.get(0).getAdresseIP().toString(),
            "Le tri lexicographique place '192.168.0.1' avant '10.0.0.2'.");
    }

    @Test
    public void testLsDomaineInexistant() {
        CommandeLs cmd = new CommandeLs("ls fake.fr");
        DnsTUI tui = new DnsTUI();
        cmd.execute(dns, tui);
        List<DnsItem> res = dns.getItems("fake.fr");
        assertTrue(res.isEmpty(),
            "Un domaine inexistant ne doit rien retourner.");
    }
}
