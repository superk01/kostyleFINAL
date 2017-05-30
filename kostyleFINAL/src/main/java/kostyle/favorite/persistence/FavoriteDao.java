package favorite.model;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import favorite.mapper.FavoriteMapper;

public class FavoriteDao {

private static FavoriteDao dao = new FavoriteDao();
	
	public static FavoriteDao getInstance(){
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
	
	
	//listFavorite(insert한 모든 Favorite객체를 list로 만들어서 내림차순으로 정렬)
		public List<Favorite> listFavorite(String c_num, int startRow){
			SqlSession sqlSession = getsqlSessionFactory().openSession();
			List<Favorite> list = null;
			try {
				list = sqlSession.getMapper(FavoriteMapper.class).listFavorite(c_num, new RowBounds(startRow, 7));
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return list;
		}
	
		
	//CountFavorite(총 게시글의 수 출력)
		public int countFavorite(String c_num){
			SqlSession sqlSession = getsqlSessionFactory().openSession();
			int re=0;
			try {
				re = sqlSession.getMapper(FavoriteMapper.class).countFavorite(c_num);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return re;
		}	
		
	//detailComent
		public Favorite detailComent(String f_num){
			SqlSession sqlSession = getsqlSessionFactory().openSession();
			Favorite favorite = null;
			try{
				favorite = sqlSession.getMapper(FavoriteMapper.class).detailComent(f_num);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				sqlSession.close();
			}
			return favorite;
		}
		
		
	//updateComent(favorite에서 해당 글의 코멘트 버튼을 클릭하면 updateForm에서 코멘트 입력/수정이 가능)
		public int updateComent(Favorite favorite){
			SqlSession sqlSession = getsqlSessionFactory().openSession();
			int re=-1;
			try {
				re = sqlSession.getMapper(FavoriteMapper.class).updateComent(favorite);
				if(re>0){
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
		
		
	//deleteFavorite(favorite에서 해당 글의 삭제 버튼을 클릭하면 DB에서 삭제됨)
		public int deleteFavorite(Favorite favorite){
			SqlSession sqlSession = getsqlSessionFactory().openSession();
			int re=-1;
			try {
				re = sqlSession.getMapper(FavoriteMapper.class).deleteFavorite(favorite);
				if(re>0){
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
