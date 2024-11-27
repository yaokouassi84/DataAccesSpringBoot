package fr.Data.Acces.dao;

import java.util.List;

import fr.Data.Acces.model.Etudiant;

public interface EtudiantDaoJdbc {

	public Etudiant insertEtu(Etudiant etu);
	public List<Etudiant> findByEtudiant(String prenom);
	public List<Etudiant> findAll();
	public String getFiliere(String filiere);
	public List<String> getFilieres(String filiere);
	public int countAll();
	
}
