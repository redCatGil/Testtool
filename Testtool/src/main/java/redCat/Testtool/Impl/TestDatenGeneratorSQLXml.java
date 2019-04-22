package redCat.Testtool.Impl;

import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXB;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDatenGeneratorSQLXml {
	
		
		private final Logger logger = LoggerFactory.getLogger(getClass());
		static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
		public TestDatenGeneratorSQLXml() {
			// TODO Auto-generated constructor stub
		}
	
		public static void main(String[] args) {
	//		logger.info("PGM startet");
			
			// XML laden und in ein Objekt laden
			Path pfad = Paths.get("resources/main/xml/insertBoniGen1.xml");
			GeneratorEingabeType ge = JAXB.unmarshal(pfad.toFile(), GeneratorEingabeType.class);
			
			System.out.println("VM-Datei: " + ge.getVeloFile());
			/* first, get and initialize an engine */
			VelocityEngine ve = new VelocityEngine();
			ve.init();
			/* next, get the Template */
			Template t = ve.getTemplate(ge.getVeloFile());
			/* create a context  */
			VelocityContext context = new VelocityContext();
			/* and add data */
			List<AttributType> atts = ge.getAttributDef().getAttribut();
			for (Iterator<AttributType> iterator = atts.iterator(); iterator.hasNext();) {
				AttributType attribut = (AttributType) iterator.next();
				System.out.println("Name: " + attribut.getTemplateName());
				context.put(attribut.getTemplateName(), Werkzeugkasten.fuellenAusAttribut(attribut));
				
			}
			/* now render the template into a StringWriter */
			StringWriter writer = new StringWriter();
			t.merge(context, writer);
			/* show the World */
			System.out.println(writer.toString());
			
		}
	
}

	

	