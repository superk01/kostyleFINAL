package favorite.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class FavoriteService {

	private static FavoriteService service = new FavoriteService();
	private static FavoriteDao dao;
	private static int page_size = 7;
	

	public static FavoriteService getInstance(){
		dao = FavoriteDao.getInstance();
		return service;
	}
	
	//listFavorite
		public ListModel listFavoriteService(HttpServletRequest request, int requestPage){

			HttpSession session = request.getSession();
			String c_num = (String)(session.getAttribute("c_num"));
			
					
			int totalCount = dao.countFavorite(c_num);
			int totalPageCount = totalCount / page_size;
			if(totalCount % page_size > 0 ){
				totalPageCount ++;
			}
			
			int startPage = requestPage-(requestPage-1)%5;
			int endPage = startPage + 4;
			if(endPage > totalPageCount){
				endPage = totalPageCount;
			}
			
			int startRow = (requestPage-1)*page_size;
			
			List<Favorite> list = dao.listFavorite(c_num, startRow);
			
			
			return new ListModel(list, requestPage, totalPageCount, startPage, endPage);
		}
		
	//detailComent
		public Favorite detailComentService(String f_num){
			return dao.detailComent(f_num);
		}
	
	//updateComent
		public int updateComentService(Favorite favorite){
			return dao.updateComent(favorite);
		}
		
		
	//deleteFavorite
		public int deleteFavoriteService(Favorite favorite){
			return dao.deleteFavorite(favorite);
		}
		
	
	
}
