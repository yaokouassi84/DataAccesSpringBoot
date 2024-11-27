package fr.Data.Acces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.Data.Acces.dao.EtudiantDaoJdbc;
import fr.Data.Acces.model.Etudiant;
import fr.Data.Acces.myExeptions.DataExeptions;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantControllerJdbcT {

	@Autowired
    private EtudiantDaoJdbc etudiantDao;

	@GetMapping("/prenom/{prenom}")
	public ResponseEntity<?> getEtudiantsByPrenom(@PathVariable String prenom) {
	    try {
	        List<Etudiant> etudiants = etudiantDao.findByEtudiant(prenom);
	        return ResponseEntity.ok(etudiants); // Retourner la liste complète en JSON
	    } catch (DataExeptions e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	    }
	}

    
    @GetMapping("/all")
    public ResponseEntity<List<Etudiant>> getAllEtudiants() {
        List<Etudiant> etudiants = etudiantDao.findAll();

        if (etudiants.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204 si aucun étudiant trouvé
        }

        return ResponseEntity.ok(etudiants); // HTTP 200 avec la liste des étudiants
    }
    
    @GetMapping("/filiere/{filiere}")
    public String getFiliereName(@PathVariable String filiere) {
        try {
            return etudiantDao.getFiliere(filiere);
        } catch (Exception e) {
            throw new RuntimeException("Aucun étudiant trouvé avec la filière : " + filiere, e);
        }
    }
    
    @GetMapping("/filieres/{filiere}")
    public List<String> getFiliereNames(@PathVariable String filiere) {
        try {
            return etudiantDao.getFilieres(filiere);
        } catch (Exception e) {
            throw new RuntimeException("Aucun étudiant trouvé avec la filière : " + filiere, e);
        }
    }
    @GetMapping("/count")
    public int getCountOfEtudiants() {
        try {
            return etudiantDao.countAll();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du comptage des étudiants", e);
        }
    }
}

