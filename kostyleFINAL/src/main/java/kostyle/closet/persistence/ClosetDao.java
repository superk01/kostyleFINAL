package closet.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import closet.mapper.ClosetMapper;

public class ClosetDao {
	private static ClosetDao dao = new ClosetDao();	
	
	public static ClosetDao getInstance(){
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
	
	
	// --------기능함수 ----------
	
	//모든 '옷장'폴더를 리스트로 반환... navigation에 사용
	public List<Closet> closetList(String c_num){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<Closet> cloTab = new ArrayList<Closet>();
		try {
			cloTab = sqlSession.getMapper(ClosetMapper.class).tabCloset(c_num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 sqlSession.close();
		}
		return cloTab;
	}
	
	//사용자의 모든 옷장에 들어있는 상품들을 전부 출력
	public List<ClosetPrd> fullCloset(ClosetPrd closetPrd){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<ClosetPrd> cloList = new ArrayList<ClosetPrd>();
		try {
			cloList = sqlSession.getMapper(ClosetMapper.class).fullCloset(closetPrd);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 sqlSession.close();
		}
		return cloList;
	}
	//조회해온 옷장상품의 clo_num으로 clo_name얻어오기
	public String cloNumTocloName(Closet closet){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String clo_name = "";
		try {
			clo_name = sqlSession.getMapper(ClosetMapper.class).cloNumTocloName(closet);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 sqlSession.close();
		}
		return clo_name;
	}
	
	//삭제를 위해 c_num으로 clo_num 가져오기
	public List<Integer> cNumTocloNum(String c_num){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<Integer> clo_numList = new ArrayList<Integer>();
		try {
			clo_numList = sqlSession.getMapper(ClosetMapper.class).cNumTocloNum(c_num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 sqlSession.close();
		}
		return clo_numList;
	}
	//사용자가 선택한 옷장의 상품들만 출력
	public List<ClosetPrd> selectCloset(ClosetPrd closetPrd){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<ClosetPrd> cloList = new ArrayList<ClosetPrd>();
		try {
			cloList = sqlSession.getMapper(ClosetMapper.class).selectCloset(closetPrd);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 sqlSession.close();
		}
		
		return cloList;
	}
	
	//옷장폴더 추가
	public int insertCloset(Closet closet){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re = -1;
		try {
			re = sqlSession.getMapper(ClosetMapper.class).insertCloset(closet);
			if( re > 0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			 //sqlSession.close();
		}
		
		return re;
	}
	
	//가장 큰 옷장번호 구하기. 폴더추가시 max(clo_num)+1로
	public int max_clo_num(String c_num){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int max_clo_num=0;
		try {
			max_clo_num = sqlSession.getMapper(ClosetMapper.class).max_clo_num(c_num);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 sqlSession.close();
		}
		return max_clo_num;
	}
	//옷장폴더 삭제(해당옷장상품들 먼저 삭제 -> 옷장삭제)
	public int deleteSameCloset_prds(Closet closet){
		SqlSession session = getSqlSessionFactory().openSession();
		int re= -1;
		try {
			re = session.getMapper(ClosetMapper.class).deleteSameCloset_prds(closet);
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
		return re;
	}
	public int deleteCloset(Closet closet){
		SqlSession session = getSqlSessionFactory().openSession();
		int re= -1;
		try {
			re = session.getMapper(ClosetMapper.class).deleteCloset(closet);
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
		return re;
	}
	
	//폴더이름 수정
	public int updateCloset(Closet closet){
		SqlSession session = getSqlSessionFactory().openSession();
		int re= -1;
		try {
			re = session.getMapper(ClosetMapper.class).updateCloset(closet);
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
		return re;
	}
	public int moveClosetPrd(ClosetPrd closetPrd){
		SqlSession session = getSqlSessionFactory().openSession();
		int re= -1;
		try {
			re = session.getMapper(ClosetMapper.class).moveClosetPrd(closetPrd);
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
		return re;
	}
	
	//상품 삭제
	public int deleteClosetPrd(int clo_detail_num){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=-1;
		try {
			re = sqlSession.getMapper(ClosetMapper.class).deleteClosetPrd(clo_detail_num);
			if( re > 0){
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

	
	
	
	public int insertClosetPrd(ClosetPrd closetPrd){
		System.out.println("insertCloestPrd진입");
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re = -1;
		try{
			re = sqlSession.getMapper(ClosetMapper.class).insertClosetPrd(closetPrd);
			if(re>0){
				System.out.println("insertClosetPrd re>0진입");
				sqlSession.commit();
			}else{
				System.out.println("insertClosetPrd re>0 else문 진입");
				sqlSession.rollback();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return re;
	}
	
	//해당상품의 중복여부 우선확인
	public int check_duplication(ClosetPrd closetPrd){
		System.out.println("check_duplication진입");
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int dupliCount = -1;
		try{
			dupliCount = sqlSession.getMapper(ClosetMapper.class).check_duplication(closetPrd);
			System.out.println("dao의 dupliCount: "+dupliCount);
			
			if(dupliCount>0){
				System.out.println("check_duplication dupliCount>0진입");
			}else{
				System.out.println("check_duplication dupliCount>0 else문 진입");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return dupliCount;
	}
	
	//가장 큰 clo_detail_num구하기. 찜상품추가시 +1해서 clo_detail_num으로사용
	public int max_detail_num(){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int max_detail_num = 0;
		try {
			max_detail_num = sqlSession.getMapper(ClosetMapper.class).max_detail_num();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return max_detail_num;
	}
	
	//해당상품zzim횟수 구하기
	public int count_zzim(ClosetPrd closetPrd){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int count_zzim = -1;
		try {
			count_zzim = sqlSession.getMapper(ClosetMapper.class).count_zzim(closetPrd);
			System.out.println("dao에서 count_zzim 결과: "+count_zzim);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			 sqlSession.close();
		}
		return count_zzim;
	}
	
	
	public HashMap urlRepair(String prdUrl){
		//class="xans-element- xans-product xans-product-detail" 쪽이 상단 상품정보 위치임.
		//가격: <string id="span_product_price_text" 이후 ,000원이면 추출! 확정.
		/*       String strResult = num; //출력할 결과를 저장할 변수
        Pattern p = Pattern.compile("(^[+-]?\\d+)(\\d{3})"); //정규표현식 
        Pattern p = Pattern.compile("(^[+-]?\\d+,)*(\\d{3})")원$; //정규표현식 
        Matcher regexMatcher = p.matcher(num); 
[출처] [Java] 숫자에 쉼표(콤마;Comma) 넣기, 천(1000) 단위 구분 (정규표현식이용, replaceAll())|작성자 자바킹
*/

		//class="xans-element- xans-product xans-product-action " 이전까지 인덱스로 끊으면 상단상품 계산 위쪽까지 모아짐.
		
		
		//우선..자바의 줄바꿈은 시스템마다 달라진다. 현재 운영체제의 줄바꿈 문자 얻는법.
		String line = System.getProperty("line.separator");
		System.out.println("dao에서 받은 prdUrl: "+prdUrl);

		URL url =null;
		BufferedReader br =null;
		//String address="http://66girls.co.kr/product/detail.html?product_no=67185&cate_no=81&display_group=2";
		String address="http://hotping.co.kr/product/detail.html?product_no=19218&cate_no=26&display_group=1";
		
		/*String address2= "http://66girls.co.kr/product/search.html?banner_action=&keyword=%EA%B0%80%EB%94%94%EA%B1%B4";
		address2 = URLDecoder.decode(address2);
		System.out.println("디코딩된 값: "+address2);*/
		
		String imgUrl ="";
		String price="";
		String prdName="";
		try {
			url = new URL(prdUrl);
			URLConnection con = url.openConnection(); 
			br = new BufferedReader(new InputStreamReader(url.openStream()));
		    String code;
			StringBuffer sb = new StringBuffer();

			while((code=br.readLine())!=null){
				sb.append(code+line); // 문자열을 합칠 때 append가 속도가 빠름
			}
			code = sb.substring(0);
			
			
				imgUrl = getImgURL(code); 
				price = getPrice(code);
				prdName = "";
			
			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if( br !=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		HashMap<String,String> hash = new HashMap<String,String>();
		hash.put("imgUrl", imgUrl);
		hash.put("price", price);
		hash.put("prdName", prdName);
		
		return hash;
	}
	
	
	//찜상품 이미지url따오기
	  public String getImgURL(String source){
		  String imgUrl = null;
		  int index1 = source.indexOf("keyImg");
		   System.out.println("이미지url인덱스: "+index1);
		   if(index1 != -1){
			   source = source.substring(index1);
			  /* int index2 = sb.indexOf("alt");
			   
			   code = code.substring(0, index2);*/
	        //StringBuffer sb = new StringBuffer();
	        //String regex ="(http|https|ftp)://[^\\s^\\.]+(\\.[^\\s^\\.^\"^\']+)*"; //공백문자,백슬러시,따옴표 등으로 시작하지 않는
	
	        Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
	        Matcher matcher = pattern.matcher(source);
	         
	        //while(matcher.find()){//여러개다뽑으면 while+스트링버퍼. 리턴도 스트링버퍼로.
	        if(matcher.find()){//첫번째 것만 뽑을거니 if에 String하나로. 
	        	imgUrl = matcher.group(1) ; //어우헷갈... 여기서 그룹1은  ([^>\"']+) 이다.  그룹(0)은 매칭된 전체.)
	        	System.out.println(imgUrl);
	        }
	    }	
		   return imgUrl;
	  }
	 
	 //찜상품 가격 가져오기
	  public String getPrice(String source){
		  String price = null;
		  int index1 = source.indexOf("span_product_price_text");
		  System.out.println("가격추출인덱스결과: "+index1);
		  
		  if(index1 != -1){
			  source = source.substring(index1);
		  } 
		  System.out.println("가격추출할 소스: "+source);
		  //상품이름의 정규표현식...필요.. 전부 실패!
		  // Pattern pattern = Pattern.compile("(^[+-]?\\d+)*(,\\d{3})*원$");
		  //Pattern pattern = Pattern.compile("^[\\d]{1}([\\d,])*(\\s)*(원)$");
		  //Pattern pattern = Pattern.compile("(^[\\d])+[,]*[\\d]*");
		  //Pattern pattern = Pattern.compile("(^[\\d])+(,\\d{3})*(원)$");
		  //Pattern pattern = Pattern.compile("\\b(?:\\d{1,3})(?:,\\d{3})*(?:\\.\\d+)?\\b");
		 // Pattern pattern = Pattern.compile("(^[\\d])+([,][\\d]{3})*");
		  Pattern pattern = Pattern.compile("(\\d{1,3})+(,\\d{3})*");
		  //도대체 왜 시작태그인 ^나 원$으로 끝나는 것을 찾으면 전부 null? 
		  Matcher matcher = pattern.matcher(source);
		  
		  if(matcher.find()){
			  System.out.println("매치되는 가격을 찾긴찾았다!");
			  price = matcher.group(); //매칭된부분 전체반환
		  }
		  System.out.println("최종추출가격: "+price);
		  
		  return price+"원";
	  }
	 //찜상품 이름 가져오기
	  public String getPrdName(String source){
		  String prdName = null;
		
		  int index1 = 0;
		  System.out.println("'상품명'추출인덱스결과: "+index1);
		  
		  if(index1 != -1){
			  source = source.substring(index1);
		  }
		  
		  Pattern pattern = Pattern.compile(""); //상품이름 가져올 정규표현식 알아야..
		  Matcher matcher = pattern.matcher(source);
		  
		  if(matcher.find()){
			  prdName = matcher.group(); //매칭된부분 전체반환
			  System.out.println("최종추출'상품명': "+prdName);
		  }
		  
		  return prdName;
	  }
	  //url에서 http://떼기
	  public String prdUrlRepair(String prdUrl){
		  int index = -1;
		  index = prdUrl.indexOf("http://");
		  
		  if(index != -1){
			  index = index +7;
			  prdUrl = prdUrl.substring(index);
			  System.out.println("http://뗀 prdUrl: "+prdUrl);
		  }
		  return prdUrl;
	  }
	  //쇼핑몰 도메인 가져오기
	  public String getShopUrl(String prdUrl){
		  String shopUrl = "";
		  
		  prdUrl = dao.prdUrlRepair(prdUrl);
		  
		  int index = prdUrl.indexOf("/product/");
		  if(index != -1 ){
			  shopUrl = prdUrl.substring(0, index);
			  System.out.println("얻은 shopUrl: "+shopUrl);
		  }
		  
		  return shopUrl;
	  }
		//꼭 DB로 해야하나.. 등록시 url로 쇼핑몰이름따오기
		public String getS_name(String closetPrd_shopUrl){
			String s_name = "";
			if(closetPrd_shopUrl.indexOf("66girls.co.kr") != -1){
				s_name = "66걸즈";
			}else if(closetPrd_shopUrl.indexOf("www.stylenanda.com") != -1 ){
				s_name = "스타일난다";
			}else if(closetPrd_shopUrl.indexOf("ggsing.com") != -1){
				s_name = "고고싱";
			}else if(closetPrd_shopUrl.indexOf("loveloveme.com") != -1){
				s_name = "러브러브미";
			}else if(closetPrd_shopUrl.indexOf("hotping.co.kr") != -1){
				s_name = "핫핑";
			}else if(closetPrd_shopUrl.indexOf("www.dejou.co.kr") != -1){
				s_name = "더데이즈";
			}else if(closetPrd_shopUrl.indexOf("imvely.com") != -1){
				s_name = "임블리";
			}
			return s_name;
		}
}	//end class