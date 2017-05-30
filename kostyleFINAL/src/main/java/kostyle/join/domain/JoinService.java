package join.model;

public class JoinService {

	private static JoinService service = new JoinService();
	private static JoinDao dao;

	public static JoinService getInstance(){
		dao = JoinDao.getInstance();
		return service;
	}
	
	public int insertJoinService(Join join){
		String num1 = dao.autoNum();//문자열 0
		int num2 = Integer.parseInt(num1);//숫자0
		int num3 = num2 + 1;//숫자1
		String num4 = num3 + "";//문자열 1
		
		join.setC_num(num4);
		
		System.out.println("서비스 : " + join.toString());
		return  dao.insertJoin(join);
	}
		
	public int overlapIdService(Join join){
		return  dao.overlapId(join);
	}
	
	
}
