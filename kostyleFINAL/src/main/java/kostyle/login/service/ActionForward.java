package login.action;

public class ActionForward {
	private boolean isRedirect;
	//컨트롤러가 결과값을 들고 jsp를 찾아갈 때, Redirect로 갈건지, dispatch해서 직접갈건지 정해야함
	private String path;
	
	public boolean isRedirect(){
		return isRedirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}
