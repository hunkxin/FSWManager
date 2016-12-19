package JSPDisign;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends GenericServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost((HttpServletRequest)request,(HttpServletResponse)response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		    String username = request.getParameter("username");
		    String userpass = request.getParameter("userpass");
		    //LoginBean loginBean = new LoginBean();
		    boolean b = username.equals(getServletContext().getInitParameter("username"))&&userpass.equals(getServletContext().getInitParameter("password"));
		    String forward;
		    if(b){
		    HttpSession session = (HttpSession)request.getSession(true);
		    session.setAttribute("username",username);
		    forward = "/WEB-INF/Index.jsp";
		    }else{
		    forward = "Login.jsp";
		    }
		    RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		    dispatcher.forward(request,response);
		}
		
//		public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//			doPost(request,response);
//		}

}
