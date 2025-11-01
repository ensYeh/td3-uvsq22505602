package fr.uvsq.cprog.collex;

import java.util.Objects; // pour la redifinition de la methode hashCode();

public final class DnsItem {
    private final AdresseIP adresseIP;
    private final NomMachine nomMachine;

    public DnsItem(AdresseIP adresseIP, NomMachine nomMachine){
        if(adresseIP == null || nomMachine == null) {
            throw new IllegalArgumentException("Erreur: l'un des deux attributs est null.");
        }

        this.adresseIP = adresseIP;
        this.nomMachine = nomMachine;
    }

    @Override 
    public String toString()
    {
        return this.adresseIP.toString() + " " + this.nomMachine.toString();
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        } else if(!(other instanceof DnsItem)){
            return false;
        } else {
            DnsItem nouveauDnsItem = (DnsItem) other;
            return nouveauDnsItem.adresseIP.equals(this.adresseIP) && nouveauDnsItem.nomMachine.equals(this.nomMachine);
        }
    }

    @Override 
    public int hashCode() {
        return Objects.hash(adresseIP, nomMachine);
    }

    public AdresseIP getAdresseIP() {
        return this.adresseIP;
    }

    public NomMachine getNomMachine() {
        return this.nomMachine;
    }
}