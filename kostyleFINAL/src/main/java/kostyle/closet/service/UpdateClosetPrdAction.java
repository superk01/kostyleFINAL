package closet.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class UpdateClosetPrdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		ClosetPrd closetPrd = new ClosetPrd();
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		List<ClosetPrd> cloList = new ArrayList<ClosetPrd>();
		List<Closet> cloTab= new ArrayList<Closet>();

		return forward;
	}

}
