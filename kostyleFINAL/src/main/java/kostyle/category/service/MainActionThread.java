package Category.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import Category.model.Product;
import sun.security.util.Length;

public class MainActionThread extends Thread{
	private String frontURL;
	private String shopurl;
	private List<Product> main_product_list = new ArrayList<Product>();
	private int pagecount = 0;

	public MainActionThread(String frontURL, String shopurl) {
		super();
		this.frontURL = frontURL;
		this.shopurl = shopurl;
	}

	public List<Product> getMain_product_list() {
		return main_product_list;
	}

	@Override
	public void run() {
		   String frontURL = this.frontURL;
		   String shopurl = this.shopurl;

		   //검색 페이지면 마지막 페이지 숫자 삭제
			   if(frontURL.indexOf("&page="+pagecount) != -1){
				   frontURL = frontURL.substring(0, frontURL.length() - 7);
			   }			   
			   //검색 페이지 처리
			   pagecount ++;
			   frontURL = frontURL + "&page=" + pagecount;	   			   
		   
		   List<Integer> startLiRowNum = new ArrayList<Integer>(); // 물품 시작 box 줄 모음
		   List<Integer> endLiRowNum = new ArrayList<Integer>(); // 물품 끝 box 줄 모음
		   List<String> product_NameList = new ArrayList<String>();
		   List<String> product_PriceList = new ArrayList<String>();
		   List<String> product_LinkList = new ArrayList<String>();
		   List<String> product_ImgLinkList = new ArrayList<String>();
		   List<String> list = new ArrayList<String>();   //페이지row 저장
		   		
		   try {
		         URL url = new URL(frontURL);
		         System.out.println("연결중....url = " + frontURL);
		         URLConnection con = url.openConnection();
		         BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		         String tmp;

		         while ((tmp = br.readLine()) != null) {
		            list.add(tmp);
		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      }finally{
		    	  System.out.println("완료");
		      }
		   
	       // 물품 하나 시작 태그 인식
	       String startLI = "<li id";
	       String startClass = "xans-record"; // 쇼핑몰 물품 박스 class 이름

	       // 물품 하나 끝 태그 인식
	       String endLI = "</li>"; // 이건 row 전체값이 </li>이여야 한다.

	       // 물품 하나 박스 생성

	       // 물품 바로가기 링크 페이지 인식
	       String productLink[] = { "a href=\"/product/detail", "a href=\"/shop/view" };
	       String product_NameCheck = "[가-힣]"; // 물품 이미지 검색 (한글 있으면 true 리턴)
	       int productImgLinkRowNum = 0;// 이미지 src 담겨있는 열 검사결과

	       String Not_product_NameCheck = "[^가-힣]"; // 물품 이름 추출 최대길이 10, 물품 금액은
	                                        // 한글 4글자 이하, 물품 설명은 한글
	                                        // 10자 이하 한글 검사 있으면
	                                        // false리턴
	       int productLinkRowNum = 0; // 결과값 저장

	       
	       //검색한 하나의 상품을 Box형태로 모으기
	       for (int i = 0; i < list.size(); i++) {
	          String row = list.get(i);
	          int startlicheck = -1;
	          int startliclasscheck = -1;
	          startlicheck = row.indexOf(startLI);
	          startliclasscheck = row.indexOf(startClass);

	          if (startlicheck != -1 && startliclasscheck != -1) {
	             startLiRowNum.add(i);
	          } else if (row.trim().equals(endLI) && startLiRowNum.size() > 0) {
	             if (startLiRowNum.get(0) < i) {
	                endLiRowNum.add(i);
	             }
	          }            
	       }
	       //검색한 상품이 없을때 재귀함수 중지
	       if(startLiRowNum.size() == 0 && endLiRowNum.size() == 0 ){
	    	   System.out.println("-------검색끝 " + (pagecount-1) + " 개 페이지 검색-------");
	    	   return;
	       }else if(pagecount == 2){ //느려서 최대9페이지까지만 받아옴
	    	   System.out.println("페이지 초과");
	    	   return;
	       }
	       

	       for (int i = 0; i < startLiRowNum.size(); i++) {
	          int startrow = startLiRowNum.get(i);
	          int endrow = endLiRowNum.get(i);            
	          
	          List<String> oneBoxProduct_price = new ArrayList<String>();
	          List<String> oneBoxProduct_price_reset = new ArrayList<String>();

	          //상품Box에서 데이터뽑기
	          for(int j=startrow; j<endrow; j++){
	             String row = list.get(j);
	             
	            String product_link = ""; //물건 링크값
	             //물건 설명값
	             String product_name = "";//물건 이름값
	             String product_price = "";//물건 금액 값
	             String product_ImageLink = "";//물건 이미지 링크값
	             
	             //가격찾기
	            	String pattern = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
	               	String price = row.replaceAll(pattern, "");	               	
	               	price = price.trim();   		             
	               	String price2 = price.replaceAll("[0-9]{1,3},[0-9]{1,3}", "");
	               	price2 = price2.trim();              	              
	               	List<String> fristrowList = new ArrayList<String>();
	               	List<String> LastrowList = new ArrayList<String>();
	               	
		              for(int s=0; s<price.length(); s++){
		            	  fristrowList.add(price.substring(s,s+1));
		              }
		              for(int s=0; s<price2.length(); s++){
		            	  LastrowList.add(price2.substring(s,s+1));
		              }
		              //두개의리스트를비교해중복값제거하고첫번째리스트에저장
		              fristrowList.removeAll(LastrowList);
		              for(int s=0; s<fristrowList.size(); s++){
		            	  product_price+=fristrowList.get(s);		            	
		              }
		              
		              if(!(product_price.equals(""))){		            	  
		            	  oneBoxProduct_price.add(product_price);
		              }
	              //가격찾기끝
	             
	             for(int p=0; p<productLink.length; p++){
	                int pLinkNum = row.indexOf(productLink[p]);
	                
	                if( pLinkNum != -1 && row.replaceAll("[^가-힣]","").length() > 0){ //이 줄에 링크가 있고 한글이 있으면                                     
	              	 product_name = searchProduct_Name(row);   //한글 값 추출
	                   product_link = searchProduct_Link(row, shopurl); //링크 값 추출
	                   product_NameList.add(product_name);
	                   product_LinkList.add(product_link);                                
	                  
	                }else if(row.indexOf(shopurl) != -1){ //만약에 링크만 있고 한글이 없으면 이미지 링크도 있으니 이미지 링크 추출                    
	              	  product_ImageLink= searchImgLink(row, shopurl);      //상품 이미지 링크
	                   product_ImgLinkList.add(product_ImageLink);
	                }
	             }                    
	          }           
	          //돈 추가
	          	         
	          if(oneBoxProduct_price.size() > 0 ){
	        	  if(oneBoxProduct_price.get(0).length() > 6){
	        		  oneBoxProduct_price.set(0,oneBoxProduct_price.get(0).substring(0, 7));
	        	  }        	  
	        	  product_PriceList.add(oneBoxProduct_price.get(0)+"원");     
	        	  oneBoxProduct_price = oneBoxProduct_price_reset;
	          }else if(oneBoxProduct_price.size() == 0){
	        	  product_PriceList.add("");
	          }
	       }	         	      
	       
	       //중복제거 구문            
	       //이미지링크 중복제거
	       List<String> ImgresultList = new ArrayList<String>();
	       for (int i = 0; i < product_ImgLinkList.size(); i++) {
	           if (!ImgresultList.contains(product_ImgLinkList.get(i))) {
	          	 ImgresultList.add(product_ImgLinkList.get(i));
	           }
	       }
	       product_ImgLinkList = ImgresultList;
	       
	       
	     //상품이름 중복제거
	       List<String> NameresultList = new ArrayList<String>();
	       for (int i = 0; i < product_NameList.size(); i++) {
	           if (!NameresultList.contains(product_NameList.get(i))) {
	          	 NameresultList.add(product_NameList.get(i));
	           }
	       }
	       product_NameList = NameresultList;
	       
	       
	     //상품링크 중복제거
	       List<String> LinkresultList = new ArrayList<String>();
	       for (int i = 0; i < product_LinkList.size(); i++) {
	           if (!LinkresultList.contains(product_LinkList.get(i))) {
	          	 LinkresultList.add(product_LinkList.get(i));
	           }
	       }
	       product_LinkList = LinkresultList;
	       
	      
	       
	       //중복제거하고 구문 처리
	       System.out.println("카테고리 결과 검색중.......");
	       System.out.println("링크 갯수 : "+product_LinkList.size());
	       System.out.println("이름갯수 : " + product_NameList.size());
	       System.out.println("가격 갯수 : " + product_PriceList.size());
	       System.out.println("이미지 갯수 : " + product_ImgLinkList.size());	       
	       int saveSize = product_LinkList.size();
	       if(saveSize > product_NameList.size()){
	    	   saveSize = product_NameList.size();
	       }else if(saveSize > product_PriceList.size()){
	    	   saveSize = product_PriceList.size();
	       }else if(saveSize > product_ImgLinkList.size()){
	    	   saveSize = product_ImgLinkList.size();
	       }
	       
	       for(int i=0; i<saveSize; i++){
	      	Product pro  = new Product(shopurl, product_LinkList.get(i), product_NameList.get(i), product_PriceList.get(i), product_ImgLinkList.get(i));
	      	main_product_list.add(pro);	     
	       }
	       
	       run();
	   }
	
	//물품 바로가기 링크 페이지 인식하면 이미지링크 추출
    public String searchImgLink(String row, String shopurl){        
    	String pattern[] = {".gif\"",".jpg\"",".png\""};
    	
        int productImgLinkRow = row.indexOf("<img src");        //물품 바로가기에서 옷 이미지 링크 줄번호 가져오기

        String productLinkResult = "";        //물품 바로가기에서 옷 이미지 링크 가져오기
        if( productImgLinkRow != -1){
           String productLinkRow = row; //ex ) aaaaA<img src = "dddddd">ddd
           String productLinkRow_Frist_Cut = productLinkRow.substring(productImgLinkRow, productLinkRow.length()); //ex) <img src="ddddddd">ddd
           int productLinkRow_End_Cut_Index = -1;
           for(int i=0; i<pattern.length; i++){
        	   if( productLinkRow_Frist_Cut.indexOf(pattern[i]) != -1){
        	   productLinkRow_End_Cut_Index = productLinkRow_Frist_Cut.indexOf(pattern[i])+5; //끝값 식별 .gif .png .jpg
        	   }
           }
           productLinkResult = productLinkRow_Frist_Cut.substring(12, productLinkRow_End_Cut_Index-1); // <img src="ddddddd">
        }
        return productLinkResult;
    }
    
    public String searchProduct_Name(String row){
	   	 row = row.trim();
	     String start_Atag = "<a href=\"";
	     String end_Atag = "/>";
	     
	     int start_Atag_index = row.indexOf(start_Atag);
	     int end_Atag_index = -1;
	     String startRow = row.substring(start_Atag_index,row.length());
	     end_Atag_index = startRow.indexOf(end_Atag);
	     
	     
	     if(start_Atag_index < end_Atag_index){
	    	 startRow = row.substring(start_Atag_index, end_Atag_index);
	     }
	     
	     String product_name = startRow.replaceAll("[^가-힣]", ""); //상품 이름
	     return product_name;
    }
    
    public String searchProduct_Link(String row,String shopurl){
	   	 row = row.trim();
	     String start_Atag = "<a href=\"";
	     String end_Atag = ">";	     	

	     int start_Atag_index = row.indexOf(start_Atag);
	     String startRow = row.substring(start_Atag_index,row.length());
	     int end_Atag_index = startRow.indexOf(end_Atag);

	     String total_Atag = row.substring(start_Atag_index, end_Atag_index);
	                                      
	     String product_link = total_Atag.substring(10, total_Atag.length()-1); //상품 링크
	     product_link = shopurl + product_link;
	     
	     
	     if(product_link.indexOf("name=") != -1 ){
	    	 product_link = product_link.substring(0, product_link.indexOf("name="));
	     }
	     
	     
	     if(product_link.indexOf("\"") != -1){
	    	 product_link = product_link.substring(0, product_link.length()-2);
	     }
	     
	     //4.25 5:57수정
	     if(product_link.indexOf("\" class") != -1){
	    	 product_link = product_link.substring(0, product_link.indexOf("\" class"));
	     }
	     
	     return product_link;
	}
}
