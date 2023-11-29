package kr.co.Mua.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.Mua.Mapper.ChartMapper;
import kr.co.Mua.Mapper.InsertDBMapper;
import kr.co.Mua.Mapper.SearchMapper;

@Configuration
@EnableWebMvc
@ComponentScan("kr.co.Mua.controller")
@ComponentScan("kr.co.Mua.service")
@ComponentScan("kr.co.Mua.dao")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppContext implements WebMvcConfigurer{
	
	@Value("${db.classname}")
	private String db_classname;
	@Value("${db.url}")
	private String db_url;
	@Value("${db.username}")
	private String db_username;
	@Value("${db.password}")
	private String db_password;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/",".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		
		return source;
	}
	
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}
	
	@Bean
	public MapperFactoryBean<InsertDBMapper> getInsertDBMapper(SqlSessionFactory factory){
		MapperFactoryBean<InsertDBMapper> factoryBean = new MapperFactoryBean<InsertDBMapper>(InsertDBMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<ChartMapper> getChartMapper(SqlSessionFactory factory){
		MapperFactoryBean<ChartMapper> factoryBean = new MapperFactoryBean<ChartMapper>(ChartMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<SearchMapper> getSearchMapper(SqlSessionFactory factory){
		MapperFactoryBean<SearchMapper> factoryBean = new MapperFactoryBean<SearchMapper>(SearchMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
}
