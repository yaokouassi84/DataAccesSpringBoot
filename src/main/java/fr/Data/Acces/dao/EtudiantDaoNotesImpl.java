package fr.Data.Acces.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.Data.Acces.model.Etudiant;

@Repository
public class EtudiantDaoNotesImpl implements EtudiantDaoNotes {

	@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        try {
            String sql = "INSERT INTO etudiant (nom, prenom, email, telephone, adresse, date_de_naissance, filiere) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, etudiant.getNom(), etudiant.getPrenom(), etudiant.getEmail(), etudiant.getTelephone(),
                    etudiant.getAdresse(), etudiant.getDateDeNaissance(), etudiant.getFiliere());

            // Récupérer l'ID de l'étudiant qui a été inséré
            String selectSql = "SELECT id FROM etudiant WHERE email = ?";
            int etudiantId = jdbcTemplate.queryForObject(selectSql, new Object[]{etudiant.getEmail()}, Integer.class);

            etudiant.setId(etudiantId);
            return etudiant;

        } catch (Exception e) {
            // Loggez l'exception ou effectuez un traitement supplémentaire
            throw new RuntimeException("Erreur lors de l'ajout de l'étudiant", e);
        }
    }

    @Override
    @Transactional
    public void ajouterNotesPourEtudiant(int etudiantId, List<Double> notes, String matiere) {
        try {
            String sql = "INSERT INTO note (etudiant_id, matiere, valeur) VALUES (?, ?, ?)";

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, etudiantId);  // set etudiant_id
                    ps.setString(2, matiere);   // set matiere
                    ps.setDouble(3, notes.get(i));  // set valeur
                }

                @Override
                public int getBatchSize() {
                    return notes.size();  // nombre de notes à insérer
                }
            });
        } catch (Exception e) {
            // Loggez l'exception ou effectuez un traitement supplémentaire
            throw new RuntimeException("Erreur lors de l'ajout des notes", e);
        }
    }
	@Override
	public boolean mettreAJourEtudiant(Etudiant etudiant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimerEtudiant(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Etudiant rechercherEtudiantParId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etudiant> listerTousLesEtudiants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etudiant> rechercherEtudiantsParNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existeParEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertBatchEtudiants(List<Etudiant> etudiants) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterNotesPourEtudiant(int etudiantId, List<Double> notes, String matiere) {
		 String sql = "INSERT INTO note (etudiant_id, matiere, valeur) VALUES (?, ?, ?)";

	        // Insérer chaque note pour l'étudiant dans la base de données
	        for (Double note : notes) {
	            jdbcTemplate.update(sql, etudiantId, matiere, note);
	        }

	}

}
