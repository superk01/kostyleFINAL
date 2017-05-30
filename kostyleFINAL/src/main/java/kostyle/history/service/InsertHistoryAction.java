package history.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import history.model.History;
import history.model.HistoryDao;
import stats.model.HitCount;
import stats.model.ShoppingMall;
import stats.model.StatsDao;

public class InsertHistoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("InsertHistoryAction시작");
		
		String product_link=request.getParameter("product_link");
		String product_ImageLink=request.getParameter("product_ImageLink");
		String product_name=request.getParameter("product_name");
		String product_price=request.getParameter("product_price");
		
		HttpSession session = request.getSession();
		
		String c_num = (String)session.getAttribute("c_num");
		
		
		
		System.out.println("InsertHistoryAction-product_link:"+product_link);
		System.out.println("InsertHistoryAction-product_ImageLink:"+product_ImageLink);
		System.out.println("InsertHistoryAction-product_name"+product_name);
		System.out.println("InsertHistoryAction-product_price"+product_price);
		
		History history = new History();
		history.setH_Imgurl(product_ImageLink);
		history.setH_Name(product_name);
		history.setH_Prdurl(product_link);
		history.setC_Num(c_num+"");
		
		
		HistoryDao dao = HistoryDao.getInstance();
		String str = dao.getHistoryNum();
		System.out.println("InsertHistoryAction-str:"+str);
		if(str==null){
			str="h_000";
		}
				
		String str1= str.substring(2, 5);
		System.out.println("InsertHistoryAction-str1:"+str1);
		int num = Integer.parseInt(str1)+1;
		System.out.println("InsertHistoryAction-num:"+num);
		String h_num = "";
		if(num<10){
			h_num = "h_00"+num;
		}else if( num < 100){
			h_num = "h_0"+num;
		}else{
			h_num = "h_"+num;
		}
		System.out.println("InsertHistoryAction-h_num:"+h_num);
		history.setH_Num(h_num);
		dao.insertHistory(history);
		
		  //----------------------------------------------add for stats
	      StatsDao statsDao = StatsDao.getInstance();
	      HitCount hitCount = new HitCount();
	      ShoppingMall shoppingMallJ = new ShoppingMall();
	      
	      String cnt_prdurl = product_link.substring(7);
	      String shop[] = cnt_prdurl.split("/");
	      String s_shopurl = shop[0]+"/";	      	
	      
	      shoppingMallJ = statsDao.getSNum(s_shopurl);
	      
	      hitCount.setS_num(shoppingMallJ.getS_num());
	      hitCount.setCnt_prdurl(cnt_prdurl);
	      hitCount.setC_num(c_num);
	      
	      statsDao.insertHitCount(hitCount);
	      
	      
	      //----------------------------------------------add for stats
		
		return null;
	}

	/*@Override
	public void executeVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
	}*/

}
