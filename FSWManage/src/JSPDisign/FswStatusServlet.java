package JSPDisign;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import getFSWmessages.MessageFilter;

/**
 * Servlet implementation class FswStatusServlet
 */
@WebServlet("/FswStatus")
public class FswStatusServlet extends HttpServlet {
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
//		doGet(request, response);
//		System.out.println(request.getParameter("action"));
		switch(request.getParameter("action"))
		{
		case "status":
			this.runaction(request,response,"status");
			break;
		case "Regs":
			this.runaction(request,response,"Regs");
			break;
		case "Channels":
			this.runaction(request,response,"Channels");
			break;
		case "Confs":
			this.runaction(request,response,"Confs");
			break;
		default:
			request.getRequestDispatcher("/WEB-INF/Index.jsp").forward(request, response);
		}
		return;
	}

	protected void runaction(HttpServletRequest request, HttpServletResponse response,String action) throws ServletException, IOException{
		request.setAttribute("hstatus", action);
		MessageFilter filter = new MessageFilter();
		switch(action)
		{
		case "status":
			request.setAttribute("msgstatus", filter.GetStatus());
			break;
		case "Regs":
			request.setAttribute("msgReg", filter.GetReg());
			if(filter.GetReg()!=null)
				request.setAttribute("msgRegCount", filter.GetReg().size());
			break;
		case "Channels":
			request.setAttribute("msgChannels", filter.GetChannels());
			if(filter.GetChannels()!=null)
				request.setAttribute("msgChannelsCount", filter.GetChannels().size());
			break;
		case "Confs":
			request.setAttribute("msgConfs", filter.GetConfs());
			if(filter.GetConfs()!=null){
				request.setAttribute("msgConfsCount", filter.GetConfs().size());
			}
			break;
//		case "status":
//			request.setAttribute("msgstatus", this.test1());
//			break;
//		case "Regs":
//			request.setAttribute("msgReg", this.test1());
//			break;
//		case "Channels":
//			request.setAttribute("msgChannels", this.test(action));
//			break;
//		case "Confs":
//			request.setAttribute("msgConfs", this.test(action));
//			break;
		}
		request.getRequestDispatcher("/FswStatus.jsp").forward(request, response);
	}
	
//	protected List<Content> test(String action){
//		List<Content> contents = new ArrayList<Content>();
//		Content content = null;
//		switch(action)
//		{
//		case "Channels":
//			for(int a=0;a<5;a++){
//				content = new Content("regname"+a,"outbound","activebgtime"+a,"caller"+a,"dest"+a,"application"+a,"applicationdata"+a);
//				contents.add(content);
//			}
//			break;
//		case "Confs":
//			for(int a=0;a<5;a++){
//				List<String> msg = new ArrayList<String>();
//				for(int i=0;i<5;i++){
//					msg.add("msg"+i);
//				}
//				content = new Content("conf"+a,msg);
//				contents.add(content);
//			}
//			break;
//		}
//		return contents;
//	}
//	
//	protected List<String> test1(){
//		List<String> contents = new ArrayList<String>();
//		for(int a=0;a<5;a++){
//			contents.add("abc"+a);
//			}
//		return contents;
//	}
}
