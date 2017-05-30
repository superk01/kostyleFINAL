package search.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SearchThread extends Thread {
	
	private String path;
	private String result;
	private boolean flag = false;
	
	public SearchThread(){
		
	}
	
	public SearchThread(String path) {
		super();
		this.path = path;
		result = "";
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public void threadStop(){
		this.flag = true;
	}

	@Override   
	public void run() {

		try {
	         URL url = new URL(path);
	         /*System.out.println("Searching-url:"+url);
	         System.out.println("Searching:"+path);*/
	         URLConnection con = url.openConnection();
	         BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         
	         String tmp="";
	         while ((tmp = br.readLine()) != null) {
	             result += tmp;
	        	/* list.add(tmp);*/
	            /* System.out.println(tmp);*/
	             
	         }
	         
	         System.out.println("완료");
	         br.close();
	         Thread.sleep(1000);
	      } catch (Exception e) {
	         e.printStackTrace();
	      } 
		
		/*System.out.println("SearchThread-result:"+result);*/
	}
	}


