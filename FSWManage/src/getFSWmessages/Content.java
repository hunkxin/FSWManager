package getFSWmessages;

import java.util.List;

public class Content {
	private String regname;
	private String direction;
	private String activebgtime;
	private String caller;
	private String dest;
	private String application;
	private String applicationdata;
	
	private String confname;
	private int confmemcount;
	private List<String> confmember;
	
	public Content(String confname, int confmemcount, List<String> confmember) {
		super();
		this.confname = confname;
		this.confmemcount = confmemcount;
		this.confmember = confmember;
	}

	public String getConfname() {
		return confname;
	}

	public void setConfname(String confname) {
		this.confname = confname;
	}
	
	public int getConfmemcount() {
		return confmemcount;
	}
	
	public void setConfmemcount(int confmemcount) {
		this.confmemcount = confmemcount;
	}

	public List<String> getConfmember() {
		return confmember;
	}

	public void setConfmember(List<String> confmember) {
		this.confmember = confmember;
	}

	public Content(String regname, String direction, String activebgtime,
			String caller, String dest, String application, String applicationdata) {
		super();
		this.regname = regname;
		this.direction = direction;
		this.activebgtime = activebgtime;
		this.caller = caller;
		this.dest = dest;
		this.application = application;
		this.applicationdata = applicationdata;
	}
	
	public String getRegname() {
		return regname;
	}
	public void setRegname(String regname) {
		this.regname = regname;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getActivebgtime() {
		return activebgtime;
	}
	public void setActivebgtime(String activebgtime) {
		this.activebgtime = activebgtime;
	}
	public String getCaller() {
		return caller;
	}
	public void setCaller(String caller) {
		this.caller = caller;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getApplicationdata() {
		return applicationdata;
	}
	public void setApplicationdata(String applicationdata) {
		this.applicationdata = applicationdata;
	}
}
