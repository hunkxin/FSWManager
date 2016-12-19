package Hunk.xmlparse.jdom;
import java.util.Date;
import java.text.SimpleDateFormat;

public class GetSystemTime {
	public String gettime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String time=df.format(new Date()).toString();
		return time;
	}
}
