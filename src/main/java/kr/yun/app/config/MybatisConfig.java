package kr.yun.app.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(value = "kr.yun.app.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class MybatisConfig {

	@Bean(name = "DataSource")
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource DataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
	@Bean(name = "SqlSessionFactory")
	@Primary
	public SqlSessionFactory SqlSessionFactory(@Qualifier("DataSource")DataSource DataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(DataSource);
//		sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/sql/*.xml"));
		return sessionFactoryBean.getObject();
	}
	
	
	
//	@Bean
//	public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) throws Exception {		
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
	
	
	
}
