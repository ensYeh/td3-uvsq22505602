package fr.uvsq.cprog.collex;

public class CommandeQuit implements Commande {

    private final String ligne;

    public CommandeQuit() {
        this.ligne = "quit";
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        if (tui != null)
            tui.affiche("Fermeture du programme...");
        else
            System.out.println("Fermeture du programme...");

        System.exit(0); // Fin du programme
    }
}
