package Hunk.xmlparse.jdom;

public class ConfParameters {
	
	protected String conf_name;
	protected String layoutname;
	protected String canvas_size;
	protected ConfPara_Layout layout;
	
	public ConfParameters(){
		return;
	}
	
	public ConfParameters(String conf_name,String canvas_size,ConfPara_Layout layout) {
		
		this.conf_name=conf_name;
		this.canvas_size=canvas_size;
		this.layout=layout;
		this.layout.setName(conf_name);
	}
	
	public String getName()
	{
		return this.conf_name;
	}
	
	public String getlayoutName()
	{
		return this.layoutname;
	}
	
	public String getcanvas_size()
	{
		return this.canvas_size;
	}
	
	public ConfPara_Layout getlayout()
	{
		return this.layout;
	}
	
	public void setName(String name)
	{
		this.conf_name=name;
	}
	
	public void setlayoutName(String layoutname)
	{
		this.layoutname=layoutname;
	}

	public void setcanvas_size(String canvas_size)
	{
		this.canvas_size=canvas_size;
	}
	
	public void setlayout(ConfPara_Layout layout)
	{
		this.layout=layout;
	}
	
}
