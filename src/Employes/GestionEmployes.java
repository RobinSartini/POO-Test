package Employes;
import java.util.ArrayList;

public class GestionEmployes {
    private static ArrayList<Employe> listeEmployes = new ArrayList<>();

    public static void main(String[] args) {
        // Ajout des employés
        listeEmployes.add(new Employe("MA0001", "Albert", "Nielson", "Directeur", 2000, 120000));
        listeEmployes.add(new Employe("MA0002", "Malik", "Yoan", "Chef de service", 2001, 55000));
        listeEmployes.add(new Employe("MA0003", "Vincent", "Gogh", "Informaticien", 2002, 40000));
        listeEmployes.add(new Employe("MA0004", "Youri", "Dodo", "Chef de service", 2003, 45000));
        listeEmployes.add(new Employe("MA0005", "Nael", "Miles", "Informaticien", 2004, 45000));
        listeEmployes.add(new Employe("MA0006", "Bolan", "Kiki", "Comptable", 2005, 45000));
        listeEmployes.add(new Employe("MA0007", "Dover", "Louik", "Chef de service", 2006, 45000));
        listeEmployes.add(new Employe("MA0008", "Kliens", "Lybe", "Informaticien", 2007, 35000));
        listeEmployes.add(new Employe("MA0009", "Sika", "Niyle", "Informaticien", 2008, 35000));
        listeEmployes.add(new Employe("MA0010", "Jean", "de Dieu", "Manager", 2009, 60000));
        listeEmployes.add(new Employe("MA0011", "Diane", "Shur", "CTO", 2020, 70000));

        // 1-Affichage de tous les employés
        afficherListeEmployes("Liste de tous les employés", listeEmployes);

        // 2/3-Recherche d'un employé par matricule
        afficherEmployeParMatricule("MA0007");
        afficherEmployeParMatricule("MAAGG7");

        // 4-Affichage des employés ayant commencé en 2008
        afficherListeEmployes("Employés ayant commencé en 2008", getEmployesParAnnee(2008));

        // 5-Calcul de la masse salariale
        System.out.printf("\nMasse salariale totale de l'entreprise : %.2f €\n", calculerMasseSalariale());

        // 6-Affichage des employés les plus anciens
        afficherListeEmployes("Employés les plus anciens", getEmployesPlusAnciens());

        // 7-Affichage du plus petit salaire
        System.out.printf("\nLe plus petit salaire de l'entreprise est : %.2f €\n", getPlusPetitSalaire());

        // 8/9-Affichage du plus petit salaire et augmentation de 550 €
        afficherListeEmployes("Employés avec le plus petit salaire", getEmployesAvecPlusPetitSalaire());
        augmenterPlusPetitSalaire(550);

        // 10-Affichage des informaticiens
        afficherListeEmployes("Liste des Informaticiens", getEmployesParPoste("Informaticien"));

        // 11-Calcul et affichage de la masse salariale des chefs de service
        System.out.printf("\nMasse salariale totale des Chefs de Service : %.2f €\n", calculerMasseSalarialeParPoste("Chef de service"));

        // 12-Comptage et affichage des chefs de service
        System.out.println("\nNombre total de Chefs de Service : " + compterEmployesParPoste("Chef de service"));

        // 13-Affichage des chefs de service
        afficherListeEmployes("Liste des Chefs de Service", getEmployesParPoste("Chef de service"));

        // 14-Affichage des employés ayant plus de 5 ans d'ancienneté et un salaire entre 15000€ et 60000€
        afficherListeEmployes("Employés anciens avec salaire entre 15000€ et 60000€", filtrerEmployes(5, 15000, 60000, 2020));
    }

    //  Méthode pour afficher une liste d'employés
    public static void afficherListeEmployes(String titre, ArrayList<Employe> liste) {
        System.out.println("\n" + titre);
        System.out.printf("%-8s %-10s %-10s %-20s %-6s %s%n", "Matricule", "Nom", "Prénom", "Poste", "Début", "Salaire");
        System.out.println("----------------------------------------------------------------------------");
        if (liste.isEmpty()) {
            System.out.println("Aucun employé trouvé.");
        } else {
            for (Employe e : liste) {
                e.afficherEmploye();
            }
        }
    }

    // Recherche d'un employé par matricule
    public static void afficherEmployeParMatricule(String matricule) {
        Employe employe = rechercherParMatricule(matricule);
        if (employe != null) {
            afficherListeEmployes("Employé trouvé avec matricule " + matricule, new ArrayList<>() {{ add(employe); }});
        } else {
            System.out.println("\nAucun employé trouvé avec le matricule " + matricule);
        }
    }

    public static Employe rechercherParMatricule(String matricule) {
        return listeEmployes.stream().filter(e -> e.getMatricule().equalsIgnoreCase(matricule)).findFirst().orElse(null);
    }

    //  Récupération des employés par année d'embauche
    public static ArrayList<Employe> getEmployesParAnnee(int annee) {
      return new ArrayList<>(listeEmployes.stream()
              .filter(e -> e.getAnneeDebut() == annee)
              .toList());
  }
  
    //  Calcul de la masse salariale
    public static double calculerMasseSalariale() {
        return listeEmployes.stream().mapToDouble(Employe::getSalaire).sum();
    }

    //  Employés les plus anciens
    public static ArrayList<Employe> getEmployesPlusAnciens() {
        int anneeMin = listeEmployes.stream().mapToInt(Employe::getAnneeDebut).min().orElse(0);
        return getEmployesParAnnee(anneeMin);
    }

    //  Employés avec le plus petit salaire
    public static ArrayList<Employe> getEmployesAvecPlusPetitSalaire() {
      double minSalaire = getPlusPetitSalaire();
      return new ArrayList<>(listeEmployes.stream()
              .filter(e -> e.getSalaire() == minSalaire)
              .toList());
  }
  
    // Plus petit salaire
    public static double getPlusPetitSalaire() {
      return listeEmployes.stream().mapToDouble(Employe::getSalaire).min().orElse(0);
  }
  
    // Augmenter les employés ayant le plus petit salaire
    public static void augmenterPlusPetitSalaire(double augmentation) {
      getEmployesAvecPlusPetitSalaire().forEach(e -> {
          e.setSalaire(e.getSalaire() + augmentation);
          System.out.printf("Augmentation de %.2f € pour %s %s (Nouveau salaire: %.2f €)\n",
                  augmentation, e.getPrenom(), e.getNom(), e.getSalaire());
      });
  }
  
    // Récupération des employés par poste
    public static ArrayList<Employe> getEmployesParPoste(String poste) {
      return new ArrayList<>(listeEmployes.stream()
              .filter(e -> e.getPoste().equalsIgnoreCase(poste))
              .toList());
  }
  

    // Calcul de la masse salariale pour un poste donné
    public static double calculerMasseSalarialeParPoste(String poste) {
        return getEmployesParPoste(poste).stream().mapToDouble(Employe::getSalaire).sum();
    }

    // Comptage des employés par poste
    public static int compterEmployesParPoste(String poste) {
        return getEmployesParPoste(poste).size();
    }

    // Filtrer les employés par ancienneté et salaire
    public static ArrayList<Employe> filtrerEmployes(int ancienneteMin, double salaireMin, double salaireMax, int anneeReference) {
      return new ArrayList<>(listeEmployes.stream()
              .filter(e -> (anneeReference - e.getAnneeDebut()) > ancienneteMin)
              .filter(e -> e.getSalaire() >= salaireMin && e.getSalaire() <= salaireMax)
              .toList());
  }
}
