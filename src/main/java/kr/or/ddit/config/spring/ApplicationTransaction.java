package kr.or.ddit.config.spring;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class ApplicationTransaction {

	@Resource(name = "datasource")
	private DataSource datasource;
	/*
		<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
			<property name="dataSource" ref="datasource"></property>
		</bean>
		
		<tx:advice id="txAdvice" transaction-manager="transactionManager">
			<tx:attributes>
				<tx:method name="*" propagation="REQUIRED"/>
			</tx:attributes>
		</tx:advice>
		
		<aop:config>
			<aop:advisor advice-ref="txAdvice" pointcut="execution(* kr.or.ddit..service.*.*(..))"/>
		</aop:config>
	*/
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(datasource);
		return dstm;
	}
	
	
	
}
