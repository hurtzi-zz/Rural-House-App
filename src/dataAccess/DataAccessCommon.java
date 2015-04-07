package dataAccess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

import configuration.ConfigXML;
import domain.Booking;
import domain.Client;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;
import gui.AddRuralHouse;
import gui.Loged;

public class DataAccessCommon implements DataAccessInterface {
	protected static ObjectContainer db;
	private int bookingNumber = 0; // if it is "static" then it is not
									// serialized
	private int offerNumber = 0; // if it is "static" then it is not serialized

	protected static DB4oManagerAux theDB4oManagerAux;
	ConfigXML c;

	private static DataAccessCommon theDataAccessCommon = new DataAccessCommon();

	public static DataAccessCommon getInstance() {

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

		Client cl0 = new Client("clientName", "clientSurname", "nickName",
				"pass", false, new Vector<RuralHouse>());

		Client cl1 = new Client("clie", "cli", "aaa", "aaa", false,
				new Vector<RuralHouse>());

		Owner ow0 = new Owner(666666666, "bankCount0000",
				new Vector<RuralHouse>(), "OwnerName", "OwnerSurname",
				"nickName", "pass", true, new Vector<RuralHouse>());

		Owner ow1 = new Owner(123456789, "12340000", new Vector<RuralHouse>(),
				"Iñigo", "Sanz", "isanz00", "1234", true,
				new Vector<RuralHouse>());

		Owner ow2 = new Owner(987654321, null, new Vector<RuralHouse>(),
				"Itziar", "Altuna", "jaltuna", "000", true,
				new Vector<RuralHouse>());

		Owner ow3 = new Owner(652729490, "1rrrrrr", new Vector<RuralHouse>(),
				"Urtzi", "Diaz", "urtzi00", "asd", true,
				new Vector<RuralHouse>());

		ow2.addRuralHouse(1, "deskri des de de desd es dede sdes des des des",
				"vito");
		ow3.addRuralHouse(2, "SomoEtxe", "vito");
		ow1.addRuralHouse(3, "Etxetxikia", "vito");
		ow1.addRuralHouse(4, "Udaletxea", "vito");
		ow1.addRuralHouse(1, "Ezkioko etxea", "Sanse");
		ow2.addRuralHouse(5, "Gaztetxea", "vito");
		ow2.addRuralHouse(3, "Gaztetxea", "donos");
		ow2.addRuralHouse(2, "Gaztetxea", "donos");
		ow2.addRuralHouse(1, "Gaztetxea", "donos");

		ow2.addRuralHouse(5, "Gaztetxea", "a");
		ow2.addRuralHouse(3, "Gaztetxea", "a");
		ow2.addRuralHouse(2, "Gaztetxea", "a");
		ow2.addRuralHouse(1, "Gaztetxea", "a");

		ow1.setBankAccount("1234berri");

		db.store(cl0);
		db.store(cl1);

		db.store(ow0);
		db.store(ow1);
		db.store(ow2);
		db.store(ow3);

		db.commit();
	}

	public boolean createClient(String name, String surname, String login,
			String password, boolean isOwner) {
		Client c = new Client(name, surname, login, password, false,
				new Vector<RuralHouse>());
		db.store(c);
		db.commit();
		return true;
	}

	public boolean createOwner(Integer tlfn, String bankAccount, String name,
			String abizena, String login, String password) {
		Owner berria = new Owner(tlfn, bankAccount, null, name, abizena, login,
				password, true, new Vector<RuralHouse>());
		System.out.println("abizena: " + berria.getAbizena());
		db.store(berria);
		db.commit();
		return true;
	}

	public Boolean saveRuralHouse(Integer ze, String hi, String de, Owner o) {
		db.delete(o);
		db.commit();
		RuralHouse etxetxoa = new RuralHouse(ze, o, de, hi);
		System.out.println(o.getRuralHouses().size());
		o.addRuralHouse(etxetxoa);
		System.out.println(o.getRuralHouses().size());
		db.store(o.getRuralHouses());
		db.commit();
		return true;
	}

	public void inprimatuEtxeakOwner(Owner ow) {
		System.out
				.println("-----------------------------------------------------------");
		int i = 1;
		Vector<RuralHouse> buelta = ow.getRuralHouses();
		if (buelta.size() == 0) {
			System.out.println(ow.getName() + "ez ditu etxeak");
		} else {
			System.out.println(ow.getName() + "-ren etxeak:");
			Iterator<RuralHouse> irt = buelta.iterator();
			while (irt.hasNext()) {
				System.out.print("(" + i + ") ");
				irt.next().imprimatu();
				i++;
			}
		}
		System.out
				.println("-----------------------------------------------------------");
	}

	public static ObjectContainer getContainer() {
		return db;
	}

	public static Client verifyLogin(String log, String pass) {
		Client galdera = new Client(null, null, log, pass, null, null);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			if (result.hasNext()) {
				Client c = (Client) result.next();
				return (c);
			} else {
				return null;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	public Boolean verifyLoginName(String log) {
		Client galdera = new Client(null, null, log, null, null, null);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			System.out.println(result.size());
			if (result.hasNext()) {
				Client c = (Client) result.next();
				return true;

			} else {
				return false;
			}

		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
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
			Owner proto = new Owner(null, null, null, null, null, null, null,
					null, null);
			ObjectSet<Owner> result = db.queryByExample(proto);
			Vector<Owner> owners = new Vector<Owner>();
			while (result.hasNext())
				owners.add(result.next());
			return owners;
		} finally {
			// db.close();
		}
	}

	public void ImpgetAllRuralHouses(Vector<RuralHouse> bek) {

		int i = 1;
		Vector<RuralHouse> buelta = bek;
		if (buelta.size() == 0) {
			System.out.println("DB ez ditu etxeak");
		} else {
			System.out.println("DBare-ren etxe guztiak:");
			Iterator<RuralHouse> irt = buelta.iterator();
			while (irt.hasNext()) {
				System.out.print("(" + i + ") ");
				irt.next().imprimatu();
				i++;
			}
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
		// System.out.print("itxi baino lehen=");
		// Owner berria = AddRuralHouse.owner;
		// inprimatuEtxeakOwner(berria);
		db.close();
		System.out.println("DataBase closed");
	}

	public String toString() {
		return "bookingNumber=" + bookingNumber + " offerNumber=" + offerNumber;
	}

	// public void updateOwner(Owner del, Owner add) throws RemoteException{
	// // db.delete(del);
	// db.store(add);
	// db.commit();
	// }

	public Owner getOwner(Client c) {
		Owner galdera = new Owner(null, null, null, null, null, c.getLogin(),
				null, null, null);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			Owner bilaketa = (Owner) result.get(0);
			System.out.println(bilaketa.getName());
			System.out.println(bilaketa.getRuralHouses());
			return bilaketa;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public Owner clienToOwner(Client t) {
		Owner galdera = new Owner(null, null, null, null, null, t.getLogin(),
				null, null, null);

		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			Owner bilaketa = (Owner) result.get(0);
			System.out.println(bilaketa.getAbizena() + "lalala");
			return bilaketa;
		} catch (Exception e) {

		}
		return null;
	}

	public Vector<RuralHouse> bektoreaLortu(String login) {
		try {
			Owner proto = new Owner(null, null, null, null, null, null, null,
					null, null);
			ObjectSet result = db.queryByExample(proto);
			Vector<Owner> owners = new Vector<Owner>();
			while (result.hasNext()) {
				Owner p = (Owner) result.next();
				if (p.getLogin().equals(login)) {
					System.out.println("match");
					System.out.println(p.getRuralHouses().size());
					return p.getRuralHouses();
				}

			}
			return null;
		} finally {
			// db.close();
		}
	}

	public Owner ownerBuelta(String login) {
		try {
			Owner proto = new Owner(null, null, null, null, null, null, null,
					null, null);
			ObjectSet result = db.queryByExample(proto);
			Vector<Owner> owners = new Vector<Owner>();
			while (result.hasNext()) {
				Owner p = (Owner) result.next();
				if (p.getLogin().equals(login)) {
					System.out.println("bat egiten dute");
					return p;
				}

			}
			return null;
		} finally {
			// db.close();
		}
	}

	public Vector<RuralHouse> SarchByCity(String city) {
		try {
			int i = 0;
			RuralHouse proto = new RuralHouse(0, null, null, city);
			ObjectSet<RuralHouse> result = db.queryByExample(proto);
			System.out.println(city + "--> " + proto.getCity());
			Vector<RuralHouse> ruralHouses = new Vector<RuralHouse>();
			System.out.println(" while1 ");
			while (result.hasNext()) {
				System.out.println(i + " : ");
				ruralHouses.add(result.next());
				i++;
			}

			return ruralHouses;
		} finally {
			System.out.println(" close ");
			// db.close();
		}
	}

	public static Boolean updateClient(Client c) {
		Client galdera = new Client(null,null , c.getLogin(),  null, null, null);

		Vector<RuralHouse> buelta0 = c.getRuralFav();
		if (buelta0.size() == 0) {
			System.out.println("BERRIA ez ditu favoritoak");
		} else {
			System.out.println("BERRIA favoritoak " + "(" + buelta0.size()
					+ "): ");
			Iterator<RuralHouse> irt = buelta0.iterator();
			while (irt.hasNext()) {
				irt.next().imprimatu();
			}
		}
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			if (result.hasNext()) {
				Client b = (Client) result.next();
				
				
				Vector<RuralHouse> buelta = b.getRuralFav();
				if (buelta.size() == 0) {
					System.out.println("ZAHARRA-0 ez ditu favoritoak");
				} else {
					System.out.println("ZAHARRA-0 favoritoak " + "("
							+ buelta.size() + "): ");
					Iterator<RuralHouse> irt = buelta.iterator();
					while (irt.hasNext()) {
						irt.next().imprimatu();
					}
				}
				
				b.setRuralFav(c.getRuralFav());
				
				Vector<RuralHouse> buelta2 = b.getRuralFav();
				if (buelta.size() == 0) {
					System.out.println("ZAHARRA-1 ez ditu favoritoak");
				} else {
					System.out.println("ZAHARRA-1 favoritoak " + "("
							+ buelta2.size() + "): ");
					Iterator<RuralHouse> irt = buelta2.iterator();
					while (irt.hasNext()) {
						irt.next().imprimatu();
					}
				}
				db.delete(b);
				db.store(b);
				
				db.commit();
				return true;
			} else {
				return false;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}

}
