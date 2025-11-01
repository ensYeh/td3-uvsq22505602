package fr.uvsq.cprog.collex;

public final class NomMachine {
    private final String nomMachine;

    public NomMachine(String nomMachine) {
        if (nomMachine == null || nomMachine.trim().isEmpty()) {
            throw new IllegalArgumentException("Erreur: nom de machine vide ou null.");
        }

        String trimmed = nomMachine.trim().toLowerCase();

        String[] parties = trimmed.split("\\.");

        if (parties.length != 3) {
            throw new IllegalArgumentException("Erreur: nom de machine invalide (doit être de la forme nom.qualifie.machine).");
        }

        for (String p : parties) {
            if (p.isEmpty()) {
                throw new IllegalArgumentException("Erreur: chaque partie du nom doit contenir au moins un caractère.");
            }
    }

    this.nomMachine = trimmed;
}
    

    @Override
    public boolean equals(Object other){
        if(this == other){
            return true;
        } else if(!(other instanceof NomMachine)){
            return false;
        } else {
            NomMachine nouveauNomMachine = (NomMachine) other;
            return nouveauNomMachine.nomMachine.equals(this.nomMachine);
        }
    }

    @Override
    public String toString() {
        return this.nomMachine;
    }

    @Override
    public int hashCode() {
        return nomMachine.hashCode();
    }

    public String getNomMachine() {
        return this.nomMachine;
    }
}