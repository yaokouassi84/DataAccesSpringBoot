package fr.Data.Acces.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.Data.Acces.model.Etudiant;

@Service
public class EtudiantServiceNotes {
	
	    @Autowired
	    private EtudiantDaoNotes etudiantDaoN;

	    // Ajouter un étudiant avec ses notes
	    @Transactional
	    public Etudiant ajouterEtudiantAvecNotes(Etudiant etudiant, List<Double> notes, String matiere) {
	        // Ajouter l'étudiant
	        Etudiant addedEtudiant = etudiantDaoN.ajouterEtudiant(etudiant);

	        // Ajouter les notes pour l'étudiant
	        etudiantDaoN.ajouterNotesPourEtudiant(addedEtudiant.getId(), notes, matiere);

	        return addedEtudiant;
	    }

	    // Autres méthodes...
	}


