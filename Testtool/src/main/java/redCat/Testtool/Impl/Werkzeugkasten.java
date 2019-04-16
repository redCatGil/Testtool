package redCat.Testtool.Impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Werkzeugkasten {

	public Werkzeugkasten() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static Map<String, String> wertevorrat;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
	
	private static String fuellenTS(String wert) {
		String rueckgabe = null;
		if (wert == null) {
			rueckgabe = "<unbekannt>";
		} else {
			//Sonderformate
			//aktueller Timestamp
			if (wert.equals("now")) {
				rueckgabe = sdf.format(Calendar.getInstance().getTime());
			}
			//nur Tagesdatum - Uhrzeit konstant 00:00:00.00000
			if (wert.equals("now_date")) {
				rueckgabe = sdf_date.format(Calendar.getInstance().getTime());
			}
			//Rückbezug auf anderen TS
			if (wert.substring(0, 2).equals("%%")) {
				System.out.println("Rückbezug: " + wert.substring(2));
				rueckgabe = wertevorrat.get(wert.substring(2));
				if (rueckgabe == null) {
					rueckgabe = "<Rückbezugsfehler!!>";
				}				
			}
		}
		System.out.println("TS: " + rueckgabe);
		return rueckgabe;
	}
	
	public static void fuellenWertevorrat(String dsn) {
		Properties props = new Properties();
		FileInputStream inFile;
		
		//Properties-File versuchen zu öffnen
		try {
			inFile = new FileInputStream(dsn);
			 props.loadFromXML(inFile);
		//File nicht gefunden
		} catch (FileNotFoundException e) {
			System.err.println("Die XML-Datei " +dsn+" wurde nicht gefunden!");
			e.printStackTrace();
		//Props-Format passt nicht
		} catch (InvalidPropertiesFormatException e) {
			System.err.println("Das Format passt nicht. Bitte Datei "+dsn+" prüfen");
			e.printStackTrace();
		//Fehler beim öffen / lesen der XML-Datei
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Fehler beim öffen / lesen der XML-Datei "+dsn);
			e.printStackTrace();
		}
		Set<String> kevin = props.stringPropertyNames();
		for (Iterator<String> iterator = kevin.iterator(); iterator.hasNext();) {
			String inhalt = (String) iterator.next();
			System.out.println(inhalt);
		}
		
		//Map füllen mit Werten aus der Propertie-Datei bzw. mit erzeugten Werten
		wertevorrat = new HashMap<String, String>();
		
		//User-ID
		wertevorrat.put("UID", props.getProperty("UID","<unbekannt>"));
		String uid = props.getProperty("UID");
		if (uid == null) {
			uid = "<unbekannt>";
		}
		
		//Quelle
		String quelle = props.getProperty("Quelle");
		if (quelle == null) {
			quelle = "<unbekannt>";
		}
		wertevorrat.put("Quelle", quelle);
		
		//Mandant
		String mandant = props.getProperty("Mandant");
		if (mandant == null) {
			mandant = "<unbekannt>";
		}
		wertevorrat.put("Mandant", mandant);
		
		//Version
		String version = props.getProperty("Version");
		if (version == null) {
			version = "0";
		}
		wertevorrat.put("Version", version);
		
		//TS-Insert
		String ts_insert = fuellenTS(props.getProperty("TS_Insert"));
//		if (ts_insert == null) {
//			ts_insert = "<unbekannt>";
//		} else {
//			if (ts_insert.equals("now")) {
//				ts_insert = sdf.format(Calendar.getInstance().getTime());
//			}
//			if (ts_insert.equals("now_date")) {
//				ts_insert = sdf_date.format(Calendar.getInstance().getTime());
//			}
//		}
		System.out.println("TS: " + ts_insert);
		wertevorrat.put("TS_Insert", ts_insert);
		
		//TS-Update
		String ts_update = fuellenTS(props.getProperty("TS_Update"));
		wertevorrat.put("TS_Update", ts_update);
		
		//TS-Guel-Ab
		String ts_guel_ab = fuellenTS(props.getProperty("TS_Guel_Ab"));
		wertevorrat.put("TS_Guel_Ab", ts_guel_ab);
	}

}
