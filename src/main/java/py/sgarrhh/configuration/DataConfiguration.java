package py.sgarrhh.configuration;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@Profile("dev")
public class DataConfiguration {

//	@Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/rrhh_db");
//        dataSource.setUsername("root");
//        dataSource.setPassword("");
//        return dataSource;
//    }
//	
	@Bean

	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/rrhh_db");
		dataSource.setUsername("postgres");
		dataSource.setPassword("admin");
		return dataSource;
	}
	
	
	/*
	 * @Bean public JpaVendorAdapter jpaVendorAdapter(){ HibernateJpaVendorAdapter
	 * adapter = new HibernateJpaVendorAdapter();
	 * adapter.setDatabase(Database.MYSQL); adapter.setShowSql(true);
	 * adapter.setGenerateDdl(true);
	 * adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
	 * adapter.setPrepareConnection(true); return adapter;
	 * 
	 * }
	 */
}