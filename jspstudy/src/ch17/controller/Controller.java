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
		// �� ���ø����̼� ��Ʈ ���
		ServletContext context = config.getServletContext();
		// realFolder�� �� ���ø����̼� �ý��ۻ��� ���� ��η� ���� 
		String realPath = context.getRealPath(realFolder)+ "\\"+ props;
		
		// ��ɾ�� ó�� Ŭ������ ���� ������ ������ Properties ��ü ����
		Properties pr = new Properties();
		FileInputStream f= null;
		
		try {
			// command.properties ������ ������ �о��
			
			f = new FileInputStream(realPath);
			// command.properties�� ������ Properties ��ü pr�� ���� 
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
		// Set ��ü�� iterator() �޼ҵ带 ����� Iterator ��ü�� ��
		Iterator<?> keyIter = pr.keySet().iterator();
		// Iterator ��ü�� ����� ��ɾ�� ó�� Ŭ������ commandMap �� ����
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
	
	// ���������� ��û�� �м��ϰ�, �ش� ������ ó���� �� �� ���� �� 
	// ó������� �信 ����
	
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
