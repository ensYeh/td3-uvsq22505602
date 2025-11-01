package fr.uvsq.cprog.collex;

import java.nio.file.Files;
import java.util.List;

import java.io.BufferedWriter;
import java.io.IOException;


import fr.uvsq.cprog.collex.DnsItem;

import java.util.ArrayList;
import java.nio.file.Path;

public final class Dns {
    private final List<DnsItem> items;
    private String cheminFichier;

    public Dns() {
        items = new ArrayList<>();
        cheminFichier = null;
    }

    public Dns(String cheminFichier){
        items = new ArrayList<>();
        if(cheminFichier == null) {
            throw new IllegalArgumentException("Erreur: chemin du fichier invalide.");
        }
        this.cheminFichier = cheminFichier;


        try{      
            List<String> lignes = Files.readAllLines(Path.of(cheminFichier));
            for(String ligne: lignes){
                String[] informationLigne = ligne.trim().split("\\s+");
                if(informationLigne.length != 2) {
                    System.out.println("Avertissement ! ligne \'" + ligne + "\' mal former.");
                    continue;
                }

                AdresseIP adresseIP = new AdresseIP(informationLigne[0]);
                NomMachine nomMachine = new NomMachine(informationLigne[1]);
                DnsItem dnsItem = new DnsItem(adresseIP, nomMachine);

                items.add(dnsItem);

            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addItem(DnsItem item) {
        if(item == null){
            throw new IllegalArgumentException("Erreur: item null.");
        }
        items.add(item);
    } 

    DnsItem getItem(AdresseIP adresse) {
        for(DnsItem item: items){
            if(item.getAdresseIP().equals(adresse))
                return item;
        }
        return null;
    }


    DnsItem getItem(NomMachine nomMachine){
        for(DnsItem item: items){
            if(item.getNomMachine().equals(nomMachine)){
                return item;
            }
        }
        return null;
    }

    List<DnsItem> getItems(String domaine) {
        List<DnsItem> resultat = new ArrayList<DnsItem>();
        for(DnsItem item: items){
            if(item.getNomMachine().getNomMachine().endsWith(domaine)){
                resultat.add(item);
            }            
        }
        return resultat;
    }

    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder();
        for (DnsItem item : items) {
            resultat.append(item).append("\n");
        }
        return resultat.toString();
    }


    public boolean contains(DnsItem item){
        return items.contains(item);
    }

    public int size() {
        return items.size();
    }

    public void chargerDepuisFichier(String cheminFichier){
        if(cheminFichier == null){
            throw new IllegalArgumentException("Erreur: Le nom du fichier est nulle.");
        }
        try{
            List<String> lignes = Files.readAllLines(Path.of(cheminFichier));
            for(String ligne: lignes){
                if(ligne.isBlank()){
                    continue;
                }
                String[] informationLigne = ligne.trim().split("\\s+");
                if(informationLigne.length != 2) {
                    System.out.println("Avertissement ! ligne \'" + ligne + "\' mal former.");
                    continue;
                }

                AdresseIP adresseIP = new AdresseIP(informationLigne[0]);
                NomMachine nomMachine = new NomMachine(informationLigne[1]);
                DnsItem dnsItem = new DnsItem(adresseIP, nomMachine);

                items.add(dnsItem);
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void sauvegarderFichier() {
        if (this.cheminFichier == null) {
            throw new IllegalStateException("Aucun fichier d'origine défini pour la sauvegarde.");
        }

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(cheminFichier))) {
            for (DnsItem item : items) {
                writer.write(item.getAdresseIP().toString() + " " + item.getNomMachine().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde du fichier : " + e.getMessage());
        }
    }

    public void supprimer(DnsItem item){
        items.remove(item);
    }

    public void affiche() {
        for(DnsItem item : items){
            System.out.println(item.toString() + "\n");
        }
    }
    public List<DnsItem> getItems() {
        return new ArrayList<>(items); // retourne toute la table
    }

}