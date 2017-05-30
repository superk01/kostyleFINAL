package Category.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import Category.mapper.CategoryMapper;

public class CategoryDao {
	static CategoryDao dao = new CategoryDao();
	
	public static CategoryDao getDao(){
		return dao;
	}
	
	
	public SqlSessionFactory getSqlSessionFactory(){
		String resource = "mybatis-config.xml";
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SqlSessionFactoryBuilder().build(in);
	}
	
	public List<Categorysmall> categorysmallSelect(String midnum){
		SqlSession session = getSqlSessionFactory().openSession();
		List<Categorysmall> list = new ArrayList<Categorysmall>();
		try {
			list = session.getMapper(CategoryMapper.class).categorysmallSelect(midnum);
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
		
	}


	public List<String> categorymidSelect(String keyword) {
		SqlSession session = getSqlSessionFactory().openSession();
		List<String> list = new ArrayList<String>();
		try {
			list = session.getMapper(CategoryMapper.class).categorymidSelect(keyword);
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
		
	}


	public List<Adshoppingmall> getAdShoppingmallJoin() {
		List<Adshoppingmall> adsList = new ArrayList<Adshoppingmall>();
		SqlSession session = getSqlSessionFactory().openSession();
		try {
			adsList = session.getMapper(CategoryMapper.class).getAdShoppingmallJoin();
			System.out.println(adsList.size());
			return adsList;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return adsList;
	}


	public List<Category> getCategoryList() {
		List<Category> categoryList = new ArrayList<Category>();
		SqlSession session = getSqlSessionFactory().openSession();
		try {
			categoryList = session.getMapper(CategoryMapper.class).getCategoryList();		
			return categoryList;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return categoryList;	
	}

	public List<PickProduct_List> getPickProduct_List(){
		List<PickProduct_List> pickProduct_List = new ArrayList<PickProduct_List>();
		SqlSession session = getSqlSessionFactory().openSession();
		try {
			pickProduct_List = session.getMapper(CategoryMapper.class).getPickProduct_List_s();		
			return pickProduct_List;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return pickProduct_List;		
	}
	
	public void insertCustomer_taste_stack(Customer_taste_stack ct){
		SqlSession session = getSqlSessionFactory().openSession();
		int result = -1;
		try {
			result = session.getMapper(CategoryMapper.class).insertCustomer_taste_stack(ct);
			if ( result > 0){
				session.commit();
			}else{
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public List<Customer_taste_Select> getCustomer_taste_Select(String c_num) {
		SqlSession session = getSqlSessionFactory().openSession();
		List<Customer_taste_Select> list = new ArrayList<Customer_taste_Select>();
		try {
			list= session.getMapper(CategoryMapper.class).getCustomer_taste(c_num);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return list;
		}finally{
			session.close();
		}
	}
	
	public void insertCategoryList(String data){
		SqlSession session = getSqlSessionFactory().openSession();
		int result = -1;
		try {
			result = session.getMapper(CategoryMapper.class).insertCategoryList(data);
			if ( result > 0){
				session.commit();
			}else{
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
	
	public List<Category> getCategorylist_brother(){
		List<Category> categoryList = new ArrayList<Category>();
		SqlSession session = getSqlSessionFactory().openSession();
		try {
			categoryList = session.getMapper(CategoryMapper.class).getCategorylist_brother();		
			return categoryList;
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
		return categoryList;
	}
	
	public void click_CategoryList(String data){
		SqlSession session = getSqlSessionFactory().openSession();
		int result = -1;
		try {
			result = session.getMapper(CategoryMapper.class).click_CategoryList(data);
			if ( result > 0){
				System.out.println("커밋");
				session.commit();
			}else{
				System.out.println("실패");
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
	}
}
