package dataAccess;

import java.util.Date;
import java.util.Vector;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import configuration.ConfigXML;
import domain.Booking;
import domain.Client;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;

public class DataAccessCommon implements DataAccessInterface {
	protected static ObjectContainer db;
	private int bookingNumber = 0; // if it is "static" then it is not
									// serialized
	private int offerNumber = 0; // if it is "static" then it is not serialized

	protected static DB4oManagerAux theDB4oManagerAux;
	ConfigXML c;
	
	private static DataAccessCommon theDataAccessCommon =new DataAccessCommon();

	
	
	public static DataAccessCommon getInstance()  {
		
		return theDataAccessCommon;
	}


	public DataAccessCommon() {
		theDB4oManagerAux = new DB4oManagerAux(0, 0);
		c = ConfigXML.getInstance();
		System.out.println("Creating DB4oManager instance => isDatabaseLocal: "
				+ c.isDatabaseLocal() + " getDatabBaseOpenMode: "
				+ c.getDataBaseOpenMode());
	}

	class DB4oManagerAux {
		int bookingNumber;
		int offerNumber;

		DB4oManagerAux(int bookingNumber, int offerNumber) {
			this.bookingNumber = bookingNumber;
			this.offerNumber = offerNumber;
		}
	}

	public void initializeDB() {
		Client aaa = new Client("clie", "cli", "aaa", "aaa", false);
		
		Client aaa2 = new Client("clie2", "cli", "aaa2", "aaa", false);
		
		Client aaa3 = new Client("clie3", "cli", "aaa3", "aaa", false);
		
		Owner iñigo = new Owner("Iñigo", "Sanz", "iñig00", "1234",true, 65252525,
				"00000000",new Vector<RuralHouse>());
		Owner itziar = new Owner("ici", "jaltuna", "icic00", "99999",true, 65252525,
				null,new Vector<RuralHouse>());
		Owner urtzi = new Owner("ur", "diaz", "utrr00", "5555",true, 65252525,
				"1rrrrrr",new Vector<RuralHouse>());
		
		
		Owner sss = new Owner("sss", "sss", "sss", "sss",true, 65252525,
				"1rrrrrr",new Vector<RuralHouse>());

		iñigo.addRuralHouse(1, "Ezkioko etxea", "Ezkio");
		iñigo.addRuralHouse(2, "Etxetxikia", "Iruña");
		itziar.addRuralHouse(3, "Udaletxea", "Bilbo");
		itziar.addRuralHouse(4, "Gaztetxea", "Renteria");
		
		sss.addRuralHouse(4, "Gaztetxea", "Renteria");

		iñigo.setBankAccount("12345677");
		
		db.store(aaa);
		db.store(aaa2);
		db.store(aaa3);
		db.store(iñigo);
		db.store(itziar);
		db.store(urtzi);
		
		db.store(sss);
		

		db.commit();
	}
	
//	public void storeClintDB(,,,,) {
//		Client aaa = new Client("Iñigo", "Sanz", "aaa", "aaa", false);
//		db.store(urtzi);
//		db.commit();
//	}
//	
//	public void storeClintDB(,,,,) {
//		Client aaa = new Client("Iñigo", "Sanz", "aaa", "aaa", false);
//		db.store(urtzi);
//		db.commit();
//	}
	
	public  static ObjectContainer getContainer(){
		  return db;
	    }
	
	public static Client verifyLogin(String log, String pass){
		Client galdera = new Client(null, null,log,pass,null);
			try {							
				ObjectContainer db=DataAccessCommon.getContainer();
				 ObjectSet result = db.queryByExample(galdera);
				 if(result.hasNext())
				 {  
					 Client c=(Client)result.next();
					 return(c);
				 }else{
					 return null;
				 }
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}
	
	
	public Owner verifyLoginName(String log){
		Owner galdera = new Owner(null,null,log,null,null,null, null,null);
			try {							
				ObjectContainer db=DataAccessCommon.getContainer();
				 ObjectSet result = db.queryByExample(galdera);
				 System.out.println(result.size());
				 if(result.hasNext())
				 {  
					 System.out.println((Owner)result.next());
					 return(Owner)result.next();
					
					
				 }else{
					 System.out.println("ez dago erabiltzailea");
					 return null;
				 }
				 
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

	public Offer createOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay, float price) {

		try {

			// if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),
					null, null, null);
			ObjectSet<RuralHouse> result = db.queryByExample(proto);
			RuralHouse rh = result.next();
			Offer o = rh.createOffer(theDB4oManagerAux.offerNumber++, firstDay,
					lastDay, price);
			db.store(theDB4oManagerAux); // To store the new value for
											// offerNumber
			db.store(o);
			db.commit();
			return o;
		} catch (com.db4o.ext.ObjectNotStorableException e) {
			System.out
					.println("Error: com.db4o.ext.ObjectNotStorableException in createOffer");
			return null;
		}
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

		try {

			// if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse proto = new RuralHouse(ruralHouse.getHouseNumber(),
					null, ruralHouse.getDescription(), ruralHouse.getCity());
			ObjectSet<RuralHouse> result = db.queryByExample(proto);
			RuralHouse rh = result.next();

			Offer offer;
			offer = rh.findOffer(firstDate, lastDate);

			if (offer != null) {
				offer.createBooking(theDB4oManagerAux.bookingNumber++,
						bookTelephoneNumber);
				db.store(theDB4oManagerAux); // To store the new value for
												// bookingNumber
				db.store(offer);
				db.commit();
				return offer.getBooking();
			}
			return null;

		} catch (com.db4o.ext.ObjectNotStorableException e) {
			System.out
					.println("Error: com.db4o.ext.ObjectNotStorableException in createBooking");
			return null;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	/**
	 * This method existing owners
	 * 
	 */
	public Vector<Owner> getOwners() {

		// if (c.isDatabaseLocal()==false) openObjectContainer();

		try {
			Owner proto = new Owner(null, null, null, null,null, null, null,null);
			ObjectSet<Owner> result = db.queryByExample(proto);
			Vector<Owner> owners = new Vector<Owner>();
			while (result.hasNext())
				owners.add(result.next());
			return owners;
		} finally {
			// db.close();
		}
	}

	public Vector<RuralHouse> getAllRuralHouses() {

		// if (c.isDatabaseLocal()==false) openObjectContainer();

		try {
			RuralHouse proto = new RuralHouse(0, null, null, null);
			ObjectSet<RuralHouse> result = db.queryByExample(proto);
			Vector<RuralHouse> ruralHouses = new Vector<RuralHouse>();
			while (result.hasNext())
				ruralHouses.add(result.next());
			return ruralHouses;
		} finally {
			// db.close();
		}
	}

	public boolean existsOverlappingOffer(RuralHouse rh, Date firstDay,
			Date lastDay) throws OverlappingOfferExists {
		try {
			// if (c.isDatabaseLocal()==false) openObjectContainer();

			RuralHouse rhn = (RuralHouse) db.queryByExample(
					new RuralHouse(rh.getHouseNumber(), null, null, null))
					.next();
			if (rhn.overlapsWith(firstDay, lastDay) != null)
				throw new OverlappingOfferExists();
			else
				return false;
		} finally {
			// db.close();
		}
	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public String toString() {
		return "bookingNumber=" + bookingNumber + " offerNumber=" + offerNumber;
	}

}
