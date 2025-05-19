package config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import aspect.TransactionLogAspect;
import dao.BankAccountDao;
import org.apache.tomcat.jdbc.pool.DataSource;


@Configuration 
@EnableAspectJAutoProxy
@ComponentScan(basePackages= {"service" , "UI" , "account" , "dao"})
public class AppCtx{ 
	
	
	
	// dataSource 빈 등록 
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		
		DataSource ds = new DataSource();
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		ds.setUrl("jdbc:mariadb://localhost:3306/BankSystem");
		ds.setUsername("root"); 
		ds.setPassword("0205"); 
		
		// 10 개로 시작 , 최대 활성 갯수는 10000 
		ds.setInitialSize(10); 
		ds.setMaxActive(10000); 
	
		return ds; 
	}
	
	// 데이터 베이스 빈 등록 
	@Bean 
	public BankAccountDao bankAccountDao() {
		return new BankAccountDao(dataSource()); 
	}
	
	// 로그 데이터를 위한 AOP 빈 등록 
	@Bean
	public TransactionLogAspect transactionLogAspect() {
		return new TransactionLogAspect(); 
	}
	
	
	
}










//
//xception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'consoleUI' available
//at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:687)
//at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1205)
//at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:292)
//at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:205)
//at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1091)
//at main.Main.main(Main.java:22)