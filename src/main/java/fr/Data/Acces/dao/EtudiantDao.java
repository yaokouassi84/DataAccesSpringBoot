package fr.Data.Acces.dao;
 // N°2
import fr.Data.Acces.model.Etudiant;
import java.util.List;

public interface EtudiantDao {

   
    public Etudiant ajouterEtudiant(Etudiant etudiant);

    
    public boolean mettreAJourEtudiant(Etudiant etudiant);

    
    public boolean supprimerEtudiant(int id);

    public Etudiant rechercherEtudiantParId(int id);

    
    public List<Etudiant> listerTousLesEtudiants();

    
   public  List<Etudiant> rechercherEtudiantsParNom(String nom);

    
    public boolean existeParEmail(String email);
    
    public void insertBatchEtudiants(List<Etudiant> etudiants);
}
