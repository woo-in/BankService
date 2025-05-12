package config;



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration; 


@Configuration 
@ComponentScan(basePackages= {"service" , "UI" , "account" , "dao"})
public class AppCtx{ }










//
//xception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'consoleUI' available
//at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBeanDefinition(DefaultListableBeanFactory.java:687)
//at org.springframework.beans.factory.support.AbstractBeanFactory.getMergedLocalBeanDefinition(AbstractBeanFactory.java:1205)
//at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:292)
//at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:205)
//at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1091)
//at main.Main.main(Main.java:22)