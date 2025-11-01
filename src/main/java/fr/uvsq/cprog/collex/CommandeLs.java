package fr.uvsq.cprog.collex;

import java.util.*;

public class CommandeLs implements Commande {
    private final String ligne;

    public CommandeLs(String ligne) {
        this.ligne = ligne.trim();
    }

    @Override
public void execute(Dns dns, DnsTUI tui) {
    String[] parties = ligne.trim().split("\\s+");
    boolean parAdresse = false;

    // Vérification des options valides
    for (String part : parties) {
        if (part.startsWith("-") && !part.equals("-a")) {
            tui.affiche("Option invalide : " + part);
            return;
        }
        if (part.equals("-a")) {
            parAdresse = true;
        }
    }

    List<DnsItem> liste;

    // Cas 1 : "ls" seul ou "ls -a"
    if (parties.length == 1 || (parties.length == 2 && parAdresse)) {
        liste = dns.getItems("");
    }
    // Cas 2 : "ls <domaine>" ou "ls -a <domaine>"
    else {
        String domaine = parties[parties.length - 1];
        liste = dns.getItems(domaine);
    }

    // Tri
    if (parAdresse) {
        liste.sort(Comparator.comparing(o -> o.getAdresseIP().toString()));
    } else {
        liste.sort(Comparator.comparing(o -> o.getNomMachine().toString()));
    }

    // Affichage
    if (liste.isEmpty()) {
        tui.affiche("Aucune entrée trouvée.");
    } else {
        for (DnsItem item : liste) {
            tui.affiche(item.toString());
        }
    }
}

}