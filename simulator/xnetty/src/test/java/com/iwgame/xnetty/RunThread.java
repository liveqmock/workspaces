package com.iwgame.xnetty;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunThread extends Thread {
	public void run(){
		String cmd="ping 127.0.0.1";
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "ISO8859_1"));
			String line = br.readLine();
			int i = 0;
			while ((line != null) && (i < 10)) {
				line=new String(line.getBytes("ISO8859_1"), "GBK");
				System.out.println("renturn i="+i+"  line is "+line);
				if(!line.equals("")){
					i++;
					System.out.println("i="+i+ " "+ line+"\r\n");
				}
				line = br.readLine();
				
			}
			br.close();
			System.out.println("p.waitFor is "+p.waitFor());
			p.destroy();
			System.out.println("thread id = is end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
