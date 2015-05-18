package dataAccess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import configuration.ConfigXML;
import domain.Activity;
import domain.Admin;
import domain.Booking;
import domain.Client;
import domain.Comment;
import domain.Offer;
import domain.Owner;
import domain.RuralHouse;
import exceptions.OfferCanNotBeBooked;
import exceptions.OverlappingOfferExists;
import gui.AddRuralHouse;
import gui.Loged;

public class DataAccessCommon implements DataAccessInterface, Serializable {
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

		Client cl0 = new Client("clientName", "clientSurname", "nickNameC",
				"pass", false, new Vector<RuralHouse>());
		
		Client cl1 = new Client("Erabiltzailea", "Diaz", "client", "000", false,
				new Vector<RuralHouse>());


		Owner ow0 = new Owner(666666666, "bankCount0000",new Vector<RuralHouse>(), "OwnerName", "OwnerSurname", "nickName", "pass", true, new Vector<RuralHouse>());

		Owner ow1 = new Owner(123456789, "202149955487", new Vector<RuralHouse>(),"Owner", "Altuna", "owner", "000", true, new Vector<RuralHouse>());


		Admin admin = new Admin("admin", "1234", new Vector<Owner>(),new Vector<Client>());
		

		ow0.addRuralHouse(1, "Etxetxikia", "Gasteiz");
		ow0.addRuralHouse(2, "Udaletxea", "Gasteiz");
		ow0.addRuralHouse(3, "Ezkioko etxea", "Gasteiz");


		Comment co1 = new Comment(cl1, 10, "Oso ondo");
		Comment co2 = new Comment(cl1, 10, "Politena");
		Comment co3 = new Comment(cl1, 2, "Hiriko alde zaharretik oso urrun");
		Comment co4 = new Comment(cl1, 5, "Dena ok");
		Comment co5 = new Comment(cl1, 2, "Zarata gehiegi gauetan");
		Comment co6 = new Comment(cl1,9, "Politena");
		
		Activity a1 = new Activity("Paintball 8", "4v4 paintball", 8, true, ow1);
		Activity a2 = new Activity("Paintball 12", "6v6 paintball", 12, false, ow1);
		

		RuralHouse rh = ow1.addRuralHouse(4, "Gaztetxea", "Donosti");
		rh.addComent(co1);


		RuralHouse rh1 = ow1.addRuralHouse(5, "Etxetxikia", "Donosti");
		rh1.addComent(co2);
		rh1.addComent(co3);



		RuralHouse rh2 = ow1.addRuralHouse(6, "Ezkioko etxea", "Donosti");
		
		
		rh2.appendActivity(a1);


		RuralHouse rh3 = ow1.addRuralHouse(7, "Maitenea", "Donosti");
		rh3.addComent(co4);
		rh3.addComent(co5);
		rh3.addComent(co6);
		
		


		ow1.appendActivity(a1);
		ow1.appendActivity(a2);
		

		db.store(cl0);
		db.store(cl1);


		db.store(ow0);
		db.store(ow1);
		
		db.store(admin);



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
	public boolean createAdmin(String login, String pass, Vector<Owner> ow, Vector<Client> cl) {
		Admin berria = new Admin(login, pass, ow, cl);
		System.out.println("abizena: " + berria.getLogin());
		db.store(berria);
		db.commit();
		return true;
	}

	public Boolean saveRuralHouse(Integer ze, String hi, String de, Owner o) {
		// db.delete(o);
		// db.commit();
		// // Owner berria = AddRuralHouse.owner;
		// // ImpgetAllRuralHouses(getAllRuralHouses());
		// // inprimatuEtxeakOwner(berria);
		// // System.out.println("ownwer gorde: " + berria + ": " +
		// // berria.getName()
		// // + " " + berria.getAbizena());
		// // System.out.println("landetxea gorde: " + ze + " " + hi + " " +
		// de);
		// // berria.addRuralHouse(ze, hi, de);
		// // db.store(berria);
		// // ImpgetAllRuralHouses(getAllRuralHouses());
		// // inprimatuEtxeakOwner(berria);
		// // db.commit();
		// RuralHouse etxetxoa = new RuralHouse(ze, o, de, hi);
		// // o.addRuralHouse(etxetxoa);
		// System.out.println(o.getRuralHouses().size());
		//
		// o.addRuralHouse(etxetxoa);
		// System.out.println(o.getRuralHouses().size());
		//
		// db.store(o.getRuralHouses());
		// // db.store(etxetxoa);
		// db.commit();
		// return true;

		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			Owner oGaldera = o;
			RuralHouse rhGaldera = new RuralHouse(ze, null, null, null);
			ObjectSet rhResult = db.queryByExample(rhGaldera);
			if (rhResult.hasNext())
				return false;
			else {
				ObjectSet oResult = db.queryByExample(oGaldera);
				Owner oToSet = (Owner) oResult.next();
				RuralHouse rhToSet = new RuralHouse(ze, o, de, hi);
				oToSet.addRuralHouse(rhToSet);
				db.store(oToSet);
				db.store(rhToSet);
				db.commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean editRuralHouse(String hi, String de, Owner o, RuralHouse rh) {
		Owner galderaO = new Owner(null, null, null, null, null, o.getLogin(),
				null, null, null);
		RuralHouse galderaRH = new RuralHouse(rh.getHouseNumber(), null, null,
				null);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet resultO = db.queryByExample(galderaO);
			ObjectSet resultRH = db.queryByExample(galderaRH);
			if (resultO.hasNext() && resultRH.hasNext()) {
				Owner originalOwner = (Owner) resultO.next();
				RuralHouse originalRH = (RuralHouse) resultRH.next();
				originalOwner.getRHByNumber(rh.getHouseNumber()).setCity(hi);
				originalOwner.getRHByNumber(rh.getHouseNumber())
						.setDescription(de);
				originalRH.setCity(hi);
				originalRH.setDescription(de);
				db.store(originalOwner);
				db.store(originalRH);
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

	public Boolean deleteRuralHouse(Owner o, RuralHouse rh) {
		Owner galderaO = new Owner(null, null, null, null, null, o.getLogin(),
				null, null, null);
		RuralHouse galderaRH = new RuralHouse(rh.getHouseNumber(), null, null,
				null);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet resultO = db.queryByExample(galderaO);
			ObjectSet resultRH = db.queryByExample(galderaRH);
			if (resultO.hasNext() && resultRH.hasNext()) {
				Owner originalOwner = (Owner) resultO.next();
				RuralHouse originalRH = (RuralHouse) resultRH.next();
				System.out.println(originalOwner.getRuralHouses().size());
				originalOwner.deleteRH(originalRH);
				System.out.println(originalOwner.getRuralHouses().size());
				db.store(originalOwner);

				Iterator it = originalOwner.getRuralHouses().iterator();
				while (it.hasNext())
					System.out.println("PROBA" + it.next());

				db.delete(originalRH);
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
	public Boolean deleteOwner(Owner o) {
		Owner galderaO = new Owner(null, null, null, null, null, o.getLogin(),
				null, null, null);
		
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet resultO = db.queryByExample(galderaO);
			
			if (resultO.hasNext()) {
				Owner originalOwner = (Owner) resultO.next();
				db.delete(originalOwner);;
				System.out.println(originalOwner.getRuralHouses().size());
				db.commit();
				
			}
			return true;

				
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
	
	public Boolean deleteClient(Client o) {
		Client galderaO = new Client(null, null, o.getLogin(), null, null, null);
		
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet resultO = db.queryByExample(galderaO);
			
			if (resultO.hasNext()) {
				Client originalOwner = (Client) resultO.next();
				db.delete(originalOwner);
				db.commit();
				
			}
			return true;

				
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}

	public boolean saveOffer(RuralHouse rh, Date d1, Date d2, Float prezioa) {
		// db.delete(rh);
		// db.commit();
		Offer oferta = new Offer(rh, d1, d2, prezioa);
		rh.addOffer(oferta);
		System.out.println("holaaaa");
		db.store(rh.getOffer());
		System.out.println(rh.getOffer());
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

	public boolean VerifyOffer(RuralHouse ruralHouse, Date firstDay,
			Date lastDay) {

		Offer galdera = new Offer(ruralHouse, firstDay, lastDay, 0);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			System.out.println(result.size());
			if (result.hasNext()) {
				Offer c = (Offer) result.next();
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

			@SuppressWarnings("null")
			RuralHouse proto = new RuralHouse((Integer) null, null, null, null);
			// System.out.println(.getOwner() + ruralHouse.getCity());

			ObjectSet<RuralHouse> result = db.queryByExample(proto);
			RuralHouse rh = result.next();
			Offer o = rh.createOffer(firstDay, lastDay, price);
			// db.store(theDB4oManagerAux); // To store the new value for
			// offerNumber
			// db.store(o);
			// db.commit();
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
					false, null);
			ObjectSet<Owner> result = db.queryByExample(proto);
			Vector<Owner> owners = new Vector<Owner>();
			while (result.hasNext())
				owners.add(result.next());
			return owners;
		} finally {
			// db.close();
		}
	}

	public Vector<Client> getClients() {

		// if (c.isDatabaseLocal()==false) openObjectContainer();

		try {
			Client proto = new Owner(null, null, null, null, null, null, null,
					false, null);
			ObjectSet<Client> result = db.queryByExample(proto);
			Vector<Client> clients = new Vector<Client>();
			while (result.hasNext())
				clients.add(result.next());
			return clients;
		} finally {
			// db.close();
		}
	}

	public Vector<Offer> getOffers(Date firstDay, Date lastDay) {
		try {
			Offer proto = new Offer(null, firstDay, lastDay, 0);
			ObjectSet<Offer> result = db.queryByExample(proto);
			Vector<Offer> Offers = new Vector<Offer>();
			while (result.hasNext())
				Offers.add(result.next());
			return Offers;
		} finally {
			// db.close();
		}
	}

	public Vector<Offer> findOffer(Date firstDay, Date lastDay) {
		Vector<Offer> offers = new Vector<Offer>();

		Iterator<Offer> e = offers.iterator();
		Offer offer = null;
		while (e.hasNext()) {
			offer = e.next();
			if ((offer.getFirstDay().compareTo(firstDay) == 0)
					&& (offer.getLastDay().compareTo(lastDay) == 0))
				offers.add(offer);
		}
		return offers;

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

	public Boolean updateClient(Client c) {
		Client galdera = new Client(null, null, c.getLogin(), null, null, null);

		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			if (result.hasNext()) {

				Client b = (Client) result.get(0);

				db.delete(b);
				db.commit();

				Client update = new Client(c.getName(), c.getAbizena(),
						c.getLogin(), c.getPassword(), c.getIsOwner(),
						new Vector<RuralHouse>());

				int j = 0;
				while (j < (c.getRuralFav().size())) {
					update.addRuralFav(c.getRuralFav().get(j));
					j++;
				}

				db.store(update);
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

	/*
	 * public void printResultingList(Vector<RuralHouse> result){ // Owner p;
	 * for (Object o : result) System.out.println(o); }
	 */

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

	public RuralHouse rhBuleta(String city) {
		try {
			RuralHouse proto = new RuralHouse((Integer) null, null, null, null);
			ObjectSet result = db.queryByExample(proto);
			Vector<RuralHouse> rh = new Vector<RuralHouse>();
			while (result.hasNext()) {
				RuralHouse r = (RuralHouse) result.next();
				if (r.getCity().equals(city)) {
					System.out.println("bat egiten dute");
					return r;
				}
			}
			return null;
		} finally {

		}

	}

	public Vector<RuralHouse> SarchssByOwner(Owner o) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<RuralHouse> SarchByOwner(String LoginName) {

		Owner galdera = new Owner(null, null, null, null, null, LoginName,
				null, true, null);

		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			if (result.hasNext()) {
				Owner o = (Owner) result.next();
				return (o.getRuralHouses());
			} else {
				return null;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	// 33333333333

	public Boolean RuralHouseRefactorComment(RuralHouse rh) {

		System.out.println("getHouseNumber: " + rh.getHouseNumber());
		System.out.println("getOwner: " + rh.getOwner().getName());
		System.out.println("getCity: " + rh.getCity());

		System.out.println("*********************1");
		ImpgetAllRuralHouses(getAllRuralHouses());
		System.out.println("*********************2");
		Owner bezeroa = rh.getOwner();
		Vector<Offer> vo = rh.getOffers();
		LinkedList<Comment> lc = rh.getComments();

		inprimatuEtxeakOwner(bezeroa);
		System.out.println("*********************3");

		RuralHouse galdera = new RuralHouse(rh.getHouseNumber(), null, null,
				rh.getCity());

		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);

			System.out.println("result.size(): " + result.size());

			int i = 0;
			if (result.hasNext()) {
				System.out.println("-------------i: " + i);
				RuralHouse b = (RuralHouse) result.next();

				b.imprimatu();
				System.out.println("*********************4");

				db.delete(b);
				db.commit();

				RuralHouse update = new RuralHouse(rh.getHouseNumber(),
						rh.getOwner(), rh.getDescription(), rh.getCity());
				update.setComments(lc);
				update.setOffers(vo);

				db.store(update);
				db.commit();

				i++;

			}
			if (result.size() < 1) {
				return false;
			}
			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

	}

	public RuralHouse addComment(Client c, int botua, String kom, RuralHouse rh) {
		rh.addComent(c, botua, kom);
		return rh;
	}

	public Boolean updateHouse(RuralHouse rh) {
		db.store(rh);
		db.commit();
		return true;
	}


	public Boolean deleteAllCom(RuralHouse rh) {
		int kop = rh.getComments().size();
		for (int i = 0; i < kop; i++) {
			rh.getComments().remove();
		}
		db.store(rh);
		db.commit();
		return true;
	}

	public Boolean deleteCom(RuralHouse rh, int i) { 
		rh.getComments().remove(i);
		db.store(rh);
		db.commit();
		return true;
	}

	public int getFreeNumber() {
		int free = 0;
		int test = 1;
		RuralHouse galdera = new RuralHouse();
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			Vector<Integer> takenNumbers = new Vector<Integer>();
			while (result.hasNext()) {
				galdera = (RuralHouse) result.next();
				takenNumbers.add(galdera.getHouseNumber());
			}
			while (free == 0) {
				if (takenNumbers.contains(test) == false) {
					free = test;
				}
				test++;
			}
			return free;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Boolean createActivity(String izena, String deskribapena, int kop,
			Boolean egunez, Owner owner) {
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			Activity galdera = new Activity(izena);
			ObjectSet result = db.queryByExample(galdera);
			if (result.hasNext())
				return false;

			Owner oGaldera = new Owner(null, null, null, null, null,
					owner.getLogin(), null, true, null);
			ObjectSet result2 = db.queryByExample(oGaldera);
			if (result2.hasNext())
				oGaldera = (Owner) result2.next();
			Activity a = new Activity(izena, deskribapena, kop,
					egunez.equals(false), oGaldera);
			oGaldera.appendActivity(a);
			db.store(oGaldera);
			db.store(a);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Vector<Activity> getOwnerActivities(Owner o) throws RemoteException,
			Exception {
		Owner galdera = new Owner(null, null, null, null, null, o.getName(),
				null, true, null);
		ObjectContainer db = DataAccessCommon.getContainer();
		ObjectSet result = db.queryByExample(galdera);
		if (result.hasNext()) {
			Owner ask = (Owner) result.next();
			return ask.getActivities();
		} else
			return null;
	}

	public Vector<Activity> getHouseActivities(RuralHouse rh)
			throws RemoteException, Exception {
		RuralHouse galdera = new RuralHouse(rh.getHouseNumber(), null, null,
				null);
		ObjectContainer db = DataAccessCommon.getContainer();
		ObjectSet result = db.queryByExample(galdera);
		if (result.hasNext()) {
			RuralHouse ask = (RuralHouse) result.next();
			return ask.getActivities();
		} else
			return null;
	}

	public Boolean addActivity1(Activity a, RuralHouse rh)
			throws RemoteException, Exception {
		RuralHouse rhGaldera = new RuralHouse(rh.getHouseNumber(), null, null,
				null);
		Activity aGaldera = new Activity(a.getName());
		ObjectContainer db = DataAccessCommon.getContainer();
		ObjectSet rhResult = db.queryByExample(rhGaldera);
		ObjectSet aResult = db.queryByExample(aGaldera);
		if (rhResult.hasNext() && aResult.hasNext()) {
			RuralHouse rural = (RuralHouse) rhResult.next();
			Activity act = (Activity) aResult.next();
			Vector<Activity> rhActList = rural.getActivities();
			Iterator<Activity> it = rhActList.iterator();
			while (it.hasNext()) {
				if (it.next().getName().equals(act.getName()))
					return false;
			}
			rural.appendActivity(act);
			db.store(rural);
			return true;
		} else
			return false;
	}
	
	

	public Vector<Offer> OfertakBueltatu(String Login) {
		Vector<RuralHouse> rh = SarchByOwner(Login);// tengo las casas rurales
													// del login mio.
		Vector<Offer> vo = new Vector<Offer>();
		for (int i = 0; i < rh.size(); i++) {
			vo.addAll(rh.elementAt(i).getOffers());
			System.out.println(vo.size());
		}
		return vo;
	}

	public Offer offerBuelta(RuralHouse rh, Date d1, Date d2, Float price) {
		try {
			Offer proto = new Offer(null, null, null, 0);
			ObjectSet result = db.queryByExample(proto);
			while (result.hasNext()) {
				Offer p = (Offer) result.next();
				if (p.getRuralHouse().equals(rh) && p.getFirstDay().equals(d1)
						&& p.getLastDay().equals(d2) && p.getPrice() == price) {
					System.out.println("bat egiten dute");
					return p;
				}

			}
			return null;
		} finally {
			// db.close();
		}
	}

	public Boolean deleteOferta(RuralHouse rh, Offer o) {
		System.out.println(rh.getOffer().size());
		// RuralHouse galderaRH = new RuralHouse(rh.getHouseNumber(), null,
		// null,null);
		// Offer galderaOf = new Offer(null, o.getFirstDay(), null, 0);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			// ObjectSet resultOf = db.queryByExample(galderaOf);
			ObjectSet resultRH = db.queryByExample(rh);
			if (resultRH.hasNext()) {
				// Offer originalOffer= (Offer) resultOf.next();
				RuralHouse originalRH = (RuralHouse) resultRH.next();
				System.out.println(originalRH.getOffers().size());
				// System.out.println(originalOffer.getRuralHouse().getCity());
				originalRH.removeOferta(o);
				System.out.println(originalRH.getOffers().size());
				db.store(originalRH);

				Iterator it = originalRH.offers.iterator();
				while (it.hasNext())
					System.out.println("PROBA" + it.next());

				db.delete(o);
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

	public Vector<Offer> getOffersRH(RuralHouse rh, Date firstDay, Date lastDay) {
		try {
			Offer proto = new Offer(rh, firstDay, lastDay, 0);
			ObjectSet<Offer> result = db.queryByExample(proto);
			Vector<Offer> Offers = new Vector<Offer>();
			while (result.hasNext())
				Offers.add(result.next());
			return Offers;
		} finally {
			// db.close();
		}
	}
	public Boolean verifyAdmin(String log, String pass) {
		Admin galdera = new Admin(log, pass, null, null);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			if (result.hasNext()) {
				Admin c = (Admin) result.next();
				System.out.println(c.getLogin());
				return true;
			} else {
				return false;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
	}
	
	public  Admin getAdmin(String log, String pass) {
		Admin galdera = new Admin(log, pass, null, null);
		try {
			ObjectContainer db = DataAccessCommon.getContainer();
			ObjectSet result = db.queryByExample(galdera);
			if (result.hasNext()) {
				Admin c = (Admin) result.next();
				System.out.println(c.getLogin());
				return (c);
			} else {
				return null;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	@Override
	public Boolean updateHouse2(RuralHouse rh) throws RemoteException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addActivity(Activity a, RuralHouse rh)
			throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<Client> getClient() {

		try {
			Client proto = new Client(null, null, null, null, false, null);
			ObjectSet<Client> result = db.queryByExample(proto);
			Vector<Client> clients = new Vector<Client>();
			while (result.hasNext())
				clients.add(result.next());
			System.out.println("dadadadada--------->"+clients.size());
			return clients;
		} finally {
			// db.close();
		}
	}

	public Vector<Owner> getOwnerss() {


		try {
			Owner proto = new Owner(null, null, null, null, null, null, null,
					true, null);
			ObjectSet<Owner> result = db.queryByExample(proto);
			Vector<Owner> owners = new Vector<Owner>();
			while (result.hasNext()){
				owners.add(result.next());
			}
			System.out.println("dadada: "+owners.size());
			return owners;
		} finally {
			// db.close();
		}
	}


}