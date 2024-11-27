package fr.Data.Acces.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import fr.Data.Acces.model.Etudiant;
import fr.Data.Acces.myExeptions.DataExeptions;

public class EtudiantDaoAutreJdbcImpl implements EtudiantDaoJdbc {

	@Autowired
	private DataSource dts;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public EtudiantDaoAutreJdbcImpl(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		
	}		

	@Override
	public List<Etudiant> findByEtudiant(String prenom) {
	    String sql = "SELECT * FROM ETUDIANT WHERE prenom = ?";
	    List<Etudiant> results = jdbcTemplate.query(
	        sql,
	        new Object[]{prenom},
	        new BeanPropertyRowMapper<>(Etudiant.class)
	    );

	    if (results.isEmpty()) {
	        throw new DataExeptions("Aucun étudiant trouvé avec le prénom : " + prenom);
	    }

	    return results; // Retourner la liste complète
	}


	@Override
	public List<Etudiant> findAll() {
		String sql = "SELECT * FROM ETUDIANT";
		jdbcTemplate = new JdbcTemplate(dts);
		List<Etudiant> ets = new ArrayList<Etudiant>();
		List<Map<String,Object>>rows = jdbcTemplate.queryForList(sql);
		for(Map<String,Object> row : rows) {
			Etudiant etudiant = new Etudiant();
	        
	        etudiant.setId((Integer) row.get("id")); // Mapping des autres champs
	        etudiant.setNom((String) row.get("nom"));
	        etudiant.setPrenom((String) row.get("prenom"));
	        etudiant.setEmail((String) row.get("email"));
	        etudiant.setTelephone((String) row.get("telephone"));
	        etudiant.setAdresse((String) row.get("adresse"));

	        // Conversion de la date
	        java.sql.Date sqlDate = (java.sql.Date) row.get("date_de_naissance");
	        if (sqlDate != null) {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format désiré
	            etudiant.setDateDeNaissance(dateFormat.format(sqlDate));
	        }

	        etudiant.setFiliere((String) row.get("filiere"));
	        ets.add(etudiant);
	    }
	    return ets;
	}

	@Override
	public String getFiliere(String filiere) {
		String sql = "SELECT nom FROM ETUDIANT WHERE filiere = ?";
		jdbcTemplate = new JdbcTemplate(dts);
		
		return jdbcTemplate.queryForObject(sql, String.class,filiere);
	}

	// Recuperer tous les nom des etudiants qui font la meme filiere
	@Override
	public List<String> getFilieres(String filiere) {
		String sql = "SELECT nom FROM ETUDIANT WHERE filiere = ?";
	    jdbcTemplate = new JdbcTemplate(dts);

	    return jdbcTemplate.queryForList(sql, String.class, filiere);
	}

	@Override
	public int countAll() {
		String sql = "SELECT count(*) FROM ETUDIANT";
		jdbcTemplate = new JdbcTemplate(dts);
		
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public Etudiant insertEtu(Etudiant etu) {
		// TODO Auto-generated method stub
		return null;
	}

}
