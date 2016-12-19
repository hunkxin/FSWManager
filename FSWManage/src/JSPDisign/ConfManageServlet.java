package JSPDisign;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Hunk.xmlparse.jdom.ParseCF;

/**
 * Servlet implementation class ConfManageServlet
 */
@WebServlet("/ConfManageIndex")
public class ConfManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		ParseCF parsecf = new ParseCF();
		List<String> allconf = parsecf.Parse();
		request.setAttribute("allconf", allconf);
		request.getRequestDispatcher("ConfManage.jsp").forward(request, response);
	}

}
