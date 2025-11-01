package fr.uvsq.cprog.collex;

import java.util.Scanner;

public final class DnsTUI {
    private final Scanner sc = new Scanner(System.in);

    public Commande nextCommande() {
        System.out.println("> ");
        String ligne = sc.nextLine().trim();

        if(ligne.equalsIgnoreCase("quit")) {
            return new CommandeQuit();
        }

        if(ligne.startsWith("add")){
            return new CommandeAdd(ligne);
        }

        if(ligne.startsWith("ls")) {
            return new CommandeLs(ligne);
        }

        if(ligne.matches("^\\d+\\.\\d+\\.\\d+\\.\\d+$")) {
            return new CommandeAdresse(ligne);
        }

        if(ligne.contains(".")) {
            return new CommandeNom(ligne);
        }

        return new CommandeInvalide(ligne);
    }

    public void affiche(String message){
        System.out.println(message);
    }
}