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

	// ��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	public ControllerURI() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// Servlet Config��? �ϳ��� Servlet �ʱ�ȭ�� �ʿ��� ������ �����ϱ� ���� Config ��ü�Դϴ�.

		// initParames ���� propertyConfig�� ���� �о��
		String props = config.getInitParameter("propertyConfig");
		String realFolder = "/property";

		// �� ���ø����̼� ��Ʈ ���
		ServletContext context = config.getServletContext();

		// realFolder�� �� ���ø����̼� �ý��ۻ��� ������
		String realPath = context.getRealPath(realFolder) + "\\" + props;

		System.out.println("��¥ �ּҴ�" + realPath);

		// ��ɾ�� ó�� Ŭ������ ���� ������ ������ Properties ��ü ����
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

		// Set ��ü�� iterator() �޼ҵ带 ����� Iterator ��ü�� ��
		Iterator<?> keyIter = pr.keySet().iterator();
		// Iterator ��ü�� ����� ��ɾ�� ó�� Ŭ������ commandMap �� ����
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
