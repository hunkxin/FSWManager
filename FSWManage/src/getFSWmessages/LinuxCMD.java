package getFSWmessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LinuxCMD {
	
	public static final String cmd_status = "status";
	public static final String cmd_channels = "show channels";
	public static final String cmd_reg = "sofia status profile internal reg";
	public static final String cmd_conf = "conference list";
	public static final String cmd_reloadxml = "reloadxml";
	public static final String cmd_conftime = "get run_time";
	
	public List<String> runCommand(String cmd) throws IOException, InterruptedException{
		String[] cmds = {"/bin/sh","-c","/usr/local/freeswitch/bin/fs_cli -x "+"'"+cmd+"'"};  
        Process pro = Runtime.getRuntime().exec(cmds);  
        pro.waitFor();  
        InputStream in = pro.getInputStream();  
        BufferedReader read = new BufferedReader(new InputStreamReader(in));  
        String line = null;  
        List<String> messages = new ArrayList<String>();
        while((line = read.readLine())!=null){  
//            System.out.println(line);
            messages.add(line);
        }
        in.close();
        read.close();
        pro.destroy();
//      System.out.println(messages.size());
        return messages; 
	}    
}
