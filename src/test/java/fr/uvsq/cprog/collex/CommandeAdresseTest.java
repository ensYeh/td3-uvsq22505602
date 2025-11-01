package fr.uvsq.cprog.collex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandeAdresseTest {

    private Dns dns;

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
    public void testCommandeAdresseExistante() {
        CommandeAdresse cmd = new CommandeAdresse("10.0.0.2");
        // on ne passe pas de TUI (tui = null) => affichage console
        cmd.execute(dns, null);
        DnsItem item = dns.getItem(new AdresseIP("10.0.0.2"));
        assertNotNull(item, "L’adresse 10.0.0.2 doit être trouvée");
        assertEquals("serveur2.uvsq.fr", item.getNomMachine().toString());
    }

    @Test
    public void testCommandeAdresseInexistante() {
        CommandeAdresse cmd = new CommandeAdresse("1.1.1.1");
        cmd.execute(dns, null);
        DnsItem item = dns.getItem(new AdresseIP("1.1.1.1"));
        assertNull(item, "Aucun nom ne doit être trouvé pour 1.1.1.1");
    }
}
