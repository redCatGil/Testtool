package redCat.Testtool.Impl;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDatenGeneratorSQLVar {
	
		
		private final Logger logger = LoggerFactory.getLogger(getClass());
		static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
		public TestDatenGeneratorSQLVar() {
			// TODO Auto-generated constructor stub
		}
	
		public static void main(String[] args) {
	//		logger.info("PGM startet");
	
			// TODO Auto-generated method stub
			/* first, get and initialize an engine */
			VelocityEngine ve = new VelocityEngine();
			ve.init();
			/* next, get the Template */
			Template t = ve.getTemplate("resources/main/sql/SchufaTestDaten.vm");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();
	        
			//Werte aufbauen
			Werkzeugkasten.fuellenWertevorrat_var("resources/main/xml/insertBoni2.xml");
			
			//Werte übergeben
			Properties props = Werkzeugkasten.erzeugenUmsetzung("resources/main/xml/BoniUmschluesselung1.xml");
			Set<String> umsetzung = props.stringPropertyNames();
			for (Iterator<String> iterator = umsetzung.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				System.out.println("Key: " + key + " --> " + props.getProperty(key));
				context.put(key, Werkzeugkasten.wertevorrat.get(props.get(key)));
			}
//			context.put("uId", Werkzeugkasten.wertevorrat.get("UID"));
//			context.put("version", Werkzeugkasten.wertevorrat.get("Version"));
//			context.put("mandant", Werkzeugkasten.wertevorrat.get("Mandant"));
//			context.put("quelle", Werkzeugkasten.wertevorrat.get("Quelle"));
//			context.put("kndAuskuftId", Werkzeugkasten.wertevorrat.get("ID_KAuskunft"));
//			context.put("tsInsert", Werkzeugkasten.wertevorrat.get("TS_Insert"));
			/* now render the template into a StringWriter */
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			/* show the World */
			System.out.println(writer.toString());
		}
	
}

	

	