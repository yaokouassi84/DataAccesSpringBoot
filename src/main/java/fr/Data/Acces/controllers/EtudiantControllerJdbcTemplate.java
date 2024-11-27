package fr.Data.Acces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.Data.Acces.dao.EtudiantDaoJdbcTemplateImpl;
import fr.Data.Acces.model.Etudiant;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantControllerJdbcTemplate {

    @Autowired
    private EtudiantDaoJdbcTemplateImpl edjt; // DAO injection

    @GetMapping("/findByNom/{nom}")
    public ResponseEntity<?> getEtudiantByNom(@PathVariable String nom) {
        try {
            Etudiant etudiant = edjt.findByNom(nom); // Use the injected DAO method
            if (etudiant != null) {
                return ResponseEntity.ok(etudiant);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("Etudiant not found with name: " + nom);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/findByNomAll/{nom}")
    public ResponseEntity<?> getEtudiantsByNom(@PathVariable String nom) {
        try {
            List<Etudiant> etudiants = edjt.findByNomTous(nom); // Appeler la méthode modifiée
            if (!etudiants.isEmpty()) {
                return ResponseEntity.ok(etudiants); // Retourne la liste des étudiants
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                     .body("No students found with the name: " + nom);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error: " + e.getMessage());
        }
    }

}
