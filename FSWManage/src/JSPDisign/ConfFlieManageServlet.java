package JSPDisign;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Hunk.xmlparse.jdom.FileOutput;


/**
 * Servlet implementation class ConfFlieManageServlet
 */
@WebServlet("/FileManage")
public class ConfFlieManageServlet extends HttpServlet {
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
			String cmd = ParameterName.nextElement();
			if(cmd.equals("filemanage")){
				FileOutput conffilenames = new FileOutput();
				request.setAttribute("allconffilename", conffilenames.getconffilename());
				request.getRequestDispatcher("FileManage.jsp").forward(request, response);
				break;
			}else if(cmd.equals("backindex")){
				request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
				break;
			}else if(cmd.equals("deleteselected")){
				FileOutput conffilenames = new FileOutput();
				String allselectedname = request.getParameter("allselectedname");
				request.setAttribute("allconffilename",conffilenames.deleteconffile(allselectedname));
				request.getRequestDispatcher("FileManage.jsp").forward(request, response);
				break;
			}else if(cmd.length()>10){
				if(cmd.substring(0,10).equals("setdefault")){
					FileOutput conffile = new FileOutput();
					String sPath = conffile.getbackupsxmlconfpath()
							+conffile.getconffile().substring(0, conffile.getconffile().lastIndexOf("."))
							+"_"
							+cmd.substring(10)
							+".xml";
					conffile.setdefault(sPath);
					request.setAttribute("allconffilename", conffile.getconffilename());
					request.getRequestDispatcher("FileManage.jsp").forward(request, response);
					break;
				}
			}
		}
		
	}

}
