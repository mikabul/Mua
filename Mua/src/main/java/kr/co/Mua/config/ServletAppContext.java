package kr.co.Mua.config;

import java.util.Properties;

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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.Mua.Mapper.AdminMapper;
import kr.co.Mua.Mapper.ChartMapper;
import kr.co.Mua.Mapper.InsertDBMapper;
import kr.co.Mua.Mapper.SearchMapper;
import kr.co.Mua.Mapper.SuggestMapper;
import kr.co.Mua.Mapper.UserMapper;
import kr.co.Mua.bean.AdminDto;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.interceptor.AcceptAdminInterceptor;
import kr.co.Mua.interceptor.ChartInterceptor;
import kr.co.Mua.interceptor.CheckLoginInterceptor;
<<<<<<< HEAD
import kr.co.Mua.interceptor.NewChartInterceptor;
=======
import kr.co.Mua.interceptor.GenreChartInterceptor;
import kr.co.Mua.interceptor.NewChartInterceptor;
import kr.co.Mua.interceptor.Top100ChartInterceptor;
>>>>>>> refs/remotes/origin/이규순
import kr.co.Mua.service.ChartService;

@Configuration
@EnableWebMvc
@ComponentScan("kr.co.Mua.dao")
@ComponentScan("kr.co.Mua.service")
@ComponentScan("kr.co.Mua.controller")
@ComponentScan("kr.co.Mua.Social")
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
	
	@Resource(name = "loginAdminDto")
	private AdminDto loginAdminDto;
	
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
		reg1.excludePathPatterns("/admin/**");
		
		ChartInterceptor chartInterceptor = new ChartInterceptor(chartService);
		InterceptorRegistration reg2 = registry.addInterceptor(chartInterceptor);
		reg2.addPathPatterns("/main");
		
		AcceptAdminInterceptor acceptAdminInterceptor = new AcceptAdminInterceptor(loginAdminDto);
		InterceptorRegistration reg3 = registry.addInterceptor(acceptAdminInterceptor);
		reg3.addPathPatterns("/admin/**");
		reg3.excludePathPatterns("/admin/login", "/admin/login_pro", "/admin/login_fail");
		
		//============ 어드민 잘못된 접근 ============
		AcceptAdminInterceptor acceptAdminInterceptor = new AcceptAdminInterceptor(loginAdminDto);
		InterceptorRegistration reg3 = registry.addInterceptor(acceptAdminInterceptor);
		reg3.addPathPatterns("/admin/**");
		reg3.excludePathPatterns("/admin/login", "/admin/login_pro", "/admin/login_fail");
		
		NewChartInterceptor newchartInterceptor = new NewChartInterceptor(chartService);
		InterceptorRegistration reg4 = registry.addInterceptor(newchartInterceptor);
		reg4.addPathPatterns("/chart/newchart");
		
		Top100ChartInterceptor top100chartInterceptor = new Top100ChartInterceptor(chartService);
		InterceptorRegistration reg6 = registry.addInterceptor(top100chartInterceptor);
		reg2.addPathPatterns("/chart/top100");
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
		return new StandardServletMultipartResolver(); //占쏙옙체 占쏙옙占쏙옙占싹울옙 占쏙옙환
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
	
	@Bean
	public MapperFactoryBean<AdminMapper> getAdminMapper(SqlSessionFactory factory){
		MapperFactoryBean<AdminMapper> factoryBean = new MapperFactoryBean<AdminMapper>(AdminMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
	@Bean
	public MapperFactoryBean<SuggestMapper> getSuggestMapper(SqlSessionFactory factory){
		MapperFactoryBean<SuggestMapper> factoryBean = new MapperFactoryBean<SuggestMapper>(SuggestMapper.class);
		factoryBean.setSqlSessionFactory(factory);
		return factoryBean;
	}
	
    @Bean("mailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("dma0501011@gmail.com");
        mailSender.setPassword("rrcz edmq hlci qrgl");
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties());

        return mailSender;
    }
	
    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        properties.put("mail.smtp.connectiontimeout", 5000);
        properties.put("mail.smtp.timeout", 5000);
        properties.put("mail.smtp.writetimeout", 5000);

        return properties;
    }
}
