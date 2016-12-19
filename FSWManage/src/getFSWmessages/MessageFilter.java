package getFSWmessages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageFilter {
	public List<String> GetReg(){
		List<String> reg = new ArrayList<String>();
		LinuxCMD cmd = new LinuxCMD();
		List<String> msg;
		try {
			msg = cmd.runCommand(LinuxCMD.cmd_reg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(msg.size()>3)
		{
			for(String linemsg:msg){
				if(linemsg.startsWith("Contact:")){
					String tmp = linemsg.substring(
							linemsg.indexOf("<")+1, 
							linemsg.length()-1);
					if(tmp.indexOf(";")>=0)
					{
						tmp = tmp.substring(0, tmp.indexOf(";"));
					}
					reg.add(tmp);
				}
			}
		}else{
			return null;
		}
//		for(String a:reg){
//			System.out.println(a);
//		}
		return reg;
	}
	
	public List<String> GetStatus(){
		LinuxCMD cmd = new LinuxCMD();
		try {
			return ParseStatus(cmd.runCommand(LinuxCMD.cmd_status));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Content> GetConfs(){
		List<Content> contents = new ArrayList<Content>();
		LinuxCMD cmd = new LinuxCMD();
		List<String> msg;
		try {
			msg = cmd.runCommand(LinuxCMD.cmd_conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		if(msg.size()>2){
			int n=0;
			for(String linemsg:msg.subList(1, msg.size()-1)){
				if(!linemsg.startsWith("Conference")){
					n++;
					if(msg.indexOf(linemsg)==(msg.size()-2)){
						contents.add(this.ParseCfLinemsg(msg.subList(msg.indexOf(linemsg)-n, msg.indexOf(linemsg)+1)));
					}
				}else{
					contents.add(this.ParseCfLinemsg(msg.subList(msg.indexOf(linemsg)-n-1, msg.indexOf(linemsg))));
					n=0;
				}
			}
		}else{
			return null;
		}
		return contents;
	}
	
	public List<Content> GetChannels(){
		List<Content> contents = new ArrayList<Content>();
		LinuxCMD cmd = new LinuxCMD();
		List<String> msg;
		try {
			msg = cmd.runCommand(LinuxCMD.cmd_channels);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		//如果msg长度等于3则channel数量为0,否则数量为msg.size()-3
		if(msg.size()>3){
			for(String linemsg:msg.subList(1, msg.size()-3)){
				contents.add(this.ParseCHLinemsg(linemsg));
//				System.out.println("//");
			}
		}else{
			return null;
		}
		return contents;
	}
	
	protected List<String> ParseStatus(List<String> msg){
		List<String> showmsg = new ArrayList<String>();
		String line1 = "运行时长："+msg.get(0).substring(3);
		showmsg.add(line1);
		String line2 = msg.get(1);
		line2="freeSWITCH版本号："+line2.substring(line2.indexOf("Version"), line2.indexOf(")"));
		showmsg.add(line2);
		String line3 = msg.get(2);
		line3="自启动来所运行的总Session数："+line3;
		showmsg.add(line3);
		String line4 = msg.get(3);
		line4="当前运行的Session数："+line4;
		showmsg.add(line4);
		String line5 = msg.get(4);
		line5="系统每秒最大可初始化Session数："+line5.substring(line5.indexOf("max")+4);
		showmsg.add(line5);
		String line6 = msg.get(5);
		line6="系统最大可并发量："+line6;
		showmsg.add(line6);
		String line7 = msg.get(6);
		line7="服务器CPU使用率（空闲）："+line7.substring(line7.indexOf("/")+1);
		showmsg.add(line7);
		return showmsg;
	}
	
	protected Content ParseCHLinemsg(String msg){
		Content content = null;
		String[] tmplist = msg.split(",", 13);
		
		if(tmplist[1].endsWith("outbound")){
			content = new Content
					(tmplist[4].substring(tmplist[4].lastIndexOf("/")+1),
					tmplist[1],
					tmplist[2],
					tmplist[7]+"@"+tmplist[8],
					tmplist[9],
					tmplist[10],
					tmplist[11]);
		}else{
			content = new Content
					(tmplist[6]+"@"+tmplist[8],
					tmplist[1],
					tmplist[2],
					tmplist[7]+"@"+tmplist[8],
					tmplist[9],
					tmplist[10],
					tmplist[11]);
		}
		
//		System.out.print(content.getRegname());
//		System.out.print(content.getDirection());
//		System.out.print(content.getActivebgtime());
//		System.out.print(content.getDest());
//		System.out.print(content.getApplication());
//		System.out.print(content.getApplicationdata());
		
		return content;
	}

	protected Content ParseCfLinemsg(List<String> msg){
		Content content = null;
		List<String> members = new ArrayList<>();
		for(String tmp:msg){
			if(tmp.startsWith("Conference")){
				String timecmd = tmp.substring(0, 
						tmp.indexOf("("));
				LinuxCMD cmd = new LinuxCMD();
				try {
					String msgtime = cmd.runCommand(timecmd+cmd.cmd_conftime).toString();
					String time = msgtime.substring(1, msgtime.length()-1);
					long minute = (long) Math.floor(Integer.parseInt(time)/60);
					int second = Integer.parseInt(time)%60;
					members.add(String.valueOf(minute));
					members.add(String.valueOf(second));
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					members.add("0");
					members.add("0");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					members.add("0");
					members.add("0");
				}
			}else{
				String[] tmplist = tmp.split(";", 3);
				String member = tmplist[1].substring(
						tmplist[1].lastIndexOf("/")+1, 
						tmplist[1].lastIndexOf("@"));
				members.add(member);
			}
		}
		String confname = msg.get(0).substring(11, msg.get(0).indexOf("-"));
		content = new Content(confname,members.size()-2,members);
		
//		System.out.println(content.getConfname());
//		for(String a:content.getConfmember()){
//			System.out.println(a);
//		}
		
		return content;
	}
}
