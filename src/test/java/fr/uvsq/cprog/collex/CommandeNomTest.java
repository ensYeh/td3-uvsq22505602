package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CommandeNomTest {

    private Dns dns;

    private static class FakeTUI {
        String message = "";
        public void affiche(String msg) { this.message = msg; }
        public String getMessage() { return message; }
    }

    @BeforeEach
    public void setUp() throws IOException {
        Path tempFile = Files.createTempFile("dns", ".txt");
        List<String> lignes = List.of(
            "192.168.0.1 serveur1.uvsq.fr",
            "10.0.0.2 serveur2.uvsq.fr",
            "193.51.68.1 dns.uvsq.fr"
        );
        Files.write(tempFile, lignes);
        dns = new Dns(tempFile.toString());
    }

    @Test
    public void testCommandeNomExistante() {
        FakeTUI tui = new FakeTUI();
        CommandeNom cmd = new CommandeNom("dns.uvsq.fr");
        cmd.execute(dns, (DnsTUI) null);

        DnsItem item = dns.getItem(new NomMachine("dns.uvsq.fr"));
        assertNotNull(item, "Le nom dns.uvsq.fr doit exister");
        assertEquals("193.51.68.1", item.getAdresseIP().toString());
    }

    @Test
    public void testCommandeNomInexistante() {
        FakeTUI tui = new FakeTUI();
        CommandeNom cmd = new CommandeNom("fake.uvsq.fr");
        cmd.execute(dns, (DnsTUI) null);

        DnsItem item = dns.getItem(new NomMachine("fake.uvsq.fr"));
        assertNull(item, "Le nom fake.uvsq.fr ne doit pas exister");
    }
}
