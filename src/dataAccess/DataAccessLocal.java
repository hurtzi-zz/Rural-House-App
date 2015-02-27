package dataAccess;

import java.util.ListIterator;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;

import java.io.*;

public class DataAccessLocal extends DataAccessCommon {
	private static EmbeddedConfiguration configuration;

	public DataAccessLocal(){
		super();

		System.out.print("0000000xml: "+c.getDataBaseOpenMode());
		if ((c.getDataBaseOpenMode().equals("initialize")))
			System.out.print("22");

			new File(c.getDb4oFilename()).delete();

		
		configuration = Db4oEmbedded.newConfiguration();
		configuration.common().activationDepth(c.getActivationDepth());
		configuration.common().updateDepth(c.getUpdateDepth());
		db=Db4oEmbedded.openFile(configuration, c.getDb4oFilename());
		System.out.println("DataBase opened");
		
		if (c.getDataBaseOpenMode().equals("initialize"))
		{			
			initializeDB();
			System.out.println("DataBase initialized");
		}
		else // c.getDataBaseOpenMode().equals("open")
			System.out.print("22");
			{		

				ObjectSet<DB4oManagerAux> res =db.queryByExample(DB4oManagerAux.class);
				ListIterator<DB4oManagerAux> listIter = res.listIterator();
				if (listIter.hasNext()) theDB4oManagerAux =  res.next();         	
            }
		
		
		
	}

}
