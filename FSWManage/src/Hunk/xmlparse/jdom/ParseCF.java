package Hunk.xmlparse.jdom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


public class ParseCF {
	
	public List<String> Parse(){
		
		SAXBuilder builder=new SAXBuilder(false);
		FileOutput fileoutput = new FileOutput();
		List<String> allconf = new ArrayList<> ();
		try {
				Document doc=builder.build(fileoutput.getxmlpath()+fileoutput.getconffile());
				List<?> conflist=doc.getRootElement().getChild("profiles").getChildren("profile");
				for (Iterator<?> iter = conflist.iterator(); iter.hasNext();) 
				{
					Element conf = (Element) iter.next();
					allconf.add(conf.getAttributeValue("name"));	
				}	
		} catch (JDOMException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
		return allconf;
	}
	
public ConfParameters Parsesingleconf(String confname){
	
		SAXBuilder builder=new SAXBuilder(false);
		FileOutput fileoutput = new FileOutput();
		ConfParameters confpara = new ConfParameters();
		try {
				Document doc=builder.build(fileoutput.getxmlpath()+fileoutput.getconffile());
				List<?> conflist=doc.getRootElement().getChild("profiles").getChildren("profile");	
				for (Iterator<?> iter = conflist.iterator(); iter.hasNext();) 
				{
					Element conf = (Element) iter.next();
					if(conf.getAttributeValue("name").equals(confname))
					{
						confpara.setName(confname);
						List<?> confparalist=conf.getChildren("param");
						for (Iterator<?> paralist = confparalist.iterator(); paralist.hasNext();) 
						{
							Element para = (Element) paralist.next();
						    if(para.getAttributeValue("name").equals("video-layout-name")){
						    	confpara.setlayoutName(para.getAttributeValue("value"));
							}
						    if(para.getAttributeValue("name").equals("video-canvas-size")){	
								confpara.setcanvas_size(para.getAttributeValue("value"));
							}
						}
						ParseCFCLayouts parselayout = new ParseCFCLayouts();
				    	confpara.setlayout(parselayout.Parse(confpara.getlayoutName(),confpara.getcanvas_size()));
						break;
					}			
				}	
		} catch (JDOMException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
		return confpara;
	}
	
	public boolean addconf(ConfParameters confparanew,String ConfTemp){
		SAXBuilder builder=new SAXBuilder(false);
		FileOutput fileoutput = new FileOutput();
		try {
				Document doc=builder.build(fileoutput.getxmlpathtemplate());
				List<?> conftemplist=doc.getRootElement().getChildren("profile");
				Element conftemp = null;
				for (Iterator<?> itertemp = conftemplist.iterator(); itertemp.hasNext();)
				{
					Element conftp = (Element) itertemp.next();
					if (conftp.getAttributeValue("name").equals(ConfTemp)){	
						conftemp=conftp;
						conftemp.setAttribute("name",confparanew.getName());
						List<?> confparatemplist=conftemp.getChildren("param");
						for (Iterator<?> paratemplist = confparatemplist.iterator(); paratemplist.hasNext();)
						{
							Element confparatp = (Element) paratemplist.next();
							if (confparatp.getAttributeValue("name").equals("video-layout-name")){
								confparatp.setAttribute("value",confparanew.getlayout().getName());
					    	}
							else if(confparatp.getAttributeValue("name").equals("video-canvas-size")){
								confparatp.setAttribute("value",confparanew.getcanvas_size());
				    		}
						}
						break;
					}
				}
				doc=builder.build(fileoutput.getxmlpath()+fileoutput.getconffile());
				doc.getRootElement().getChild("profiles").addContent(conftemp.detach());
				confparanew.getlayout().setName(confparanew.getName());
				ParseCFCLayouts parselayout = new ParseCFCLayouts();
				GetSystemTime time=new GetSystemTime();
				String timenow = time.gettime();
				parselayout.add(confparanew.getlayout(),timenow);
				fileoutput.fileoutput(doc, fileoutput.getconffile(),fileoutput.getbackupsxmlconfpath(),timenow);
		} catch (JDOMException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean modifyconf(ConfParameters confparanew,String oldconfname){
		SAXBuilder builder=new SAXBuilder(false);
		FileOutput fileoutput = new FileOutput();
		try {
				Document doc=builder.build(fileoutput.getxmlpath()+fileoutput.getconffile());
				List<?> conflist=doc.getRootElement().getChild("profiles").getChildren("profile");
				for (Iterator<?> iter = conflist.iterator(); iter.hasNext();)
				{
					Element conf = (Element) iter.next();
					if(conf.getAttributeValue("name").equals(oldconfname))
					{
						conf.setAttribute("name",confparanew.getName());
						List<?> confparalist=conf.getChildren("param");
						for (Iterator<?> paralist = confparalist.iterator(); paralist.hasNext();) 
						{
							Element para = (Element) paralist.next();
					    	if (para.getAttributeValue("name").equals("video-layout-name"))
					    	{
					    		para.setAttribute("value", confparanew.getlayout().getName());
						    }
					    	else if(para.getAttributeValue("name").equals("video-canvas-size"))
					    	{
					    		para.setAttribute("value", confparanew.getcanvas_size());
					    	}
						}
						break;
					}
				}
				ParseCFCLayouts parselayout = new ParseCFCLayouts();
				GetSystemTime time=new GetSystemTime();
				String timenow = time.gettime();
	    		parselayout.modify(confparanew.getlayout(),oldconfname,timenow);
	    		fileoutput.fileoutput(doc, fileoutput.getconffile(),fileoutput.getbackupsxmlconfpath(),timenow);
		} catch (JDOMException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteconf(ConfParameters confparadel){
		SAXBuilder builder=new SAXBuilder(false);
		FileOutput fileoutput = new FileOutput();
		try {
				Document doc=builder.build(fileoutput.getxmlpath()+fileoutput.getconffile());
				List<?> conflist=doc.getRootElement().getChild("profiles").getChildren("profile");
				Vector<Integer> deleteindex = new Vector<Integer>();
				for (Iterator<?> iter = conflist.iterator(); iter.hasNext();) 
				{
					Element conf = (Element) iter.next();
					//System.out.println(conf.getAttributeValue("name"));
					if(conf.getAttributeValue("name").equals(confparadel.getName()))
					{
						deleteindex.add(doc.getRootElement().getChild("profiles").indexOf(conf));
					}				
				}
				int m =0;
				for(int n = 0;n<deleteindex.size();n++)
				{
					Element a = doc.getRootElement().getChild("profiles");
					a.removeContent(deleteindex.elementAt(n)-m);
					m+=1;
				}
				confparadel.getlayout().setName(confparadel.getName());
				ParseCFCLayouts parselayout = new ParseCFCLayouts();
				GetSystemTime time=new GetSystemTime();
				String timenow = time.gettime();
				parselayout.delete(confparadel.getlayout().getName(),timenow);
				fileoutput.fileoutput(doc, fileoutput.getconffile(),fileoutput.getbackupsxmlconfpath(),timenow);				
		} catch (JDOMException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}	
}
