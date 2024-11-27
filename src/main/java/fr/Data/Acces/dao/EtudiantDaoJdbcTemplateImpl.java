package fr.Data.Acces.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import fr.Data.Acces.model.Etudiant;

public class EtudiantDaoJdbcTemplateImpl implements EtudiantDao {

	private DataSource dts;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	
	public EtudiantDaoJdbcTemplateImpl(DataSource dts) {
		
		this.dts = dts;
		
	}

	@Override
	public Etudiant ajouterEtudiant(Etudiant etudiant) {
		// TODO Auto-generated method stub
		return null;
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

	@SuppressWarnings("deprecation")
	public Etudiant findByNom(String nom) {
	    String sql = "SELECT * FROM ETUDIANT WHERE nom = ?";
	    jdbcTemplate = new JdbcTemplate(dts);
	    
	    final Etudiant etu = new Etudiant(); // Préparer un objet Etudiant pour stocker les résultats
	    
	    jdbcTemplate.query(sql, new Object[]{nom}, new RowCallbackHandler() {
	        @Override
	        public void processRow(ResultSet rs) throws SQLException {
	            // Mapper les colonnes de la table ETUDIANT aux champs de l'objet Etudiant
	            etu.setId(rs.getInt("id"));
	            etu.setNom(rs.getString("nom"));
	            etu.setPrenom(rs.getString("prenom"));
	            etu.setEmail(rs.getString("email"));
	            etu.setTelephone(rs.getString("telephone"));
	            etu.setAdresse(rs.getString("adresse"));
	            etu.setDateDeNaissance(rs.getString("date_de_naissance"));
	            etu.setFiliere(rs.getString("filiere"));
	        }
	    });
	    
	    // Retourne l'objet Etudiant rempli ou null s'il n'a pas été trouvé
	    return etu.getNom() == null ? null : etu;
	}

	@SuppressWarnings("deprecation")
	public Etudiant findByNomV2(String nom) {
	    String sql = "SELECT * FROM ETUDIANT WHERE nom = ?";
	    jdbcTemplate = new JdbcTemplate(dts);
	    
	    return jdbcTemplate.queryForObject(sql, new Object[]{nom}, (rs, rowNum) -> {
	        final Etudiant etudiant = new Etudiant();
	        etudiant.setId(rs.getInt("id"));
	        etudiant.setNom(rs.getString("nom"));
	        etudiant.setPrenom(rs.getString("prenom"));
	        etudiant.setEmail(rs.getString("email"));
	        etudiant.setTelephone(rs.getString("telephone"));
	        etudiant.setAdresse(rs.getString("adresse"));
	        etudiant.setDateDeNaissance(rs.getString("date_de_naissance"));
	        etudiant.setFiliere(rs.getString("filiere"));
	        return etudiant;
	    });
	}
	
	@SuppressWarnings("deprecation")
	public List<Etudiant> findByNomTous(String nom) {
	    // Vérification du paramètre d'entrée
	    if (nom == null || nom.trim().isEmpty()) {
	        throw new IllegalArgumentException("Le paramètre 'nom' ne peut pas être nul ou vide.");
	    }

	    String sql = "SELECT * FROM ETUDIANT WHERE nom = ?";

	    try {
	        // Exécution de la requête avec le mapping des résultats
	        return jdbcTemplate.query(sql, new Object[]{nom}, (rs, rowNum) -> {
	            Etudiant etudiant = new Etudiant();
	            etudiant.setId(rs.getInt("id"));
	            etudiant.setNom(rs.getString("nom"));
	            etudiant.setPrenom(rs.getString("prenom"));
	            etudiant.setEmail(rs.getString("email"));
	            etudiant.setTelephone(rs.getString("telephone"));
	            etudiant.setAdresse(rs.getString("adresse"));
	            etudiant.setDateDeNaissance(rs.getString("date_De_Naissance"));
	            etudiant.setFiliere(rs.getString("filiere"));
	            return etudiant;
	        });
	    } catch (Exception e) {
	        // Journalisation de l'erreur
	        System.err.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
	        e.printStackTrace();

	        // Retourner une liste vide en cas d'échec
	        return new ArrayList<>();
	    }
	}

}
