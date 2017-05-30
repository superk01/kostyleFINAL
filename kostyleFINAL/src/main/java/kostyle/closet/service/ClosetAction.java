package closet.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class ClosetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		ClosetPrd closetPrd = new ClosetPrd();
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		List<ClosetPrd> cloList = new ArrayList<ClosetPrd>();
		List<Closet> cloTab= new ArrayList<Closet>();
		
		//로그인이 되어있지 않으면 우선 로그인부터!
		if(session.getAttribute("c_num")==null){
			forward.setRedirect(true);
			forward.setPath("login/loginCustomer.jsp");
		}else{
			request.removeAttribute("select_clo_num"); //일단 비워야 이전값이 남지않음.
			System.out.println("클로젯액션의 clo_num은? : "+request.getParameter("clo_num"));
			
			//System.out.println("session.getAttribute(clo_num): "+ session.getAttribute("clo_num"));
			if(request.getParameter("clo_num").equals("0")){
				
				System.out.println("ClosetAction clo_num == full진입");
				closetPrd.setC_num((String)session.getAttribute("c_num"));
				System.out.println("보낸 ClosetPrd정보: "+closetPrd);
				cloList = dao.fullCloset(closetPrd);
				System.out.println("받은 옷장: "+cloList);
				
				//우선 DB에서 closetPrd리스트를 받은 후에, 해당하는 상품의 옷장이름도 추가해야함.
				for(int i=0; i<cloList.size(); i++){
					Closet closet = new Closet();
					closet.setC_num(cloList.get(i).getC_num());
					closet.setClo_num(cloList.get(i).getClo_num());
					String clo_name = dao.cloNumTocloName(closet);
					System.out.println("받아온 clo_name: "+ clo_name);
					cloList.get(i).setClo_name(clo_name);
				}
				
				request.setAttribute("select_clo_num", "0");
				
		
				
			}else{
				System.out.println("ClosetAction clo_num != full진입");
				closetPrd.setC_num((String)session.getAttribute("c_num"));
			
				System.out.println("셀렉터클로젯액션의 c_num: "+ session.getAttribute("c_num"));
				closetPrd.setC_num((String)session.getAttribute("c_num"));
				closetPrd.setClo_num(Integer.parseInt(request.getParameter("clo_num")));		
				System.out.println("셀렉터클로젯액션 보낸closetPrd: "+closetPrd);
				cloList = dao.selectCloset(closetPrd);
				System.out.println("받은 옷장: "+cloList);
				//우선 DB에서 closetPrd리스트를 받은 후에, 해당하는 상품의 옷장이름도 추가해야함.
				for(int i=0; i<cloList.size(); i++){
					Closet closet = new Closet();
					closet.setC_num(cloList.get(i).getC_num());
					closet.setClo_num(cloList.get(i).getClo_num());
					String clo_name = dao.cloNumTocloName(closet);
					System.out.println("받아온 clo_name: "+ clo_name);
					cloList.get(i).setClo_name(clo_name);
				}
				
				request.setAttribute("select_clo_num", request.getParameter(("clo_num"))+"");
				
				//List<ClosetPrd> cloList = dao.selectCloset(closetPrd);
			}
			//최초실행시 전체옷장으로 진입하게했지만, 무한대로 실행되는 것을 끊기위해
			request.setAttribute("trig", "not");
			System.out.println("action에서의 trig값: "+request.getAttribute("trig"));
			//cloTab = dao.closetList(session.getAttribute("c_num").toString());
			cloTab = dao.closetList((String)session.getAttribute("c_num"));
			System.out.println("받은 탭리스트: "+cloTab);

			request.setAttribute("cloList", cloList);
			request.setAttribute("closetTab", cloTab);
			
			forward.setRedirect(false);
			forward.setPath("main/template.jsp?body=../closet/closet.jsp");
		}
		
	
		//----------------
		
		/*List<Board> list = dao.listBoard(search);
		   request.setAttribute("list", list);
		   
		   ActionForward forward = new ActionForward();
		  forward.setRedirect(false);
		  forward.setPath("/list.jsp");*/
		
		return forward;
	}

}
