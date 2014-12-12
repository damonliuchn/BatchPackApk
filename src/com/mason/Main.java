package com.mason;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.beust.jcommander.JCommander;
import com.mason.library.FileUtil;
import com.mason.library.StringUtil;
import com.mason.library.ZipUtil;

/**
 * 往meta-inf 添加文件不影响签名，即使不是空文件也没有问题
 * java -jar bb.jar -c google -c ee ee.apk out
 * 
 * 
 * 混淆：
 * !shrink
 * !warning
 * !optimize
 * !com.mason.**
 * keep attribute annotation
 * @author liumeng
 *
 */
public class Main {

    public static void main(String[] args) {
    	String projectDir = System.getProperty("user.dir");
		/*******************************************/
//		String apkPath = "/Users/liumeng/Downloads/eleme4_3_1.apk";
//		String outPath = projectDir+"/output";
//		String channels [] = {"eleme","google"};
    	MyJCommander jct = new MyJCommander(args);
		String apkPath = jct.apkpath;
		String outPath = jct.output;
		String []channels  = jct.channels.toArray(new String[jct.channels.size()]);
		
		outPath = outPath+"/batchpack"+System.currentTimeMillis();
		System.out.println("Processing...");
		/*******************************************/
		try {
			File apkFile = new File(apkPath);
			File tmpApk = new File(outPath+"/tmp.apk");
			
			for(int i=0;i<channels.length;i++){
				FileUtil.copyFile(apkFile, tmpApk);
				File channelFile = new File(outPath+"/umeng-"+channels[i]);
				FileUtil.createNewFile(channelFile);
				ZipUtil.addFileToZip2(tmpApk,"/META-INF",channelFile);
				FileUtil.delete(channelFile);
				FileUtil.rename(tmpApk, channels[i]+"-"+apkFile.getName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done!");
    }
}