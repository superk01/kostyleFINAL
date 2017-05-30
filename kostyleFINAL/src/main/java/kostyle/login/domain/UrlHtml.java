package login.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class UrlHtml{
private List<String> htmlList = null;
	private  URL url = null;
	private  String address = null;

	public UrlHtml(){}

	public UrlHtml(String address) {
		super();
		this.address = address;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> urlHtml(String address){
		try {
			   url = new URL(address);
		       URLConnection con = url.openConnection();
		        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        String tmp;

		        while ((tmp = br.readLine()) != null) {
		           htmlList.add(tmp);
		           System.out.println(tmp);
		        }
		        System.out.println("완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlList;
	}
		
	public static void main(String[] args){
		URL url =null;
		BufferedReader br =null;
		String address="http://k-club.co.kr/?cafe_mkt=ue_trend_2&NaPm=ct%3Dj1x01it4%7Cci%3D90203119276f9bb654d918a4f99b58650c738f8a%7Ctr%3Dsbtp%7Csn%3D203198%7Chk%3D33fc48cc00f36b30fb41c5a4e1b55e0bcfbc00a32";
		try {
			url = new URL(address);
			URLConnection con = url.openConnection(); 
			br = new BufferedReader(new InputStreamReader(url.openStream()));
	        String tmp;

	        while ((tmp = br.readLine()) != null) {
	           //htmlList.add(tmp);
	           System.out.println(tmp);
	        }
	        br.close();
	        System.out.println("완료");
			
			System.out.println("conn.toString(): "+con);
			System.out.println("getAllowserInteration(): "+con.getAllowUserInteraction());
			System.out.println("getConnectTimeout(): "+ con.getConnectTimeout());
			System.out.println("getContent(): "+con.getContent());
			System.out.println("getContentEncoding(): "+con.getContentEncoding());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}//classBody
