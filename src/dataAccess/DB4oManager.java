package dataAccess;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Vector;

//hjkjljk
import businessLogic.FacadeImplementation;

import com.db4o.*;
import com.db4o.query.Predicate;
import configuration.ConfigXML;
import domain.Owner;
import exceptions.OfferCanNotBeBooked;

public class DB4oManager {
	private static final String Owner = null;
	private static DB4oManager DB4oManager=new DB4oManager();
	private static ObjectContainer  db;
	//apaaaaaaaaaa
	private DB4oManager() {
		 
		ConfigXML c=ConfigXML.getInstance();
		String dataBaseOpenMode=c.getDataBaseOpenMode();
		DB4oManager.openDatabase(dataBaseOpenMode);
	}

	public static DB4oManager getInstance() {
		// TODO Auto-generated method stub
		return DB4oManager;	
	}
    
	public  static ObjectContainer getContainer(){
		  return db;
	    }
		public static void close(){
			db.close();
			System.out.println("DataBase closed");
		}
		
	@SuppressWarnings("deprecation")
	public static void openDatabase(String mode){
		ConfigXML c=ConfigXML.getInstance();
		String db4oFileName=c.getDb4oFilename();
		if (mode.compareTo("open")==0) {
			db=Db4o.openFile(Db4o.newConfiguration(), db4oFileName);
			db.ext().configure().updateDepth(5);
			System.out.println("DataBase Opened");

		} else if (mode.compareTo("initialize")==0){
			new File(db4oFileName).delete();
			db=Db4o.openFile(Db4o.newConfiguration(), db4oFileName);
			db.ext().configure().updateDepth(5);
			 Owner iñigo = new Owner("Iñigo", "Sanz", "iñig00","1234",65252525,"00000000");
			 Owner itziar = new Owner("ici", "jaltuna", "icic00","99999",65252525,"0987654");
			 Owner urtzi = new Owner("ur", "diaz", "utrr00","5555",65252525,"1rrrrrr");
			 
			 iñigo.addRuralHouse(1, "Ezkioko etxea","Ezkio");
			 iñigo.addRuralHouse(2, "Eskiatzeko etxea","Jaca");
			 itziar.addRuralHouse(3, "dsgf", "Eibar");
			 itziar.addRuralHouse(4, "dsgf", "Eibar");
			 iñigo.setBankAccount("12345677");
			 db.store(iñigo);
			 db.store(itziar);
			 db.store(urtzi);
			 
			 db.commit();
			 System.out.println("DataBase Initialized");
		}
	}
		


	public Owner verifyLoginOwner(String log, String pass){
		Owner galdera = new Owner(null, null,log,pass,null,null);
			try {							
				ObjectContainer db=DB4oManager.getContainer();
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
	
	
	public Owner verifyLoginName(String log){
		Owner galdera = new Owner(null,null,log,null,null,null);
			try {							
				ObjectContainer db=DB4oManager.getContainer();
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


	
	
}
