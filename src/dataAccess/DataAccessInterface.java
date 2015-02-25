package dataAccess;

import java.util.Date;
import java.util.Vector;


import domain.Booking;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;

public interface DataAccessInterface {
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay, float price);

	public Booking createBooking(RuralHouse ruralHouse, Date firstDate, Date lastDate, String bookTelephoneNumber)
			throws OfferCanNotBeBooked;
	
	public Vector<Owner> getOwners();
	public Vector<RuralHouse> getAllRuralHouses();
	
	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws OverlappingOfferExists;

	public void close();
}
