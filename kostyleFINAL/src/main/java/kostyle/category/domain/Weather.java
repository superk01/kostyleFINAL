package Category.model;

public class Weather {
	private String resultweather = "";
	private String resulttime = "";
	private String resultC = "";
	private String navstr = "";
	private String resultweatherimg ="";
	public String getResultweather() {
		return resultweather;
	}
	public void setResultweather(String resultweather) {
		this.resultweather = resultweather;
	}
	public String getResulttime() {
		return resulttime;
	}
	public void setResulttime(String resulttime) {
		this.resulttime = resulttime;
	}
	public String getResultC() {
		return resultC;
	}
	public void setResultC(String resultC) {
		this.resultC = resultC;
	}
	public String getNavstr() {
		return navstr;
	}
	public void setNavstr(String navstr) {
		this.navstr = navstr;
	}
	
	public String getResultweatherimg(){
		return resultweatherimg;
	}
	
	public Weather(String resultweather, String resulttime, String resultC, String navstr,String resultweatherimg) {
		super();
		this.resultweather = resultweather;
		this.resulttime = resulttime;
		this.resultC = resultC;
		this.navstr = navstr;
		this.resultweatherimg = resultweatherimg;
	}
	  
}
