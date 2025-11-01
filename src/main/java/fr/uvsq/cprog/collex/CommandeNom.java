package fr.uvsq.cprog.collex;

public class CommandeNom implements Commande {

    private final String ligne;

    public CommandeNom(String ligne) {
        this.ligne = ligne;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        NomMachine nom = new NomMachine(ligne.trim());
        DnsItem item = dns.getItem(nom);

        String message;
        if (item != null)
            message = item.getAdresseIP().toString();
        else
            message = "Aucune adresse trouvée pour " + nom;

        if (tui != null)
            tui.affiche(message);
        else
            System.out.println(message);
    }
}
