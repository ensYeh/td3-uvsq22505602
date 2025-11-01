package fr.uvsq.cprog.collex;

public class CommandeAdresse implements Commande {

    private final String ligne;

    public CommandeAdresse(String ligne) {
        this.ligne = ligne;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        AdresseIP adresse = new AdresseIP(ligne.trim());
        DnsItem item = dns.getItem(adresse);

        String message;
        if (item != null)
            message = item.getNomMachine().toString();
        else
            message = "Aucun nom trouvé pour " + adresse;

        if (tui != null)
            tui.affiche(message);
        else
            System.out.println(message);
    }
}
