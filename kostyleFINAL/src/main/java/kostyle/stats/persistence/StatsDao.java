package stats.model;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import stats.mapper.StatsMapper;

public class StatsDao {
	private static StatsDao dao = new StatsDao();
	
	public static StatsDao getInstance(){
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
	
	public void insertHitCount(HitCount hitCount) throws Exception{
		SqlSession session = getSqlSessionFactory().openSession();
		int re = -1;

		
		try {
			re = session.getMapper(StatsMapper.class).insertHitCount(hitCount);
			System.out.println(re+" succeed insert in dao");
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
	
	public List<HitCount> listHitCount() throws Exception{
		SqlSession session = getSqlSessionFactory().openSession();
		List<HitCount> list = null;
		try {
			list = session.getMapper(StatsMapper.class).listHitCount();
			System.out.println(list.get(0).getC_num()+" get list in dao");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	
	public ShoppingMall getSNum(String s_shopurl)throws Exception{
		SqlSession session = getSqlSessionFactory().openSession();
		ShoppingMall shoppingMall = null;
		
		System.out.println(s_shopurl+ " in dao for get snum");
		try {
			shoppingMall = session.getMapper(StatsMapper.class).getSNum(s_shopurl);
			System.out.println(shoppingMall.getS_num()+" get shoppingmall in dao");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return shoppingMall;
	}
	
	public int insertSearchKeyword(SearchKeyword searchKeyword){
		SqlSession session = getSqlSessionFactory().openSession();
		int re = -1;
		
		
		try {
			re = session.getMapper(StatsMapper.class).insertSearchKeyword(searchKeyword);
			if(re >0){
				session.commit();
			}else{
				session.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return re;
	}
	
	public List<SearchKeyword> listSearchKeyword(){
		SqlSession session = getSqlSessionFactory().openSession();
		List<SearchKeyword> list = null;
		
		try {
			list = session.getMapper(StatsMapper.class).listSearchKeyword();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	
//delete
	public List<StatsGender> statsGenderRate(String s_num){
		SqlSession session = getSqlSessionFactory().openSession();
		List<StatsGender> list = null;
		try {
			System.out.println(s_num + " in dao before get list");
			list = session.getMapper(StatsMapper.class).statsGenderRate(s_num);
			System.out.println(list.get(0).getS_num()+" in dao after get list");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	
	public List<HitCount> statsDate(String statsSearchShop, String statsSearchStartDate, String statsSearchEndDate, String chartFor){
		SqlSession session = getSqlSessionFactory().openSession();
		List<HitCount> list = null;
		String s_num=statsSearchShop;
		
		try {
			System.out.println(chartFor+" in dao in try");
			if(chartFor.equals("area")){
				list = session.getMapper(StatsMapper.class).statsDate_adr(s_num, statsSearchStartDate, statsSearchEndDate);
			}else if(chartFor.equals("gender")){
				list = session.getMapper(StatsMapper.class).statsDate_gender(s_num, statsSearchStartDate, statsSearchEndDate);
				System.out.println(list.get(0).getCnt_date()+" in dao");
				System.out.println(list.get(0).getFemale()+" in dao");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	
	
}
