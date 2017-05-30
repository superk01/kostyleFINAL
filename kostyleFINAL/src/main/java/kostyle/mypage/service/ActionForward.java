package mypage.action;

public class ActionForward {
	private boolean isRedirect;
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
