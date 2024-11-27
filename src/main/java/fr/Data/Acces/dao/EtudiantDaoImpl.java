package fr.Data.Acces.dao;
// N°3
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import fr.Data.Acces.model.Etudiant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class EtudiantDaoImpl implements EtudiantDao {

    private DataSource dts;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EtudiantDaoImpl(DataSource dts) {
        this.dts = dts;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EtudiantDaoImpl.class);
    
    @Override
    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        String sql = "INSERT INTO etudiant (nom, prenom, email, telephone, adresse, date_de_naissance, filiere) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dts.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, etudiant.getNom());
            ps.setString(2, etudiant.getPrenom());
            ps.setString(3, etudiant.getEmail());
            ps.setString(4, etudiant.getTelephone());
            ps.setString(5, etudiant.getAdresse());
            ps.setString(6, etudiant.getDateDeNaissance());
            ps.setString(7, etudiant.getFiliere());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return etudiant;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean mettreAJourEtudiant(Etudiant etudiant) {
        String sql = "UPDATE etudiant SET nom = ?, prenom = ?, email = ?, telephone = ?, adresse = ?, date_de_naissance = ?, filiere = ? WHERE id = ?";
        try (Connection conn = dts.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, etudiant.getNom());
            ps.setString(2, etudiant.getPrenom());
            ps.setString(3, etudiant.getEmail());
            ps.setString(4, etudiant.getTelephone());
            ps.setString(5, etudiant.getAdresse());
            ps.setString(6, etudiant.getDateDeNaissance());
            ps.setString(7, etudiant.getFiliere());
            ps.setInt(8, etudiant.getId());
            return ps.executeUpdate() > 0; // Retourne true si une ligne est modifiée
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimerEtudiant(int id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";
        try (Connection conn = dts.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0; // Retourne true si une ligne est supprimée
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Etudiant rechercherEtudiantParId(int id) {
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        try (Connection conn = dts.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToEtudiant(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Etudiant> listerTousLesEtudiants() {
        String sql = "SELECT * FROM etudiant";
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection conn = dts.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                etudiants.add(mapResultSetToEtudiant(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public List<Etudiant> rechercherEtudiantsParNom(String nom) {
        String sql = "SELECT * FROM etudiant WHERE nom LIKE ?";
        List<Etudiant> etudiants = new ArrayList<>();
        try (Connection conn = dts.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nom + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                etudiants.add(mapResultSetToEtudiant(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public boolean existeParEmail(String email) {
        String sql = "SELECT email FROM etudiant WHERE email = ?";
        try (Connection conn = dts.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Retourne true si une ligne existe
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private Etudiant mapResultSetToEtudiant(ResultSet rs) throws Exception {
        Etudiant etudiant = new Etudiant();
        etudiant.setId(rs.getInt("id"));
        etudiant.setNom(rs.getString("nom"));
        etudiant.setPrenom(rs.getString("prenom"));
        etudiant.setEmail(rs.getString("email"));
        etudiant.setTelephone(rs.getString("telephone"));
        etudiant.setAdresse(rs.getString("adresse"));
        etudiant.setDateDeNaissance(rs.getString("date_de_naissance"));
        etudiant.setFiliere(rs.getString("filiere"));
        return etudiant;
    }
    
    /*Cette methode teste " La Classe: PreparedStatementCreator" */
    public void insertEtudiantV2(final Etudiant etudiant) throws SQLException {
        jdbcTemplate = new JdbcTemplate(dts);
        jdbcTemplate.update(new PreparedStatementCreator() {
		    @Override
		    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
		        String sql = "INSERT INTO Etudiant (nom, prenom, email, telephone, adresse, date_de_naissance, filiere) " +
		                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
		        PreparedStatement ps = con.prepareStatement(sql);
		        ps.setString(1, etudiant.getNom());
		        ps.setString(2, etudiant.getPrenom());
		        ps.setString(3, etudiant.getEmail());
		        ps.setString(4, etudiant.getTelephone());
		        ps.setString(5, etudiant.getAdresse());
		        ps.setString(6, etudiant.getDateDeNaissance());
		        ps.setString(7, etudiant.getFiliere());
		        return ps;
		    }
		});
    }

  
    /* SQL Statement et Parametres */
    public void insertEtudiantV3(final Etudiant etudiant) {
        String sql = "INSERT INTO Etudiant (nom, prenom, email, telephone, adresse, date_de_naissance, filiere) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate = new JdbcTemplate(dts);

        jdbcTemplate.update(sql,etudiant.getNom(),etudiant.getPrenom(),etudiant.getEmail(),etudiant.getTelephone(),etudiant.getAdresse(),etudiant.getDateDeNaissance(),etudiant.getFiliere());
    }


    /*  Traitement Inscription par Lot */
    @Override
    public void insertBatchEtudiants(final List<Etudiant> etudiants) {
        String sql = "INSERT INTO Etudiant (nom, prenom, email, telephone, adresse, date_de_naissance, filiere) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate = new JdbcTemplate(dts);

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            @Override
            public int getBatchSize() {
                return etudiants.size(); // Nombre total d'étudiants à insérer
            }

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Etudiant etudiant = etudiants.get(i);

                // Définir les paramètres pour chaque étudiant (sans 'id')
                ps.setString(1, etudiant.getNom());
                ps.setString(2, etudiant.getPrenom());
                ps.setString(3, etudiant.getEmail());
                ps.setString(4, etudiant.getTelephone());
                ps.setString(5, etudiant.getAdresse());
                ps.setString(6, etudiant.getDateDeNaissance());
                ps.setString(7, etudiant.getFiliere());
            }
        });
    }

}
