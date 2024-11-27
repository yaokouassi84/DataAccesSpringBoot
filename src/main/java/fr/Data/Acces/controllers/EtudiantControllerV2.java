package fr.Data.Acces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.Data.Acces.dao.EtudiantDaoImpl;
import fr.Data.Acces.model.Etudiant;

@RestController
@RequestMapping("/api/etudiants/v2")
public class EtudiantControllerV2 {

	@Autowired
	private EtudiantDaoImpl etudiantDAO; // Inject the DAO directly

	// Endpoint to insert a single Etudiant using insertEtudiantV2
	@PostMapping("/v22")
	public String v2(@RequestBody Etudiant etudiant) {
		try {
			etudiantDAO.insertEtudiantV2(etudiant); // Call DAO method directly
			return "Etudiant inserted successfully using V2 method!";
		} catch (Exception e) {
			return "Error inserting Etudiant: " + e.getMessage();
		}
	}

	// Endpoint to insert a single Etudiant using insertEtudiantV3
	@PostMapping("/v3")
	public String insertEtudiantV3(@RequestBody Etudiant etudiant) {
		try {
			etudiantDAO.insertEtudiantV3(etudiant); // Call DAO method directly
			return "Etudiant inserted successfully using V3 method!";
		} catch (Exception e) {
			return "Error inserting Etudiant: " + e.getMessage();
		}
	}

	// Endpoint to insert a batch of Etudiants
	@PostMapping("/batch")
	public String insertBatchEtudiants(@RequestBody List<Etudiant> etudiants) {
		try {
			etudiantDAO.insertBatchEtudiants(etudiants);
			return etudiants.size() + " Etudiants inserted successfully in batch!";
		} catch (Exception e) {
			e.printStackTrace(); // Log complet pour le débogage
			return "Error inserting batch of Etudiants: " + e.getMessage();
		}
	}
 /*
  Post : http://localhost:6700/Data/api/etudiants/v2/batch
  
  [
    {
        "nom": "Poire",
        "prenom": "Rouge",
        "email": "p.rouge@gmail.fr",
        "telephone": "0725624153",
        "adresse": "123 Rue des ok, Paris",
        "dateDeNaissance": "2002-05-12",
        "filiere": "EPS"
    },
    {
        "nom": "augardB",
        "prenom": "Joyce",
        "email": "bg.ouin@gmail.fr",
        "telephone": "0678451234",
        "adresse": "45 Avenue des Champs, Lyon",
        "dateDeNaissance": "1999-09-18",
        "filiere": "Philosophie"
    },
     {  "nom": "Pion",
        "prenom": "Lian",
        "email": "p.l@gmail.fr",
        "telephone": "0678451234",
        "adresse": "45 Avenue des Champs, Lyon",
        "dateDeNaissance": "1999-09-18",
        "filiere": "Philosophie"
    }
]


  */
}
