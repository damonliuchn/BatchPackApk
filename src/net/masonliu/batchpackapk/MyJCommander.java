package net.masonliu.batchpackapk;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.mason.library.StringUtil;

public class MyJCommander {
	public MyJCommander(String args[]) {
		JCommander jc = new JCommander(this, args);
		if (this.help) {

			StringBuffer sb = new StringBuffer();
			sb.append("\n");
			sb.append("********************************************************\n");
			sb.append("BatchPackApk\n");
			sb.append("BatchPackApk is a tool for batch package Android apk with channel string\n");
			sb.append("Example:java -jar batchpackapk.jar example.apk ./ -c channel1,channel2\n");
			sb.append("Copyright 2014 Mason Liu <masonliuchn@gmail.com>\n");
			sb.append("********************************************************\n");
			sb.append("Usage: batchpackapk <apk_path> <output_path>");
			jc.setProgramName(sb.toString());
			jc.usage();
		}
		
		if(parameters.size()>0){
			apkpath = parameters.get(0);
			apkpath = new File(apkpath).getAbsolutePath();
		}else{
			System.exit(0);
		}
		
		if(parameters.size()>1){
			output = parameters.get(1);
		}
		if(StringUtil.isBlank(output)){
			output = System.getProperty("user.dir");
		}
		output = new File(output).getAbsolutePath();

		if (!StringUtil.isBlank(cf)){
			cf = new File(cf).getAbsolutePath();
			String[] cs = StringUtil.file2String(new File(cf), "UTF-8").split("\n");
			channels = Arrays.asList(cs);
		}
	}
	
	@Parameter(names = "-cf", description = "channel list file path")
	public String cf;

	@Parameter(names = "-c", description = "channel list string, Example:channel1,channel2")
	public List<String> channels = new ArrayList<String>();

	@Parameter
	public List<String> parameters = new ArrayList<String>();

	public String output;

	public String apkpath;

	@Parameter(names = { "-debug", "-verbose" }, description = "Debug mode")
	public boolean debug = false;

	@Parameter(names = { "-help", "--help" }, description = "Help mode",help = true)
	public boolean help;
}
