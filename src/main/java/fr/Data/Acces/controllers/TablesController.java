package fr.Data.Acces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.Data.Acces.tables.CreateTablesDao;

@Controller
public class TablesController {

	@Autowired
    private  CreateTablesDao createTablesDao;

    @GetMapping("/createTable")
    @ResponseBody
    public String createTable() {
        try {
            createTablesDao.createTableEtudiant();
            createTablesDao.createTableNote();
            return "Les tables 'etudiant et note' ont été créée avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la création de la table : " + e.getMessage();
        }
    }

    @GetMapping("/dropTable")
    @ResponseBody
    public String dropTable() {
        try {
            createTablesDao.dropTableEtudiant();
            return "La table 'etudiant' a été supprimée avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de la suppression de la table : " + e.getMessage();
        }
    }
}
