package domain;

import java.io.Serializable;
import java.util.Vector;

public class Client implements Serializable {


	private String name="";
	private String abizena = "";
	private String login="";
	private String password="";
	private Boolean isOwner=false;
	

	//client
	public Client(String name, String abizena, String login, String password,Boolean isOwner){
		this.name=name;
		this.abizena=abizena;
		this.login=login;
		this.password=password;
		this.isOwner=isOwner;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAbizena() {
		return abizena;
	}


	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getIsOwner() {
		return isOwner;
	}


	public void setIsOwner(Boolean isOwner) {
		this.isOwner = isOwner;
	}
	
	
	

}
