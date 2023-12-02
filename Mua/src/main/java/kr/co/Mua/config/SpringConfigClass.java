package kr.co.Mua.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

<<<<<<< HEAD
public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
=======
/*public class SpringConfigClass implements WebApplicationInitializer{
>>>>>>> refs/heads/김진욱

	@Override
<<<<<<< HEAD
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ServletAppContext.class };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootAppContext.class };
	}

	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}

	/*
	 * null : 사용자가 입력한 내용을 임시기억할 아파치톰켓에서 제공하는 서버의 임시기억장소 52428800 : 업로드 데이터의 용량(1024
	 * * 50, 50MB) 524288000 : 파일데이터를 포함한 전체 용량 (500MB) 0 : 파일의 임계값(데이터를 받아서 자동으로
	 * 저장)
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		super.customizeRegistration(registration);

		MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
		registration.setMultipartConfig(config1);
=======
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
		servletAppContext.register(ServletAppContext.class);
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
		ServletRegistration.Dynamic servlet =  servletContext.addServlet("dispatcher", dispatcherServlet);
		
		//�ΰ�����
		servlet.setLoadOnStartup(1); //������� ��û������ �޾Ƶ��̰ڴٴ� �� => ���⼭���� ���
		servlet.addMapping("/");
		
		//=========================================================================================================
		//Bean���� �����Ͽ� ������
		AnnotationConfigWebApplicationContext rootAppContext = new AnnotationConfigWebApplicationContext();
		rootAppContext.register(RootAppContext.class);
		
		ContextLoaderListener listner = new ContextLoaderListener(rootAppContext);
		servletContext.addListener(listner);
		
		//�Ķ���� ���ڵ� ����
		FilterRegistration.Dynamic filter = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		filter.setInitParameter("encoding", "UTF-8");
		filter.addMappingForServletNames(null, false, "dispatcher");
		
	}
	
}*/

public class SpringConfigClass extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	// DispatcherServlet�� ������ ��û �ּҸ� �����Ѵ�.
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
>>>>>>> refs/heads/김진욱
	}

	// Spring MVC ������Ʈ ������ ���� Ŭ������ �����Ѵ�.
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { ServletAppContext.class };
	}

	// ������Ʈ���� ����� Bean���� ���Ǳ� ���� Ŭ������ �����Ѵ�.
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootAppContext.class };
	}

	// �Ķ���� ���ڵ� ���� ����
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}
/* 
 * 		null : 사용자가 입력한 내용을 임시기억할 아파치톰켓에서 제공하는 서버의 임시기억장소
 * 		52428800 : 업로드 데이터의 용량(1024 * 50, 50MB)
 * 		524288000 : 파일데이터를 포함한 전체 용량 (500MB)
 * 		0 : 파일의 임계값(데이터를 받아서 자동으로 저장)
 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		super.customizeRegistration(registration);
		
		MultipartConfigElement config1 = new MultipartConfigElement(null, 52428800, 524288000, 0);
		registration.setMultipartConfig(config1);
	}
	
}
