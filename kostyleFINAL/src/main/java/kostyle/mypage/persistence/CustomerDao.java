package mypage.model;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mypage.action.ModifySuccess;
import mypage.mapper.CustomerMapper;

public class CustomerDao {
	private static CustomerDao dao = new CustomerDao();
	
	public static CustomerDao getInstance() {
		return dao;
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream(resource);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new SqlSessionFactoryBuilder().build(in);
	}
	
	public int withdrawalCustomer(Customer customer) throws Exception {
		SqlSession session = getSqlSessionFactory().openSession();
		int re = -1;
		
		try {
			re = session.getMapper(CustomerMapper.class).withdrawalCustomer(customer);
			if(re > 0) {
				session.commit();
			}else {
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return re;
	}
	
	public int modifyCheckCustomer(Customer customer) throws Exception {
		
		SqlSession session = getSqlSessionFactory().openSession();
		int re = -1;
		
		
		try {
			re = Integer.parseInt(session.getMapper(CustomerMapper.class).modifyCheckCustomer(customer));

		} catch(Exception e){
			e.printStackTrace();
			
		} finally {
			session.close();
		}
		
		return re;
		
	}
	
	public Customer modifyCustomer(String c_num) throws Exception {
		
		SqlSession session = getSqlSessionFactory().openSession();
		
		
		try {
			return session.getMapper(CustomerMapper.class).modifyCustomer(c_num);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			session.close();
		}
		
	}
	
	public void ModifySuccess(Customer customer) throws Exception {
		SqlSession session = getSqlSessionFactory().openSession();
		System.out.println("내용:"+customer);
		int re = -1;
		try {
			re = session.getMapper(CustomerMapper.class).ModifySuccess(customer); //우리가 만든 BoardMapper.class를 등록
			//1이 넘어오면 입력이 제대로 된 것 / -1은 제대로 안된 것
			if(re>0){
				session.commit();//입력이 잘 됐을 때
			}else{
				session.rollback();//입력이 잘 안됐을 때
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
