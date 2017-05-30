package closet.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class TabClosetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		Closet closet= new Closet();
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		List<Closet> cloTab= new ArrayList<Closet>();
		
		request.setAttribute("cloTab", "");

		return forward;
	}

}
