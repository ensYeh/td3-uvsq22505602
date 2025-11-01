package fr.uvsq.cprog.collex;

public final class AdresseIP {
    private final String adresse;

    public AdresseIP(final String adresse) {
        if(adresse == null) {
            throw new IllegalArgumentException("Erreur: Adresse null.");
        }
        String trimedAdresse = adresse.trim();
        String[] valeurs = trimedAdresse.split("\\.");
        if(valeurs.length != 4){
            throw new IllegalArgumentException("Erreur: AdresseIP non valide.");
        }
        for(String valeur: valeurs){
            try {
                int numero = Integer.parseInt(valeur);
                if(numero < 0 || numero > 255){
                    throw new IllegalArgumentException("Erreur: AdresseIP non valide.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Erreur: AdresseIP non valide.");
            }
        }
        this.adresse = trimedAdresse;
    }

    @Override
    public String toString() {
        return adresse;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other){
            return true;
        } else if (!(other instanceof AdresseIP)){
            return false;
        } else {
            AdresseIP nouvelleAdresse = (AdresseIP) other;
            return this.adresse.equals(nouvelleAdresse.adresse);
        }
    }
    
    @Override
    public int hashCode() {
        return this.adresse.hashCode();
    }

    public String getAdresse() {
        return this.adresse;
    }


}
