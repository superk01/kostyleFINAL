package closet.action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.glassfish.external.statistics.impl.StatsImpl;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class CDSessionAttributeAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("실제세션함수진입!");
		HttpSession session = request.getSession();
		List<Closet> closetTab = new ArrayList<Closet>();
		
		//지울때는 이름과 삭제여부만존재. value는 create쪽 진입해서 선언.
		String attriName = request.getParameter("attriName");
		String attriCD = request.getParameter("attriCD");
		System.out.println(attriCD);
		
		if(attriCD.equals("delete")){
			System.out.println("deleteSessionAttri진입");
			session.removeAttribute(attriName);
			System.out.println("session.removeAttribute(attriName);");
		}else if(attriCD.equals("create")){
			System.out.println("createSessionAttri진입");
		
			String attriValue = request.getParameter("attriValue");
		
		 // Pattern p = Pattern.compile("(^[+-]?\\d+)(\\d{3})"); //정규표현식 
	      Pattern p1 = Pattern.compile("(clo_num=)(\\d*)[^,]"); //정규표현식 
	      Matcher m1 = p1.matcher(attriValue); 

	      Pattern p2 = Pattern.compile("(clo_name=)[^]]*"); //정규표현식 
	      Matcher m2 = p2.matcher(attriValue); 
	      
	      Pattern p3 = Pattern.compile("(c_num=)[^,]*"); //정규표현식 
	      Matcher m3 = p3.matcher(attriValue); 
	      
	      List<Integer> clo_nums = new ArrayList<Integer>();
	      List<String> clo_names = new ArrayList<String>();
	      String c_num = "";
	      
	      while(m1.find()){
	    	  int clo_num;
	    	  	//System.out.println("매치되는 clo_num찾음.");
				String temp = m1.group(); //매칭된부분 전체반환
				temp = temp.trim();
				temp = temp.replaceAll("clo_num=", "");
				clo_num = Integer.parseInt(temp);
				System.out.println("list객체에 넣을 clo_num=" +clo_num);
				clo_nums.add(clo_num);
		  }
	      
	      while(m2.find()){
	    	  //System.out.println("매치되는 clo_name찾음.");
	    	  String clo_name = m2.group(); 
	    	  clo_name = clo_name.trim();
	    	  clo_name = clo_name.replaceAll("clo_name=", "");
	    	 System.out.println("list객체에 넣을 clo_name:" +clo_name);
	    	  clo_names.add(clo_name);
	      }
	      if(m3.find()){
	    	  //System.out.println("매치되는 c_num찾음.");
	    	  c_num = m3.group();
	    	  c_num = c_num.trim();
	    	  c_num = c_num.replaceAll("c_num=", "");
	    	  System.out.println("변환한 c_num: "+c_num);
	      }
	      
	      if(clo_nums.size() == clo_names.size()){
	    	  System.out.println("clo_nums.size == clo_names.size");
	    	  for(int i=0; i<clo_nums.size(); i++){
	    		  closetTab.add(new Closet(clo_nums.get(i),c_num,clo_names.get(i)));
	    		  System.out.println("closetTab정보: "+closetTab);
	    	  }
	      }
	      
	      for(int i=0; i<closetTab.size(); i++){
	    	  System.out.println(closetTab.get(i));
	      }
		//System.out.println("List변환: value: "+attriValue);
		/*if(attriValue instanceof List){
			attriValue = (List)request.getAttribute("attriValue");
			System.out.println("변환한attriValue: "+attriValue);
		}*/
		
	      //최종목적인 세션에 저장. foreach작동하도록.
			session.setAttribute(attriName, closetTab);
			System.out.println("session.setAttribute(attriName, closetTab)실행");
			System.out.println("값: "+session.getAttribute("closetTab"));
	}	
		try {
			//에이작스에 보낼 데이터 심기.
			response.getWriter().print(session.getAttribute(attriName));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
