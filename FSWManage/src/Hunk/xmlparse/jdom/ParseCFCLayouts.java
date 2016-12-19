package Hunk.xmlparse.jdom;

import java.io.IOException;  
import java.util.Iterator;  
import java.util.List;
import java.util.Vector;

import org.jdom2.input.SAXBuilder;
//import org.jdom2.xpath.XPath;  
import org.jdom2.Document;  
import org.jdom2.Element;  
import org.jdom2.JDOMException;
  
@SuppressWarnings("deprecation")
public class ParseCFCLayouts {
  
	public ConfPara_Layout Parse(String layoutname,String canvas_size){
		SAXBuilder builder=new SAXBuilder(false);
		FileOutput fileoutput = new FileOutput();
		ConfPara_Layout CFPL_Read = new ConfPara_Layout();
		CFPL_Read.setcanvas_size(canvas_size);
		try {
			Document doc=builder.build(fileoutput.getxmlpath()+fileoutput.getcflayoutfile());
			List<?> layoutlist=doc.getRootElement().getChild("layout-settings").getChild("layouts").getChildren("layout");
			for (Iterator<?> iter = layoutlist.iterator(); iter.hasNext();) {
				Element layout = (Element) iter.next();
//				System.out.println(layout.getAttributeValue("name"));
				if(layoutname.equals(layout.getAttributeValue("name")))
				{
					CFPL_Read.setName(layout.getAttributeValue("name"));
					CFPL_Read.setimagebgname(layout.getAttributeValue("bgimg"));
					List<?> imagelist=layout.getChildren("image");
					for(Iterator<?> iter1 = imagelist.iterator(); iter1.hasNext();){
						Element image = (Element) iter1.next();
						ImageData tmp = new ImageData(
								this.transformtou(image.getAttributeValue("x"), CFPL_Read.sizetowide()),
								this.transformtou(image.getAttributeValue("y"), CFPL_Read.sizetoheight()),
								this.transformtou(image.getAttributeValue("scale"), CFPL_Read.sizetowide()),
								this.transformtou(image.getAttributeValue("hscale"), CFPL_Read.sizetoheight())
								);
						CFPL_Read.add(tmp);
					}//end for	
					break;
				}
			}//end for		
		} catch (JDOMException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
		return 	CFPL_Read;
	}
	
	public boolean add(ConfPara_Layout layoutparanew,String time){
		
		SAXBuilder builder=new SAXBuilder(false);
		FileOutput fileoutput = new FileOutput();
		//ConfPara_Layout CFPL_Read = new ConfPara_Layout();
		try 
		{
			Document doc=builder.build(fileoutput.getxmlpath()+fileoutput.getcflayoutfile());
			doc.getRootElement().getChild("layout-settings").getChild("layouts").addContent(layoutparanew.toXMLElement());
			fileoutput.fileoutput(doc, fileoutput.getcflayoutfile(),fileoutput.getbackupsxmlcflayoutpath(),time);
		} catch (JDOMException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean modify(ConfPara_Layout layoutparanew,String oldlayoutname,String time){
		
		if(this.delete(oldlayoutname,time))
			return this.add(layoutparanew,time);
		else
			return false;
	}
	
	public Boolean delete(String layoutname,String time){
		SAXBuilder builder=new SAXBuilder(false);
		FileOutput fileoutput = new FileOutput();
		try {
				Document doc=builder.build(fileoutput.getxmlpath()+fileoutput.getcflayoutfile());
				List<?> layoutlist=doc.getRootElement().getChild("layout-settings").getChild("layouts").getChildren("layout");
				Vector<Integer> deleteindex = new Vector<Integer>();
				for (Iterator<?> iter = layoutlist.iterator(); iter.hasNext();) {
					Element layout = (Element) iter.next();	
					if(layout.getAttributeValue("name").equals(layoutname)){
						//doc.getRootElement().getChild("layout-settings").getChild("layouts").removeContent(layout);
						//break;
						deleteindex.add(layout.getParent().indexOf(layout));
					}			
				}
				int m =0;
				for(int n = 0;n<deleteindex.size();n++)
				{			
					Element a = doc.getRootElement().getChild("layout-settings").getChild("layouts");
					a.removeContent(deleteindex.elementAt(n)-m);
					m+=1;
				}
				fileoutput.fileoutput(doc, fileoutput.getcflayoutfile(),fileoutput.getbackupsxmlcflayoutpath(),time);
		} catch (JDOMException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	protected String transformtou(String xmldata,String userdata){
		int data = (Integer.parseInt(xmldata))*(Integer.parseInt(userdata))/360;
		return Integer.toString(data);
	}
} 

