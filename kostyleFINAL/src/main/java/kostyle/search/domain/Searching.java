package search.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;




public class Searching {
	/*List<String> list;*/
	String result="";
	public String searching(String path){
		
		try {
	         URL url = new URL(path);
	         System.out.println("Searching-url:"+url);
	         System.out.println("Searching:"+path);
	         URLConnection con = url.openConnection();
	         BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	         
	         String tmp="";
	         while ((tmp = br.readLine()) != null) {
	             result += tmp;
	        	/* list.add(tmp);*/
	             System.out.println(tmp);
	         }
	         
	         System.out.println("완료");
	         br.close();
	      
	      } catch (Exception e) {
	         e.printStackTrace();
	      } 
		System.out.println("Searching-result:"+result);
		return result;
	}
}
