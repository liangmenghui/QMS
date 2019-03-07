package com.unind;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

//@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class App {
//public class App implements TransactionManagementConfigurer {

//	@Resource(name="txManager1")
//	private PlatformTransactionManager txManager1;
//
//	@Resource(name="txManager2")
//	private PlatformTransactionManager txManager2;
//
//	// 创建事务管理器1
//	@Bean(name = "txManager1")
//	public PlatformTransactionManager txManager(DataSource dataSource) {
//		return new DataSourceTransactionManager(dataSource);
//	}
//
//	// 创建事务管理器2
//	@Bean(name = "txManager2")
//	public PlatformTransactionManager txManager2(EntityManagerFactory factory) {
//		return new JpaTransactionManager(factory);
//	}
//
//	// 实现接口 TransactionManagementConfigurer 方法，其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器
//	@Override
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return txManager1;
//	}

	public static void main(String[] args) {
		try {
			SpringApplication.run(App.class, args);	
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}
}
