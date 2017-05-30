package join.model;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import join.mapper.JoinMapper;

public class JoinDao {

private static JoinDao dao = new JoinDao();
	
	public static JoinDao getInstance(){
		return dao;
	}
	
	
	public SqlSessionFactory getsqlSessionFactory(){
		String resource = "mybatis-config.xml";
		
		InputStream input = null;
		
		try {
			input = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SqlSessionFactoryBuilder().build(input); 
	}
	
	public int insertJoin(Join join){
		int re = -1;
		SqlSession sqlSession = getsqlSessionFactory().openSession();
		
		try {
			re = sqlSession.getMapper(JoinMapper.class).insertJoin(join);
			if(re > 0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return re;
	}
	
	public String autoNum(){
		SqlSession sqlSession = getsqlSessionFactory().openSession();
		try {
			if(sqlSession.getMapper(JoinMapper.class).autoNum() == null){
				return "0";
			}else{
				return sqlSession.getMapper(JoinMapper.class).autoNum();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}finally {
			sqlSession.close();
		}
	}
		
	public int overlapId(Join join){
		int re = -1;
		SqlSession sqlSession = getsqlSessionFactory().openSession();		
		try {
			re = sqlSession.getMapper(JoinMapper.class).overlapId(join);
			if(re > 0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return re;
	}	
	
	
}
