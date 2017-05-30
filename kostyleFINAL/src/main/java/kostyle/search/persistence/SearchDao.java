package search.model;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;

import search.mapper.SearchMapper;

public class SearchDao {
	private static SearchDao dao = null;
	public static SearchDao getInstance() {
		if(dao==null){
			dao = new SearchDao();
		}
		return dao;
	}
	public SqlSessionFactory getFactory() {
		String resource = "mybatis-config.xml";
		InputStream in = null;
		try {
			in = Resources.getResourceAsStream(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SqlSessionFactoryBuilder().build(in);
	}
	public List<String> getS_SearchUrl() {
		SqlSession session = getFactory().openSession();
		List<String> list = null;
		try {
			list = session.getMapper(SearchMapper.class).getS_SearchUrl();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}
