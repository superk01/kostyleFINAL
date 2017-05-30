package kostyle.help.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Customer.action.Action;
import Customer.action.ActionForward;
import Customer.model.AdShoppingMall;
import Customer.model.C_BoardDAO;

public class WriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		C_BoardDAO dao = C_BoardDAO.getInstance();
		List<AdShoppingMall> list = dao.adShoppingMallList();
		System.out.println("WriteAction:"+list);
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../main/template.jsp?body=../customer/write.jsp");
		return forward;
	}

}
