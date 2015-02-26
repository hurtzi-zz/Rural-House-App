package businessLogic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.sql.SQLException;
import java.util.Vector;


import dataAccess.DataAccessCommon;
import dataAccess.DataAccessInterface;
import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.BadDates;
import exceptions.DB4oManagerCreationException;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;

public class FacadeImplementation extends UnicastRemoteObject implements
		ApplicationFacadeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Vector<Owner> owners;
	Vector<RuralHouse> ruralHouses;
	DataAccessInterface dB4oManager;

	public FacadeImplementation() throws RemoteException,
			InstantiationException, IllegalAccessException,
			ClassNotFoundException, SQLException, DB4oManagerCreationException {

	}

	/**
	 * This method creates an offer with a house number, first day, last day and
	 * price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay, float price) throws OverlappingOfferExists, BadDates,
			RemoteException, Exception {
		if (firstDay.compareTo(lastDay) >= 0)
			throw new BadDates();
		ruralHouses = null;
		owners = null;
		boolean b = dB4oManager.existsOverlappingOffer(ruralHouse, firstDay,
				lastDay); // The ruralHouse object in the client may not be
							// updated
		if (!b)
			return dB4oManager
					.createOffer(ruralHouse, firstDay, lastDay, price);
		return null;
	}

	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	public Booking createBooking(RuralHouse ruralHouse, Date firstDate,
			Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked {
		ruralHouses = null;
		owners = null;
		return dB4oManager.createBooking(ruralHouse, firstDate, lastDate,
				bookTelephoneNumber);
	}

	/**
	 * This method existing owners
	 * 
	 */
	public Vector<Owner> getOwners() throws RemoteException, Exception {

		if (owners != null) {
			System.out
					.println("Owners obtained directly from business logic layer");
			return owners;
		} else
			return owners = dB4oManager.getOwners();
	}

	public Vector<RuralHouse> getAllRuralHouses() throws RemoteException,
			Exception {

		if (ruralHouses != null) {
			System.out
					.println("RuralHouses obtained directly from business logic layer");
			return ruralHouses;
		} else
			return ruralHouses = dB4oManager.getAllRuralHouses();

	}

	public void close() throws RemoteException {
		dB4oManager.close();

	}

	@Override
	public void setDataAccess(DataAccessInterface dai) throws RemoteException {
		dB4oManager = dai;
		// TODO Auto-generated method stub

	}
	
	//urtzi
	public Owner verifyLoginOwner(String login, String pass) throws RemoteException {
		// TODO Auto-generated method stub
		
		return  DataAccessCommon.getInstance().verifyLoginOwner(login,pass);
		 
		 
	}
	
	
	public boolean verifyLoginName(String login) throws RemoteException{
		return false;
		//return DB4oManager.getInstance().verifyregistre(login);
	}
	


}
