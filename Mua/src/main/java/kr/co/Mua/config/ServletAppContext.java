package kr.co.Mua.config;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.Mua.Mapper.ChartMapper;
import kr.co.Mua.Mapper.InsertDBMapper;
import kr.co.Mua.Mapper.SearchMapper;
import kr.co.Mua.Mapper.UserMapper;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.interceptor.ChartInterceptor;
import kr.co.Mua.interceptor.CheckLoginInterceptor;
import kr.co.Mua.service.ChartService;

@Configuration
@EnableWebMvc
@ComponentScan("kr.co.Mua.dao")
@ComponentScan("kr.co.Mua.service")
@ComponentScan("kr.co.Mua.controller")
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
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@Autowired
	private ChartService chartService;
	
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
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
		res.setBasenames("/WEB-INF/properties/error_message");
		return res;
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
	public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}																																								
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		
		CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
		
		InterceptorRegistration reg1 = registry.addInterceptor(checkLoginInterceptor);
		reg1.addPathPatterns("/**");
		
		ChartInterceptor chartInterceptor = new ChartInterceptor(chartService);
		InterceptorRegistration reg2 = registry.addInterceptor(chartInterceptor);
		reg2.addPathPatterns("/main", "/chart/top100");
	}
	
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}
	
	@Bean
	public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception {
		MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<UserMapper>(UserMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver(); //��ü �����Ͽ� ��ȯ
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
