package kr.or.ddit.config.spring;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

@PropertySource("classpath:kr/or/ddit/config/mybatis/db_dev.properties")
@Configuration
public class ApplicationDatasource_dev {

	@Autowired
	private Environment env;
	
	/*
	 
	  	<bean id="datasource" class="org.apache.commons.dbcp2.BasicDataSource">
			<property name="url" value="${jdbc.url}"/>
			<property name="driverClassName" value="${jdbc.driver}"/>
			<property name="username" value="${jdbc.username}"/>
			<property name="password" value="${jdbc.password}"/>
		</bean>
	  
		<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
			<property name="configLocation" value="classpath:kr/or/ddit/config/mybatis/mybatis-config.xml"/>
			<property name="dataSource" ref="datasource"/>
		</bean>

		<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
			<constructor-arg ref="sqlSessionFactory"/>
		</bean>
	  
	 */
	
	@Bean
	public DataSource datasource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setConfigLocation(new ClassPathResource("kr/or/ddit/config/mybatis/mybatis-config.xml"));
		sfb.setDataSource(datasource());
		return sfb;
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		SqlSessionTemplate sst = new SqlSessionTemplate(sqlSessionFactory().getObject());
		return sst;
	}
}