package fr.Data.Acces.controllers;


import fr.Data.Acces.dao.EtudiantServiceNotes;
import fr.Data.Acces.model.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantControllerNotes {

    @Autowired
    private EtudiantServiceNotes etudiantService;

    // Ajouter un étudiant avec ses notes
    @PostMapping("/ajouter")
    public Etudiant ajouterEtudiant(@RequestBody Etudiant etudiant, @RequestParam List<Double> notes, @RequestParam String matiere) {
        return etudiantService.ajouterEtudiantAvecNotes(etudiant, notes, matiere);
    }

    // Autres méthodes...
}
