package JSPDisign;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Hunk.xmlparse.jdom.ConfPara_Layout;
import Hunk.xmlparse.jdom.ConfParameters;
import Hunk.xmlparse.jdom.ParseCF;

/**
 * Servlet implementation class ModifyConfServlet
 */
@WebServlet("/ModifyConf")
public class ModifyConfServlet extends HttpServlet {
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
			String para = ParameterName.nextElement();
			if(para.length()>11){
				if(para.substring(0, 11).equals("ModifyConf_")){
					ParseCF parsecf = new ParseCF();
					ConfParameters confpara = parsecf.Parsesingleconf(para.substring(11));
					request.setAttribute("confname", confpara.getName());
					request.setAttribute("winnumber", confpara.getlayout().GetImage().size());
					request.getRequestDispatcher("ConfModify.jsp").forward(request, response);
					break;
				}else if(para.substring(0, 11).equals("DeleteConf_")){
					ParseCF parsecf = new ParseCF();
					ConfParameters confpara = parsecf.Parsesingleconf(para.substring(11));
					confpara.setlayout(new ConfPara_Layout());
					confpara.getlayout().setName(confpara.getName());
					parsecf.deleteconf(confpara);
					List<String> allconf = parsecf.Parse();
					request.setAttribute("allconf", allconf);
					request.getRequestDispatcher("ConfManage.jsp").forward(request, response);
					break;
				}
			}
		}
	}
}
