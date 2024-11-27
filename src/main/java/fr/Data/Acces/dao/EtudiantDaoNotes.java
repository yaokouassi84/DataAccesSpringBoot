package fr.Data.Acces.dao;


import fr.Data.Acces.model.Etudiant;
import java.util.List;

public interface EtudiantDaoNotes {

    // Ajout d'un étudiant
    public Etudiant ajouterEtudiant(Etudiant etudiant);

    // Mise à jour d'un étudiant
    public boolean mettreAJourEtudiant(Etudiant etudiant);

    // Suppression d'un étudiant
    public boolean supprimerEtudiant(int id);

    // Recherche d'un étudiant par son ID
    public Etudiant rechercherEtudiantParId(int id);

    // Liste de tous les étudiants
    public List<Etudiant> listerTousLesEtudiants();

    // Recherche d'étudiants par nom
    public List<Etudiant> rechercherEtudiantsParNom(String nom);

    // Vérification de l'existence d'un étudiant par email
    public boolean existeParEmail(String email);

    // Insertion en batch des étudiants
    public void insertBatchEtudiants(List<Etudiant> etudiants);

    // Ajouter des notes pour un étudiant
    public void ajouterNotesPourEtudiant(int etudiantId, List<Double> notes, String matiere);
}

