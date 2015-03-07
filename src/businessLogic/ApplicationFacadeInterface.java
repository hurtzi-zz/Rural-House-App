package businessLogic;

import java.rmi.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Date;

import dataAccess.DataAccessInterface;
import domain.Booking;
import domain.Client;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.OfferCanNotBeBooked; 


public interface ApplicationFacadeInterface extends Remote {
	

	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */


	Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws RemoteException, Exception;

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	Booking createBooking(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			String telephoneNumber) throws RemoteException,
			OfferCanNotBeBooked;

	
	/**
	 * This method retrieves the existing  owners 
	 * 
	 * @return a Set of owners
	 */
	public Vector<Owner> getOwners() throws RemoteException,
			Exception;
	
	/**
	 * This method retrieves the existing  rural houses 
	 * 
	 * @return a Set of rural houses
	 */
	public Vector<RuralHouse> getAllRuralHouses()throws RemoteException,
	Exception;
	
	public void close() throws RemoteException;

    public void setDataAccess(DataAccessInterface dai) throws RemoteException;
    
   
//	
    public Client verifyLogin(String a, String b) throws RemoteException;
//
	public boolean verifyLoginName(String a) throws RemoteException;
	

    public boolean createOwner( Integer telefonoa, String bank,String Izena, String Abizena,String login, String pasahitza) throws RemoteException;
    
    public boolean  createClient(String Izena, String Abizena, String login, String pasahitza, boolean isOwner) throws RemoteException;
	
    /**
     * Bezero bat emanda jabe klasea itzuliko du, datu guztiak izateko
     * @param c
     * @return The (Owner)client + it's owner info
     */
    public Owner getOwner(Client c) throws RemoteException;
	
//    public void updateOwner(Owner del, Owner add) throws RemoteException;

	public Boolean saveRuralHouse(Integer ze, String hi, String de, Owner o)throws RemoteException;
	public Owner clientToOwner( Client t ) throws RemoteException, Exception ;
	public Vector<RuralHouse> ownerBektoreaBueltatu(String login) throws RemoteException, Exception;
	public Owner ownerBuelta(String login) throws RemoteException, Exception;
	
	//5.erab kasua
	public Vector<RuralHouse> SarchByCity(String city) throws RemoteException, Exception;

}