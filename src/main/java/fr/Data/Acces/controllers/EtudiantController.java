package fr.Data.Acces.controllers;

import fr.Data.Acces.dao.EtudiantDao;
import fr.Data.Acces.model.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantDao etudiantDao;

    // Ajouter un nouvel étudiant
    @PostMapping
    public ResponseEntity<String> ajouterEtudiant(@RequestBody Etudiant etudiant) {
        if (etudiantDao.existeParEmail(etudiant.getEmail())) {
            return new ResponseEntity<>("L'email est déjà utilisé par un autre étudiant.", HttpStatus.BAD_REQUEST);
        }
        Etudiant addedEtudiant = etudiantDao.ajouterEtudiant(etudiant);
        if (addedEtudiant != null) {
            return new ResponseEntity<>("Étudiant ajouté avec succès : " + addedEtudiant.toString(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Erreur lors de l'ajout de l'étudiant.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Post :http://localhost:6700/Data/api/etudiants
    }

    // Récupérer tous les étudiants
    @GetMapping
    public ResponseEntity<List<Etudiant>> listerTousLesEtudiants() {
        List<Etudiant> etudiants = etudiantDao.listerTousLesEtudiants();
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    // Récupérer un étudiant par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> rechercherEtudiantParId(@PathVariable int id) {
        Etudiant etudiant = etudiantDao.rechercherEtudiantParId(id);
        if (etudiant != null) {
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Get :http://localhost:6700/Data/api/etudiants/3
    }

    // Mettre à jour un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<String> mettreAJourEtudiant(@PathVariable int id, @RequestBody Etudiant etudiant) {
        etudiant.setId(id); // Assurez-vous que l'ID de l'étudiant est bien défini
        boolean isUpdated = etudiantDao.mettreAJourEtudiant(etudiant);
        if (isUpdated) {
            return new ResponseEntity<>("Étudiant mis à jour avec succès.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erreur lors de la mise à jour de l'étudiant.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerEtudiant(@PathVariable int id) {
        boolean isDeleted = etudiantDao.supprimerEtudiant(id);
        if (isDeleted) {
            return new ResponseEntity<>("Étudiant supprimé avec succès.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erreur lors de la suppression de l'étudiant.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Delete :http://localhost:6700/Data/api/etudiants/2
    }

    // Rechercher des étudiants par nom
    @GetMapping("/recherche/{nom}")
    public ResponseEntity<List<Etudiant>> rechercherEtudiantsParNom(@PathVariable String nom) {
        List<Etudiant> etudiants = etudiantDao.rechercherEtudiantsParNom(nom);
        if (etudiants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
        // Get :http://localhost:6700/Data/api/etudiants/recherche/Kouadio
    }
}
