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

@WebServlet(urlPatterns = { "/ControllerURI", "*.do" }, initParams = {
		@WebInitParam(name = "propertyConfig", value = "commandURI.properties") })
public class ControllerURI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 명령어와 명령어 처리 클래스를 쌍으로 저장
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	public ControllerURI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// Servlet Config란? 하나의 Servlet 초기화에 필요한 정보를 전달하기 위한 Config 객체입니다.

		// initParames 에서 propertyConfig의 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property";

		// 웹 애플리케이션 루트 경로
		ServletContext context = config.getServletContext();

		// realFolder를 웹 애플리케이션 시스템상의 절대경로
		String realPath = context.getRealPath(realFolder) + "\\" + props;

		System.out.println("진짜 주소는" + realPath);

		// 명령어와 처리 클래스의 매핑 정보를 저장할 Properties 객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		try {
			f = new FileInputStream(realPath);
			pr.load(f);
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException e) {
				}
		}

		// Set 객체의 iterator() 메소드를 사용해 Iterator 객체를 얻어냄
		Iterator<?> keyIter = pr.keySet().iterator();
		// Iterator 객체에 저장된 명령어와 처리 클래스를 commandMap 에 저장
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);

			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = null;
		CommandProcess com = null;

		try {
			String command = request.getRequestURI();

			System.out.println("command"+command);
			if (command.indexOf(request.getContextPath()) == 0)
				command= command.substring(request.getContextPath().length());

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
