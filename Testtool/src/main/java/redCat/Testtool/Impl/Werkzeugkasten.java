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
import java.util.UUID;

import redCat.Testtool.Enum.Datentypen;

public class Werkzeugkasten {

	public Werkzeugkasten() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static Map<String, String> wertevorrat;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd 00:00:00.000");
	
	private static String fuellenTS(String wert) {
		String rueckgabe = wert;
		if (wert == null) {
			rueckgabe =  "<unbekannt>";
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
	
	private static String fuellenID(String wert) {
		String rueckgabe = wert;
		if (wert == null) {
			rueckgabe = "<unbekannt>";
		} else {
			//Sonderformate
			//neue ID
			if (wert.equals("new")) {
				rueckgabe = UUID.randomUUID().toString();
			}
			//Rückbezug auf andere ID
			if (wert.substring(0, 2).equals("%%")) {
				System.out.println("Rückbezug: " + wert.substring(2));
				rueckgabe = wertevorrat.get(wert.substring(2));
				if (rueckgabe == null) {
					rueckgabe = "<Rückbezugsfehler!!>";
				}				
			}
		}
		System.out.println("ID: " + rueckgabe);
		return rueckgabe;
		
	}
	
	public static String fuellenAusAttribut(AttributType attribut) {
		//Verarbeitung je nach Typ
		Datentypen datatyp = Datentypen.valueOf(attribut.getTyp());
		switch (datatyp.ordinal()) {
		//String
		case 0:
			return attribut.getWert();
		//TS
		case 1:
			return fuellenTS(attribut.getWert());
		//ID
		case 2:
			return fuellenID(attribut.getWert());
		default:
			System.err.println("Es gibt einen neuen Datentypen der noch nicht bekannt ist!!! ==> " +attribut.getTyp());
			return null;
		}
		
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
		
		//Quelle
		wertevorrat.put("Quelle", props.getProperty("Quelle", "<unbekannt>"));
		
		//Mandant
		wertevorrat.put("Mandant", props.getProperty("Mandant", "<unbekannt>"));
		
		//Version
		wertevorrat.put("Version", props.getProperty("Version", "0"));
		
		//TS-Insert
		String ts_insert = fuellenTS(props.getProperty("TS_Insert"));

		System.out.println("TS: " + ts_insert);
		wertevorrat.put("TS_Insert", ts_insert);
		
		//TS-Update
		wertevorrat.put("TS_Update", fuellenTS(props.getProperty("TS_Update")));
		
		//TS-Guel-Ab
		wertevorrat.put("TS_Guel_Ab", fuellenTS(props.getProperty("TS_Guel_Ab")));
		
		//ID Kundenauskunft
		wertevorrat.put("ID_KAuskunft", fuellenID(props.getProperty("ID_KAuskunft")));
		
		//ID Kundendaten
		wertevorrat.put("ID_KDaten", fuellenID(props.getProperty("ID_KDaten")));
	}
	
	public static void fuellenWertevorrat_var(String dsn) {
		
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
		
		//Map füllen mit Werten aus der Propertie-Datei bzw. mit erzeugten Werten
		wertevorrat = new HashMap<String, String>();
		
		Set<String> inhalt = props.stringPropertyNames();
		for (Iterator<String> iterator = inhalt.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(key);
			//Typ extrahieren
			String typ = key.substring(0,key.indexOf("."));
			String name = key.substring(key.indexOf(".") + 1);
			System.out.println("Typ: " + typ + " Name " + name);
			//Verarbeitung je nach Typ
			Datentypen datatyp = Datentypen.valueOf(typ);
			switch (datatyp.ordinal()) {
			//String
			case 0:
				wertevorrat.put(name, props.getProperty(key,"<unbekannt>"));
				break;
			//TS
			case 1:
				wertevorrat.put(name, fuellenTS(props.getProperty(key)));
				break;
			//ID
			case 2:
				wertevorrat.put(name, fuellenID(props.getProperty(key)));
				break;
			default:
				System.err.println("Es gibt einen neuen Datentypen der noch nicht bekannt ist!!!");
				break;
			}
		}
		
	}
	
	public static Properties erzeugenUmsetzung(String dsn) {
		
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
		
		return props;
		
	}

}
