package JSPDisign;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import getFSWmessages.LinuxCMD;

/**
 * Servlet implementation class HeadSelectServlet
 */
@WebServlet("/HeadSelect")
public class HeadSelectServlet extends HttpServlet {
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
		Enumeration<String> ParameterName = request.getParameterNames();
		while(ParameterName.hasMoreElements()){
			String name = ParameterName.nextElement();
			if(name.equals("NewConf")){
				request.getRequestDispatcher("NewConf.jsp").forward(request, response);
				break;
			}else if(name.equals("BackIndex")){
				request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
				break;
			}else if(name.equals("RefreshServer")){
				LinuxCMD linuxcmd = new LinuxCMD();
				try {
					linuxcmd.runCommand(LinuxCMD.cmd_reloadxml);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
			}
		}
	}
}
