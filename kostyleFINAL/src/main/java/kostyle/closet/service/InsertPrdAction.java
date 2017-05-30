package closet.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class InsertPrdAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		ClosetPrd closetPrd = new ClosetPrd();
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		List<ClosetPrd> cloList = new ArrayList<ClosetPrd>();
		List<Closet> cloTab= new ArrayList<Closet>();
		int re = -1;
		
		//1.prdUrl 정상으로 냅두고 해시맵을 구한 후에, http://잘라야함.
		String clo_prdUrl = request.getParameter("prdUrl");
		
		//5. clo_price , imgUrl , prdName
			/*
			hash.put("imgUrl", imgUrl);
			hash.put("price", price);
			hash.put("prdName", prdName); 한 결과를 넘겨받음.*/
		HashMap hash = dao.urlRepair(clo_prdUrl);
		String clo_price = (String) hash.get("price");
		String clo_imgUrl = (String) hash.get("imgUrl");
		String clo_prdName = (String) hash.get("prdName");
		System.out.println("받아온 해시맵clo_price: "+clo_price);
		System.out.println("받아온 해시맵imgUrl: "+clo_imgUrl);
		System.out.println("받아온 해시맵 prdName: "+clo_prdName);
		
		//2.s_sname
		String shopUrl = dao.getShopUrl(clo_prdUrl);
		System.out.println("InsertClosetPrd얻어온 shopUrl: "+shopUrl);
		String s_sname =dao.getS_name(shopUrl);
		System.out.println("InsertClosetPrd얻어온 s_sname: "+s_sname);
		
		
		//4.clo_detail_num생성
		int max_detail_num = dao.max_detail_num();
		System.out.println("구해온 max_detail_num: "+max_detail_num);
		
		//마지막-1.prdUrl의 http://자르기(순서상 다른것들 구한 후에 해야함.)
		clo_prdUrl = dao.prdUrlRepair(clo_prdUrl);
		System.out.println("InsertClosetPrd의 prdUrl: "+clo_prdUrl);
		

		//6. 미친건지.. c_num이 인식이 안된다! Integer로 잡혀서 일단은 인티저->스트링으로 변환
		System.out.println("c_num변환: "+ request.getSession().getAttribute("c_num").toString());
		String c_num = request.getSession().getAttribute("c_num").toString();
		//String c_num =(String)request.getSession().getAttribute("c_num");
		//System.out.println("c_num캐스팅...테스트 String "+c_num);
		
		
		
		//마지막.zzim갯수(순서상 prdUrl가공 후에 해야함. prdUrl과 c_num이 들은 ClosetPrd객체 필요)
		closetPrd.setC_num(c_num);
		closetPrd.setClo_prdUrl(clo_prdUrl);		
		int count_zzim = dao.count_zzim(closetPrd) +1; //자기자신포함위해 +1
		
		
		closetPrd.setClo_detail_num(max_detail_num +1);
		closetPrd.setClo_imgUrl(clo_imgUrl);
		closetPrd.setClo_prdName("임시상품명");
		closetPrd.setClo_price(clo_price); 
		closetPrd.setS_sname(s_sname); //db조회하는것 나중에 xml파일엮으면 그때. 지금은 일단 함수로.
		closetPrd.setClo_zzim(count_zzim); //만약 hit가 조회가안된다면 기본은 0.
		
		closetPrd.setClo_num(1); //사실 무조건 1(기본폴더) db자동입력 .
		//closetPrd.setClo_date(clo_date); 날짜는 안해도된다. sysdate로 db자동입력.
		//이걸 다 해야!!! 추가할 수 있어!!
		
		System.out.println("현재까지 완성된 clostPrd: "+closetPrd);
		re = dao.insertClosetPrd(closetPrd);
		
		try {
			//에이작스에 보낼 데이터 심기.
			response.getWriter().print(re);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
