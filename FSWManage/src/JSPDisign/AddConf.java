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
import Hunk.xmlparse.jdom.ImageData;
import Hunk.xmlparse.jdom.ParseCF;

/**
 * Servlet implementation class AddConf
 */
@WebServlet("/ConfDetail")
public class AddConf extends HttpServlet {
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
			if(para.equals("backtoconfmanage")){
				ParseCF parsecf = new ParseCF();
				List<String> allconf = parsecf.Parse();
				request.setAttribute("allconf", allconf);
				request.getRequestDispatcher("ConfManage.jsp").forward(request, response);
				break;
			}else if(para.equals("submitDetail")){
				request.setAttribute("confnameD", request.getParameter("confname"));
				request.setAttribute("confnumberD", request.getParameter("confnumber"));
				request.setAttribute("ConfFileTmpD", request.getParameter("ConfFileTmp"));
				request.setAttribute("selectedvalue", "/");
				request.getRequestDispatcher("ConfDetail.jsp").forward(request, response);
				break;
			}else if(para.equals("backtoSingleConf")){
				if(request.getParameter("confnameDhb").equals("?")){
					request.getRequestDispatcher("NewConf.jsp").forward(request, response);
					break;
				}
				request.setAttribute("confname", request.getParameter("confnameDhb"));
				request.setAttribute("winnumber", request.getParameter("confnumberDhb"));
				request.getRequestDispatcher("ConfModify.jsp").forward(request, response);
				break;
			}else if(para.equals("addconf")){
				ParseCF parsecf = new ParseCF();
				ConfParameters confpara = new ConfParameters();
				confpara.setName(request.getParameter("confnameD"));
				confpara.setcanvas_size(request.getParameter("resolution"));
				confpara.setlayout(new ConfPara_Layout());
				confpara.getlayout().setName(confpara.getName());
				confpara.getlayout().setcanvas_size(confpara.getcanvas_size());
//				confpara.getlayout().setimagebgname(request.getParameter("confnameD"));
				confpara.getlayout().setimagebgname("");
				for(int i = 0;i<Integer.parseInt(request.getParameter("confnumberD"));i++){
					ImageData imagedata = new ImageData(
						request.getParameter("x"+i),
						request.getParameter("y"+i),
						request.getParameter("wide"+i),
						request.getParameter("height"+i));
					confpara.getlayout().GetImage().add(imagedata);
				}
				
				if(request.getParameter("confnameDh").equals("?")){
					parsecf.addconf(confpara, "yike-video-mcu-vroom-");
					List<String> allconf = parsecf.Parse();
					request.setAttribute("allconf", allconf);
					request.getRequestDispatcher("ConfManage.jsp").forward(request, response);
					break;
				}
				parsecf.modifyconf(confpara,request.getParameter("confnameDh"));
				List<String> allconf = parsecf.Parse();
				request.setAttribute("allconf", allconf);
				request.getRequestDispatcher("ConfManage.jsp").forward(request, response);
				break;
			}else if(para.equals("ModifyDetail")){
				request.setAttribute("confnameh", request.getParameter("confnameh"));
				request.setAttribute("confnameD", request.getParameter("confname"));
				request.setAttribute("confnumberh", request.getParameter("confnumberh"));
				request.setAttribute("confnumberD", request.getParameter("confnumber"));
				if(request.getParameter("confnumberh").equals(request.getParameter("confnumber"))){
					ParseCF parsecf = new ParseCF();
					ConfParameters confpara = new ConfParameters();
					confpara = parsecf.Parsesingleconf(request.getParameter("confnameh"));
					request.setAttribute("selectedvalue", confpara.getcanvas_size());
					for(int i=0; i < confpara.getlayout().GetImage().size(); i++)
					{
						ImageData tmp = confpara.getlayout().GetImage().get(i);
						request.setAttribute("x"+i, tmp.getx());
						request.setAttribute("y"+i, tmp.gety());
						request.setAttribute("wide"+i, tmp.getscale());
						request.setAttribute("height"+i, tmp.gethscale());
					}	
					
					request.getRequestDispatcher("ConfDetail.jsp").forward(request, response);
					break;
				}
				request.setAttribute("confnumberD", request.getParameter("confnumber"));
				request.getRequestDispatcher("ConfDetail.jsp").forward(request, response);
				break;
			}
		}
	}
}
