package login.model;

import java.io.InputStream;
import java.sql.SQLPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import login.mapper.LoginMapper;




public class LoginDao {
	private static LoginDao dao = new LoginDao();	
	
	public static LoginDao getInstance(){
		return dao;
	}

	public SqlSessionFactory getSqlSessionFactory(){
		String resource = "mybatis-config.xml"; // 클래스패스가 src라서 src내부에 넣어서 이렇게만해도 가능
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream(resource);	//Resources는 org.아파치.아이바티스
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new SqlSessionFactoryBuilder().build(in); //SqlSessionFactory생성
	}
	
	
//Dao함수시작	
	
	public int checkCustomerId(String c_id){
		int re= -1;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			re= sqlSession.getMapper(LoginMapper.class).checkCustomerId(c_id);
			System.out.println("ID체크. re값: "+re);
			if(re == 1){
				re = 1;
			}else if(re==0){
				re = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return re;
	}
	public CustomerMember selectCustomer(CustomerMember cus){
		CustomerMember customer = null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		System.out.println("아이디조회 성공+ 로그인시도: "+cus);
		try {
			customer= sqlSession.getMapper(LoginMapper.class).selectCustomer(cus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
		
	}
	
	public int checkShopId(int ad_id){
		int re= -1;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			re= sqlSession.getMapper(LoginMapper.class).checkShopId(ad_id);
			
			System.out.println("ID체크. re값: "+re);
			if(re == 1){
				re = 1;
			}else if(re==0){
				re = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
	public AdShopMember selectShop(AdShopMember s){
		AdShopMember shop = null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			shop= sqlSession.getMapper(LoginMapper.class).selectShop(s);
			System.out.println("dao에서 받은 shop정보: "+shop);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return shop;
		
	}
/*	public int insertBoard(LoginBoard board){
		int re = -1;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			re = sqlSession.getMapper(LoginMapper.class).insertBoard(board);
			if(re > 0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		
		return re;
		
	}*/
/*	
	public int selectB_id(){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try {
			if(sqlSession.getMapper(BoardMapper.class).selectB_id() == null){
				//row가 하나도 없을 때에는, null이 반환되기때문에 0으로 표현해주어야 1을 만들 수 있다.
				return 0;
			}else{
				return sqlSession.getMapper(BoardMapper.class).selectB_id() ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			sqlSession.close();
		}
	}
	*/
/*	
	public List<LoginBoard> listBoard(int startRow){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<LoginBoard> list = null;
		try {
			list = sqlSession.getMapper(PcikMapper.class).listBoard(new RowBounds(startRow, 12));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	
	public LoginBoard selectBoard(int b_id){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		LoginBoard board= null;
		try {
			board =sqlSession.getMapper(PcikMapper.class).selectBoard(b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return board;
	}*/
	
/*	public void updateHit(int b_id){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Board board= null;
		int re = -1;
		try {
			re = sqlSession.getMapper(BoardMapper.class).updateHit(b_id);
			if( re >0 ){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	
	
	 public void updateStep(HashMap<String, Integer> map){
		 SqlSession sqlSession = getSqlSessionFactory().openSession();
			Board board= null;
			int re = -1;
			
			try {
				re = sqlSession.getMapper(BoardMapper.class).updateStep(map);
				if( re > 0 ){
					sqlSession.commit();
				}else{
					sqlSession.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
	 }
	 
	 public void updateBoard(Board board){
		 SqlSession sqlSession = getSqlSessionFactory().openSession();
			int re = -1;
		
			try {
				re = sqlSession.getMapper(BoardMapper.class).updateBoard(board);
				if( re > 0 ){
					sqlSession.commit();
				}else{
					sqlSession.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
	 } */

		/*public boolean checkPass(int b_id, String pass){
			System.out.println("id값 : " +b_id);
			Board board= null;
			board = dao.selectBoard(b_id);
			System.out.println(board);
			
			String password = board.getB_pwd();
			if(password.equals(pass)){
				return true;
			}else{
				return false;
			}
	 } */
	 
	/*	public int countBoard(){
			SqlSession sqlSession = getSqlSessionFactory().openSession();
			int re =0;
			try {
				re = sqlSession.getMapper(PcikMapper.class).countBoard();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			
			return re;
		}
	 
		
		public void deleteBoard(LoginBoard board) throws Exception{
			//client.update("deleteBoard",board);
			SqlSession session = getSqlSessionFactory().openSession();
			int re= -1;
			try {
				re = session.getMapper(PcikMapper.class).deleteBoard(board);
			
				if(re > 0){
					session.commit();
				}else{
					session.rollback();
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				 session.close();
			}
		}
		
*/
	 
	 
	 
	 
}	//end class