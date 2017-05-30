package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.LoginDao;
import login.model.AdShopMember;

public class loginShopAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDao dao = LoginDao.getInstance();
		AdShopMember adShoppingMall = new AdShopMember();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		int ad_id = Integer.parseInt(request.getParameter("ad_id"));
		String ad_pass = null;
		
		
		if(dao.checkShopId(ad_id)== 1){
			adShoppingMall.setAd_id(ad_id);
			adShoppingMall.setAd_pass(ad_pass = request.getParameter("ad_pass"));
			
			System.out.println("내가 입력한 AdShopMember: "+adShoppingMall);
			
			AdShopMember adShop = dao.selectShop(adShoppingMall);
			System.out.println("dao로 구한 AdShopMember값: "+adShop);
			session.setAttribute("s_num", adShop.getS_num());
			
			forward.setRedirect(true);
			forward.setPath(request.getParameter("../login/loginShop.jsp"));
			
		}else if(dao.checkShopId(ad_id)==0){
			session.setAttribute("msg", "해당하는 아이디가 존재하지 않습니다.");
			forward.setRedirect(false);
			forward.setPath("../login/loginShop.jsp");			
		}else{
			session.setAttribute("msg", "어떤 오류인지 알 수 없습니다.");
			
			forward.setRedirect(false);
			forward.setPath("../login/loginShop.jsp");
		}
		
		
		return forward;
		
		
		
	}

}//class
