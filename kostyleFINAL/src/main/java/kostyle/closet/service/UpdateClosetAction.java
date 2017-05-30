package closet.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class UpdateClosetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		Closet closet = new Closet();
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		int re = -1;
		//받은값 : clo_nums 문자열
		System.out.println("request.getParameter_clo_nums값: "+request.getParameter("folder_clo_nums"));
		String folder_clo_nums = (String)request.getParameter("folder_clo_nums");
		String closet_titles=(String)request.getParameter("closet_titles");
		System.out.println("Update클로젯액션의 folder_clo_nums: "+folder_clo_nums);
		System.out.println("Update클로젯액션의 clo_num: "+request.getParameter("clo_num"));
		System.out.println("Update클로젯액션의 closet_titles: "+closet_titles);
		
		String[] folder_clo_numArray = folder_clo_nums.split(",");
		String[] closet_titleArray = closet_titles.split(",");
		
		String c_num = (String)session.getAttribute("c_num");
		System.out.println("c_num이 잘못되었나? 보낼c_num: "+c_num);
		int maxClo_num = dao.max_clo_num(c_num);
		
		if(folder_clo_numArray.length == closet_titleArray.length){
			closet.setC_num(c_num);
			
			for(int i=0; i<folder_clo_numArray.length; i++){
				if(Integer.parseInt(folder_clo_numArray[i]) <= maxClo_num){
					System.out.println("clo_numArray쪼개기: "+folder_clo_numArray[i]);
					System.out.println("closet_titleArray쪼개기: "+closet_titleArray[i]);
					closet.setClo_num(Integer.parseInt(folder_clo_numArray[i]));
					closet.setClo_name(closet_titleArray[i]);
					re = dao.updateCloset(closet);
				}else{
					//maxClo_num보다 큰 폴더가 있다면 '폴더 새로 추가'
					closet.setClo_num(Integer.parseInt(folder_clo_numArray[i]));
					closet.setClo_name(closet_titleArray[i]);
					closet.setC_num((String)session.getAttribute("c_num"));
					System.out.println("새로 생성할 옷장카테고리정보: "+closet);
					re = dao.insertCloset(closet);
					System.out.println("insert결과re: "+re);
				}
			}
			
		}
		
		forward.setRedirect(false);
		forward.setPath("ClosetAction.closet");
		
		return forward;
	}

}
