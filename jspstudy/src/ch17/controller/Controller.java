package ch17.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(
		urlPatterns = { "/Controller" }, 
		initParams = { 
				@WebInitParam(name = "propertyConfig", value = "command.properties")
		})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> commandMap = new HashMap<String, Object>();
       
	
  
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property"; 
		// 웹 애플리케이션 루트 경로
		ServletContext context = config.getServletContext();
		// realFolder를 웹 애플리케이션 시스템상의 절대 경로로 변경 
		String realPath = context.getRealPath(realFolder)+ "\\"+ props;
		
		// 명령어와 처리 클래스의 매핑 정보를 저장할 Properties 객체 생성
		Properties pr = new Properties();
		FileInputStream f= null;
		
		try {
			// command.properties 파일의 내용을 읽어옴
			
			f = new FileInputStream(realPath);
			// command.properties의 내용을 Properties 객체 pr에 저장 
			pr.load(f);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(f!=null)
				try {
					f.close();
				} catch (Exception ex) {
					// TODO: handle exception
				}
		}
		// Set 객체의 iterator() 메소드를 사용해 Iterator 객체를 얻어냄
		Iterator<?> keyIter = pr.keySet().iterator();
		// Iterator 객체에 저장된 명령어와 처리 클래스를 commandMap 에 저장
		while(keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
				
			} catch (Exception e) {
				// TODO: handle exception
			} 
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		requestPro(request, response);
	}
	
	// 웹브라우저의 요청을 분석하고, 해당 로직의 처리를 할 모델 실행 및 
	// 처리결과를 뷰에 보냄
	
	private void requestPro(
			HttpServletRequest request, HttpServletResponse response
			) throws ServletException, IOException{
		String view = null;
		CommandProcess com = null;
		
		try {
			String command = request.getParameter("command");
			com = (CommandProcess) commandMap.get(command);
			view = com.requestPro(request, response);
		} catch (Throwable e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}

}
