package Hunk.xmlparse.jdom;

import org.jdom2.Element; 

public class ImageData {

		private String x = "";
		private String y = "";
		private String scale = "";
		private String hscale = "";
		private Boolean zoom = true;
		
		public String getx()
		{
			return this.x;
		}
		
		public String gety()
		{
			return this.y;
		}
		
		public String getscale()
		{
			return this.scale;
		}
		
		public String gethscale()
		{
			return this.hscale;
		}
		
		public ImageData(String x, String y, String scale, String hscale)
		{
			this.x = x;
			this.y = y;
			this.scale = scale;
			this.hscale = hscale;
		}
		
		public Element toXMLElement(String userw,String userh)
		{
			Element retu = new Element("image");
			retu.setAttribute("x", this.transformtos(x, userw));
			retu.setAttribute("y", this.transformtos(y, userh));
			retu.setAttribute("scale", this.transformtos(scale, userw));
			retu.setAttribute("hscale", this.transformtos(hscale, userh));
			retu.setAttribute("zoom", "true");
			return retu;
		}
		
		protected String transformtos(String htmdata,String userdata){
			int data = (Integer.parseInt(htmdata))*360/(Integer.parseInt(userdata));
			return Integer.toString(data);
		}
		
		public String toString()
		{
			return "<image x=\""+x+"\" y=\""+y+"\" scale=\""+scale+"\" hscale=\""+hscale+"\" zoom=\""+zoom+"\"/>";
		}
}
