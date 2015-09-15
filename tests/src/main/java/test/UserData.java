package test;

public class UserData {
	
	private String login;
	private String pass;
	private String lon;
	private String lat;
	
	
	
	public UserData(String login, String pass, String lon, String lat) {
		this.login = login;
		this.pass = pass;
		this.lon = lon;
		this.lat = lat;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}

}
