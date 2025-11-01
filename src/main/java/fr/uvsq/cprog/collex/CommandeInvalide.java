package fr.uvsq.cprog.collex;

public class CommandeInvalide implements Commande {
    private final String ligne;

    public CommandeInvalide(String ligne) {
        this.ligne = ligne;
    }

    @Override
    public void execute(Dns dns, DnsTUI tui) {
        tui.affiche("Commande invalide !");
    }
}