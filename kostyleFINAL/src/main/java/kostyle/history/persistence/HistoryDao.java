package history.model;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import history.mapper.HistoryMapper;

public class HistoryDao {
	private static HistoryDao dao = null;
	public static HistoryDao getInstance() {
		if(dao == null){
			dao = new HistoryDao();
		}return dao;
	}
	public SqlSessionFactory getFactory(){
		String resource = "mybatis-config.xml";
		InputStream in= null;
		try {
			in = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}	return new SqlSessionFactoryBuilder().build(in);	
	}
	
	public List<History> listHistory(int c_num) {
		SqlSession session = getFactory().openSession();
		List<History> list = null;
		try {
			list = session.getMapper(HistoryMapper.class).listHistory(c_num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return list;
	}
	public int countHistory(int c_Num) {
		SqlSession session = getFactory().openSession();
		int re = 0;
		try {
			re = session.getMapper(HistoryMapper.class).countHistory(c_Num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		} return re;
		
	}
	public void deleteHistory(int h_num) {
		SqlSession session = getFactory().openSession();
		int re = 0;
		try {
			re = session.getMapper(HistoryMapper.class).deleteAction(h_num);
			if(re>0){
				session.commit();
			}else{
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public void insertHistory(History history) {
		SqlSession session = getFactory().openSession();
		int re =-1;
		try {
			re = session.getMapper(HistoryMapper.class).insertHistory(history);
			if(re>0){
				session.commit();
			}else{
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public String getHistoryNum() {
		SqlSession session = getFactory().openSession();
		String h_num = "";
		try {
			h_num = session.getMapper(HistoryMapper.class).getHistoryNum();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return h_num;
	}
}
