package Hunk.xmlparse.jdom;

import java.util.Vector;

import org.jdom2.Element;

public class ConfPara_Layout {
	protected String name;
	protected Vector<ImageData> image = new Vector<ImageData>();
	protected String imagebgname;
	protected String canvas_size;
	
	public ConfPara_Layout() {
		return;
	}
	
	public ConfPara_Layout(String name, String fn,String canvas_size) {
		this.name = name;
		this.imagebgname = fn;
		this.canvas_size = canvas_size;
		return;
	}
	
	public ConfPara_Layout(String name, String fn, String canvas_size,ImageData data[]) {
		this.name = name;
		this.imagebgname = fn;
		this.canvas_size = canvas_size;
		if(data != null)
		{
			for(int i=0; i < data.length; i++)
			{
				this.add(data[i]);
			}
		}
		return ;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getimagebgname()
	{
		return this.imagebgname;
	}
	
	public String getcanvas_size()
	{
		return this.canvas_size;
	}
	
	public int add(ImageData ins)
	{
		this.image.add(ins);
		return this.image.size();
	}
	
	public ImageData getImageData(int index)
	{
		if(index >= this.image.size())
			return null;
		
		return this.image.get(index);
	}
	
	public Vector<ImageData> GetImage()
	{
		return this.image;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setimagebgname(String imagebgname)
	{
		this.imagebgname=imagebgname;
	}
	
	public void setcanvas_size(String canvas_size)
	{
		this.canvas_size=canvas_size;
	}
	
	public Element toXMLElement()
	{
		Element retu = new Element("layout");
		retu.setAttribute("name", this.name);
		retu.setAttribute("auto-3d-position", "true");
		retu.setAttribute("bgimg", this.imagebgname);
		for(int i=0; i < this.image.size(); i++)
		{
			ImageData tmp = this.image.get(i);
			retu.addContent(tmp.toXMLElement(this.sizetowide(),this.sizetoheight()));
		}	
		return retu;
	}
	
	public String sizetowide()
	{
		int indexofx = this.canvas_size.indexOf("x");
		return this.canvas_size.substring(0, indexofx);
	}
	
	public String sizetoheight()
	{
		int indexofx = this.canvas_size.indexOf("x");
		return this.canvas_size.substring(indexofx+1);
	}

}
