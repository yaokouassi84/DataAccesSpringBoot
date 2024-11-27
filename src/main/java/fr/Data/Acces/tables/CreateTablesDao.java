package fr.Data.Acces.tables;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

public class CreateTablesDao {

    private DataSource dataSource;

    public CreateTablesDao(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    // Création de la table Etudiant
    public void createTableEtudiant() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS etudiant (
                id INT AUTO_INCREMENT PRIMARY KEY,
                nom VARCHAR(100) NOT NULL,
                prenom VARCHAR(100) NOT NULL,
                email VARCHAR(100) NOT NULL UNIQUE,
                telephone VARCHAR(15) NOT NULL,
                adresse VARCHAR(255),
                date_de_naissance DATE,
                filiere VARCHAR(100)
            ) ENGINE=InnoDB;
        """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'etudiant' créée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Création de la table Note
    public void createTableNote() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS note (
                id INT AUTO_INCREMENT PRIMARY KEY,
                etudiant_id INT NOT NULL,
                matiere VARCHAR(100) NOT NULL,
                valeur DOUBLE NOT NULL,
                FOREIGN KEY (etudiant_id) REFERENCES etudiant(id) ON DELETE CASCADE
            ) ENGINE=InnoDB;
        """;

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'note' créée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Suppression de la table Etudiant
    public void dropTableEtudiant() {
        String dropTableSQL = "DROP TABLE IF EXISTS etudiant;";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(dropTableSQL);
            System.out.println("Table 'etudiant' supprimée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Suppression de la table Note
    public void dropTableNote() {
        String dropTableSQL = "DROP TABLE IF EXISTS note;";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(dropTableSQL);
            System.out.println("Table 'note' supprimée avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
