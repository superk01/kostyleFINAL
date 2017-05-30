package closet.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;

public class SaveClosetAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		Closet closet = new Closet();
		HttpSession session = request.getSession();
		
		int re = -1;
		
		//받은값 : clo_nums 문자열
		System.out.println("request.getParameter_clo_nums값: "+request.getParameter("folder_clo_nums"));
		String folder_clo_nums = request.getParameter("folder_clo_nums");
		System.out.println("request.getParameter_폴더이름값: "+request.getParameter("closet_titles"));
		String closet_titles= request.getParameter("closet_titles");
		System.out.println("Save클로젯액션의 folder_clo_nums: "+folder_clo_nums);
		System.out.println("Save클로젯액션의 closet_titles: "+closet_titles);
		
		//배열 제대로된 형태로.
		String[] folder_clo_numArray = folder_clo_nums.split(",");
		String[] closet_titleArray = closet_titles.split(",");
		
		String c_num = (String)session.getAttribute("c_num");
		System.out.println("SaveCloset보낼c_num: "+c_num);
		
		int maxClo_num = dao.max_clo_num(c_num); 
		
		List<Integer> pWin_clo_numList = dao.cNumTocloNum(c_num);
		System.out.println("구해온 parentWin_clo_numList: "+pWin_clo_numList);
		
	//1빠로 폴더삭제부터.
	//먼저 dao에서 c_num으로 clo_num을 싹가져오게한다음에 비교 후 일치하면 dao쪽의 값을-1로 변경
		if(pWin_clo_numList.size() >= folder_clo_numArray.length){
			for(int i=0; i<pWin_clo_numList.size(); i++){
				for(int j=0; j<folder_clo_numArray.length; j++){
					if(pWin_clo_numList.get(i) == Integer.parseInt(folder_clo_numArray[j])){
						pWin_clo_numList.set(i, -1);
						break;
					}
				}
			}
		}else{
			for(int i=0; i<folder_clo_numArray.length; i++){
				for(int j=0; j<pWin_clo_numList.size(); j++){
					if(Integer.parseInt(folder_clo_numArray[i]) == pWin_clo_numList.get(j)){
						pWin_clo_numList.set(j, -1);
						break;
					}
				}
			}
		}
		
		for(int i=0; i<pWin_clo_numList.size(); i++){
			if(pWin_clo_numList.get(i) != -1){//-1인 것은 DB에 남아있어야하고, 아닌것은 삭제해야.
				Closet tempCloset = new Closet();
				int re1 = -1;
				int re2 = -2;
				tempCloset.setC_num(c_num);
				tempCloset.setClo_num(pWin_clo_numList.get(i));
				
				re1 = dao.deleteSameCloset_prds(tempCloset);
				re2 = dao.deleteCloset(tempCloset);
				System.out.println("폴더삭제결과값 PRDre1= "+re1+"Closet re2= "+re2);
			}
		}
	
	//폴더추가와 폴더이름수정
		if(folder_clo_numArray.length == closet_titleArray.length){
			closet.setC_num(c_num);//어차피c_num은 동일
			
			
			for(int i=0; i<folder_clo_numArray.length; i++){
				//해당사용자의 c_num으로 조회해온 옷장번호의 제일큰값보다 작으면 수정, 크면 추가
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

}
}//class
