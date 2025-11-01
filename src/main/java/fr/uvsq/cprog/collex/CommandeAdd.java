package fr.uvsq.cprog.collex;

public class CommandeAdd implements Commande {

    private final String ligne;

    public CommandeAdd(String ligne) {
        this.ligne = ligne;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        String[] parties = ligne.split(" ");
        if (parties.length != 2) {
            if (tui != null)
                tui.affiche("Usage : add <adresseIP> <nomMachine>");
            else
                System.out.println("Usage : add <adresseIP> <nomMachine>");
            return;
        }

        AdresseIP adresse = new AdresseIP(parties[0]);
        NomMachine nom = new NomMachine(parties[1]);

        if (dns.getItem(nom) != null) {
            if (tui != null)
                tui.affiche("ERREUR : Le nom de machine existe déjà !");
            else
                System.out.println("ERREUR : Le nom de machine existe déjà !");
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
        }

        DnsItem item = new DnsItem(adresse, nom);
        dns.addItem(item);
        dns.sauvegarderFichier();

        if (tui != null)
            tui.affiche("Ajout réussi !");
        else
            System.out.println("Ajout réussi !");
    }
}
