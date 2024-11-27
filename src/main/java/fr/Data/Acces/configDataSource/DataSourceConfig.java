package fr.Data.Acces.configDataSource;
// N°4
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import fr.Data.Acces.dao.EtudiantDao;
import fr.Data.Acces.dao.EtudiantDaoAutreJdbcImpl;
import fr.Data.Acces.dao.EtudiantDaoImpl;
import fr.Data.Acces.dao.EtudiantDaoJdbcTemplateImpl;
import fr.Data.Acces.tables.CreateTablesDao;

@Configuration
public class DataSourceConfig {

	@Bean
	public EtudiantDaoAutreJdbcImpl etdajt() {
		return new EtudiantDaoAutreJdbcImpl(jdbcTemplate());
	}
	
	@Bean
	public EtudiantDao etudiantDao() {
		
		return new EtudiantDaoImpl(dtSource());
	}
	@Bean
	public CreateTablesDao createTables() {
		return new CreateTablesDao(dtSource());
	}
	
	@Bean
	public EtudiantDaoJdbcTemplateImpl edjt() {
		return new EtudiantDaoJdbcTemplateImpl(dtSource());
	}
	
	@Bean
	public  DataSource dtSource() {
		DriverManagerDataSource dt = new DriverManagerDataSource();
		dt.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dt.setUrl("jdbc:mysql://localhost:3306/dataacces");
		dt.setUsername("root");
		dt.setPassword("");
		return dt;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dtSource());
	}
	
	
	
	/*@Bean
	public  DataSource dtSourceBasic() {
		BasicDataSource dtb = new BasicDataSource();
		dtb.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dtb.setUrl("jdbc:mysql://localhost:3306/dataacces");
		dtb.setUsername("root");
		dtb.setPassword("");
		dtb.setInitialSize(2);
		dtb.setMaxTotal(5);
		return dtb;
	}
*/
	
}
