//package com.qiyi.testhwmessenger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.lang.reflect.Method;

public class TestHWMessenger {
 private void unzip(String file) throws Exception{
	System.out.println("unzip :" + file);
	ZipInputStream zipIn = new ZipInputStream(new FileInputStream(file));
	ZipEntry entry = zipIn.getNextEntry();
	int offset = 0;
	while (entry != null) {
		//get dataOffset;
		
		Object obj;
		Method method = ZipEntry.class.getDeclaredMethod("getDataOffset");
		if(! method.isAccessible()) {
			method.setAccessible(true);
		}
		obj = entry;
		method.invoke(obj);
		
		zipIn.closeEntry();
		System.out.println("entry name  : " + entry.getName());
		System.out.println("	   offset  : " + offset);
		System.out.println("	   offset2  : " + method.invoke(obj));
		System.out.println("	  size: " + entry.getSize());
		System.out.println("	  comp size: " + entry.getCompressedSize());
		offset += entry.getSize();
		entry = zipIn.getNextEntry();
	}
	zipIn.close();
}

 public static void main(String[] args) throws Exception{
	//System.out.println("test code");
	//TestHWMessenger demo = new TestHWMessenger();
	//demo.unzip(args[0]);
	 	Runtime currentRT = Runtime.getRuntime();
    	BufferedReader br = null;
        // 当前本地进程
        Process currentProcess;
        String tempInfo;
        System.out.println("开始调用命令..." + "\n");
        try {
        currentProcess = currentRT.exec("ls -l");
        InputStreamReader isr=new InputStreamReader(currentProcess.getInputStream(),"utf-8");
        System.out.println("InputStreamReader:"+isr.getEncoding());
        br=new BufferedReader(isr);
        while ((tempInfo=br.readLine())!=null) {
            System.out.println(tempInfo);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
