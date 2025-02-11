package Employes;

public class Employe {
    private String matricule;
    private String nom;
    private String prenom;
    private String poste;
    private int anneeDebut;
    private double salaire;

    // Constructeur
    public Employe(String matricule, String nom, String prenom, String poste, int anneeDebut, double salaire) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.anneeDebut = anneeDebut;
        this.salaire = salaire;
    }

    // Getters
    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPoste() {
        return poste;
    }

    public int getAnneeDebut() {
        return anneeDebut;
    }

    public double getSalaire() {
        return salaire;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setAnneeDebut(int anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public void setSalaire(double salaire) {
        if (salaire >= 0) {
            this.salaire = salaire;
        } else {
            System.out.println("Erreur : Le salaire ne peut pas être négatif !");
        }
    }

    // Méthode pour afficher les informations de l'employé
    public void afficherEmploye() {
        System.out.printf("%-8s %-10s %-10s %-20s %-6d %.2f%n", matricule, nom, prenom, poste, anneeDebut, salaire);
    }
}
