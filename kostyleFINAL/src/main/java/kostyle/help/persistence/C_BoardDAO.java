package kostyle.help.persistence;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import Customer.mapper.C_BoardMapper;



public class C_BoardDAO {
	//�떛湲��넠 �뙣�꽩
	private static C_BoardDAO dao;
	private C_BoardDAO(){}
	public static C_BoardDAO getInstance() {
		if(dao==null){
			dao=new C_BoardDAO();
		}
		return dao;
	}
	//?��?��?��?���??
	public SqlSessionFactory getFactory() {
		String resource="mybatis-config.xml";
		InputStream is=null;
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}return new SqlSessionFactoryBuilder().build(is);
	}
	//리스?��
	public List<C_Board> list(Search search, int startRow){
		List<C_Board> list=null;
		SqlSession session = getFactory().openSession();
		try {
			list = session.getMapper(C_BoardMapper.class).list(search,new RowBounds(startRow,3));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}return list;
	}
	public List<C_Board> seachMineBoard(int c_num){
		List<C_Board> list = null;
		SqlSession session = getFactory().openSession();
		try {
			list = session.getMapper(C_BoardMapper.class).searchMineBoard(c_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return list;
	}
	//q_Num?�� 최�?�?? 구하�??
	public int addQ_Num(){
		SqlSession session = getFactory().openSession();
		
		try {
			if(session.getMapper(C_BoardMapper.class).add()==null){
				return 0;
			}else{
				return session.getMapper(C_BoardMapper.class).add();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			session.close();
		}
		
		
	}
	//고객문의 게시?��?�� �?? 추�?
	public void insertBoard(C_Board board, int c_num) {
		
		int num = addQ_Num();
		board.setQ_Num(num+1);
		board.setC_Id(getC_Id(c_num));
		/*System.out.println(board.getQ_Num());*/
		SqlSession session = getFactory().openSession();
		int re = -1;
		try {
			re = session.getMapper(C_BoardMapper.class).insertBoard(board);
			if(re>0){
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
	//게시?�� �?? 목록?�� �???�� ?��목을 ?���???��?��?�� ?��?��?��?�� �???�� 객체?��?���?? 구함.
	public C_Board detailBoard(int q_num) {
		SqlSession session = getFactory().openSession();
		C_Board board = null;
		try {
			board=session.getMapper(C_BoardMapper.class).detailBoard(q_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	public void UpdateBoard(C_Board board) {
		SqlSession session = getFactory().openSession();
		int re =-1;
		try {
			re = session.getMapper(C_BoardMapper.class).updateBoard(board);
			if(re>0){
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
	//?��?��
	public void deleteBoard(int q_num) {
		SqlSession session = getFactory().openSession();
		int re =-1;
		try {
			re = session.getMapper(C_BoardMapper.class).deleteBoard(q_num);
			if(re>0){
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
	//고객 고유?�� 번호�?? �??�??�?? 고객?�� id�?? 구함.
	public String getC_Id(int c_num) {
		SqlSession session= getFactory().openSession();
		String c_Id = null;
		try {
			c_Id = session.getMapper(C_BoardMapper.class).getC_Id(c_num);
			/*System.out.println("C_BoardDAO:"+c_Id);*/
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}return c_Id;
	}
	//?���?? ?��?��블에 �?? 추�?.
	public void insertAnswer(Answer answer) {
		SqlSession session = getFactory().openSession();
		int num = addAs_Num();
		int as_Num = num+1;
		System.out.println("addAs-Num():"+num);
		System.out.println("as_Num : "+as_Num);
		answer.setAs_num(as_Num+"");
		/*System.out.println("insertAnswer :"+answer);*/
		int re = -1;
		
		try {
			re = session.getMapper(C_BoardMapper.class).insertAnswer(answer);
			if(re>0){
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
	//as_Num?�� 최�?값을 구함
	public int addAs_Num() {
		SqlSession session = getFactory().openSession();
		/*String re = null;*/
	
		try {
			if(session.getMapper(C_BoardMapper.class).addAs_Num()==null){
				return 0;
			}else{
				return (session.getMapper(C_BoardMapper.class).addAs_Num());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}	
	}
	//?���? 목록 보기
	public List<Answer> ListAnswer(int q_Num) {
		SqlSession session = getFactory().openSession();
		List<Answer> list = null;
		try {
			list = session.getMapper(C_BoardMapper.class).ListAnswer(q_Num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	public int countBoard(Search search) {
		SqlSession session = getFactory().openSession();
		int re =0;
		try {
			re = session.getMapper(C_BoardMapper.class).countBoard(search);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return re;
	}
	public List<C_Board> searchMine(int c_num) {
		SqlSession session = getFactory().openSession();
		List<C_Board> list = null;
		try {
			list = session.getMapper(C_BoardMapper.class).searchMineBoard(c_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return list;
	}
	public int countAnswer(int q_num) {
		SqlSession session = getFactory().openSession();
		int re = 0;
		try {
			re = session.getMapper(C_BoardMapper.class).countAnswer(q_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return re;
	}
	public List<AdShoppingMall> adShoppingMallList() {
		SqlSession session = getFactory().openSession();
		List<AdShoppingMall> list = null;
		try {
			list = session.getMapper(C_BoardMapper.class).adShoppingMallList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return list;
	}
	public String getS_Num(String selectshop) {
		SqlSession session = getFactory().openSession();
		String s_num= null;
		try {
			s_num = session.getMapper(C_BoardMapper.class).getS_Num(selectshop);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return s_num;
	}
	public String getC_Num(String c_Id){
		SqlSession session = getFactory().openSession();
		String c_Num = null;
		try {
			c_Num = session.getMapper(C_BoardMapper.class).getC_Num(c_Id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return c_Num;
	}
}
