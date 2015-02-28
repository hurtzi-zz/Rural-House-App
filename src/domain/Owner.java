package domain;

import java.io.Serializable;
import java.util.Vector;




@SuppressWarnings("serial")
public class Owner extends Client implements Serializable  {
//	private String name="";
//	private String abizena = "";
//	private String login="";
//	private String password="";
//	private Boolean isOwner=true;
	private Integer tlfn = null;
	private String bankAccount = "";
	private Vector<RuralHouse> ruralHouses=new Vector<RuralHouse>();


	
	//jabea
	public Owner(Integer tlfn, String bankAccount,Vector<RuralHouse> ruralHouses,String name, String abizena, String login, String password,Boolean isOwner){
		super(name,abizena,login,password,isOwner);
		this.bankAccount=bankAccount;
		this.tlfn=tlfn;
		this.ruralHouses=ruralHouses;
	}
	/**
	 * This method returns the name
	 * 
	 * @return owner name
	 */

	
	
	public Integer getTlfn() {
		return tlfn;
	}

	public void setTlfn(Integer tlfn) {
		this.tlfn = tlfn;
	}


	public void setRuralHouses(Vector<RuralHouse> ruralHouses) {
		this.ruralHouses = ruralHouses;
	}
	


	
	/**
	 * This method returns the owner bank account number
	 * 
	 * @return The bank account number
	 */
	public String getBankAccount() {
		return this.bankAccount;
	}

	/**
	 * This method sets the owner account number 
	 * 
	 * @param bankAccount
	 *            bank account number
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	

	/**
	 * This method obtains an owner's(userId) rural houses 
	 * 
	 * @param userId
	 *            user key
	 * @return a vector of Rural Houses
	 */
	
	public Vector<RuralHouse> getRuralHouses() {
		return ruralHouses;
	}
	
	public RuralHouse addRuralHouse(int houseNumber, String description, String city) {
     RuralHouse rh=new RuralHouse( houseNumber,  this,  description,  city);
	 ruralHouses.add(rh);
	 return rh;
	 
	}

	
}