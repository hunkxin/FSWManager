package Hunk.xmlparse.jdom;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class FileOutput {
//	private String xmlpath="C:\\Users\\Administrator\\Desktop\\test\\";
//	private String backupsxmlconfpath="C:\\Users\\Administrator\\Desktop\\test\\backup\\conf\\";
//	private String backupsxmlcflayoutpath="C:\\Users\\Administrator\\Desktop\\test\\backup\\cflayout\\";
//	private String xmlpathtemplate="C:\\Users\\Administrator\\Desktop\\test\\conference.conf.templete.xml";
	
	private String xmlpathtemplate="/usr/local/freeswitch/conf/conference.conf.templete.xml";
	private String xmlpath="/usr/local/freeswitch/conf/autoload_configs/";
	private String backupsxmlconfpath="/usr/local/freeswitch/conf/autoload_configs/backup/conf/";
	private String backupsxmlcflayoutpath="/usr/local/freeswitch/conf/autoload_configs/backup/cflayout/";
	private String conffile="conference.conf.xml";
	private String cflayoutfile="conference_layouts.conf.xml";
	
	public void setxmlpath(String path){
		this.xmlpath=path;
	}
	
	public void setbackupsxmlconfpath(String path){
		this.backupsxmlconfpath=path;
	}
	
	public void setbackupsxmlcflayoutpath(String path){
		this.backupsxmlcflayoutpath=path;
	}
	
	public void setconffile(String name){
		this.conffile=name;
	}
	
	public void setcflayoutfile(String name){
		this.cflayoutfile=name;
	}
	
	public void setxmlpathtemplate(String pathtemplate){
		this.xmlpathtemplate=pathtemplate;
	}
	
	public String getxmlpath(){
		return this.xmlpath;
	}
	
	public String getbackupsxmlconfpath(){
		return this.backupsxmlconfpath;
	}
	
	public String getbackupsxmlcflayoutpath(){
		return this.backupsxmlcflayoutpath;
	}
	
	public String getconffile(){
		return this.conffile;
	}
	
	public String getcflayoutfile(){
		return this.cflayoutfile;
	}
	
	public String getxmlpathtemplate(){
		return this.xmlpathtemplate;
	}
	
	public void fileoutput(Document doc,String name,String backuppath,String time){
		try {
			Format xmlDocumentFormat=Format.getPrettyFormat();
			XMLOutputter outputter=new XMLOutputter(xmlDocumentFormat);
			FileOutputStream outputstream = new FileOutputStream(this.getxmlpath()+name);
			outputter.output(doc,outputstream);
			outputstream = new FileOutputStream(backuppath+name.substring(0, name.lastIndexOf("."))+"_"+time+".xml");
			outputter.output(doc,outputstream);
			outputstream.flush();
			outputstream.close();
//			outputstream.getFD().sync();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {  
			e.printStackTrace(); 
		}
	}
	
	public boolean setdefault(String sPath) { 
		boolean flag = false;
        if(copyFile(sPath,this.getxmlpath()+this.getconffile(),true)){
        	String backupcflayoutpath = this.getbackupsxmlcflayoutpath()
        			+this.getcflayoutfile().substring(0, this.getcflayoutfile().lastIndexOf("."))
        			+sPath.substring(sPath.indexOf("_"));
        	if(copyFile(backupcflayoutpath,this.getxmlpath()+this.getcflayoutfile(),true))
        		flag = true;
        } 	  
	    return flag;  
	} 
	
	public List<String> getconffilename() { 
		List<String> conffilename = new ArrayList<String> ();
		File backupfileconf = new File(this.backupsxmlconfpath); 
		if(backupfileconf.exists())
		{
            File[] files = backupfileconf.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    continue;
                } else {
//                	conffilename.add(file2.getAbsolutePath().substring(file2.getAbsolutePath().indexOf("_")+1,
//                			file2.getAbsolutePath().lastIndexOf(".")));
                	String name= file2.getAbsolutePath().substring(file2.getAbsolutePath().lastIndexOf("/"));
                	conffilename.add(name.substring(name.indexOf("_")+1,
                			name.lastIndexOf(".")));
                }
            }
		}
		return conffilename;
	} 
	
	public List<String> deleteconffile(String allselectedname) { 
		while(allselectedname.lastIndexOf("/")!=-1){
			String confname = this.getbackupsxmlconfpath()
					+this.getconffile().substring(0, this.getconffile().lastIndexOf("."))
					+"_"
					+allselectedname.substring(8,allselectedname.indexOf("/"))
					+".xml";
			String cflayoutname = this.getbackupsxmlcflayoutpath()
					+this.getcflayoutfile().substring(0, this.getcflayoutfile().lastIndexOf("."))
					+"_"
					+allselectedname.substring(8,allselectedname.indexOf("/"))
					+".xml";
			deleteFile(confname);
			deleteFile(cflayoutname);
			allselectedname = allselectedname.substring(allselectedname.indexOf("/")+1);
		}
		return this.getconffilename();
	} 
	
	protected boolean deleteFile(String srcFileName) {
//		System.out.println(srcFileName);
		File srcFile = new File(srcFileName);
		 if (!srcFile.exists()) {    
	            return false;  
	        } else if (!srcFile.isFile()) {  
	            return false;  
	        } 
		 if(srcFile.delete())
			 return true;
		 return false;
	}
	
	protected boolean copyFile(String srcFileName, String destFileName,  
            boolean overlay) {  
        File srcFile = new File(srcFileName);  
  
        // 判断源文件是否存在  
        if (!srcFile.exists()) {    
            return false;  
        } else if (!srcFile.isFile()) {  
            return false;  
        }  
  
        // 判断目标文件是否存在  
        File destFile = new File(destFileName);  
        if (destFile.exists()) {  
            // 如果目标文件存在并允许覆盖  
            if (overlay) {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } else {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) {  
                // 目标文件所在目录不存在  
                if (!destFile.getParentFile().mkdirs()) {  
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;  
                }  
            }  
        }  
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
}
