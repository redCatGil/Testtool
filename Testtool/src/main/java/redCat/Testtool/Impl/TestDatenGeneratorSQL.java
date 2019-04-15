package redCat.Testtool.Impl;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDatenGeneratorSQL {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public TestDatenGeneratorSQL() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
//		logger.info("PGM startet");
//		Werkzeugkasten wk = new Werkzeugkasten();
		// TODO Auto-generated method stub
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		/* next, get the Template */
		Template t = ve.getTemplate("resources/main/sql/SchufaTestDaten.vm");
		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		String kndAuskUuid = UUID.randomUUID().toString();
		context.put("kndAuskuftId", kndAuskUuid);
		Werkzeugkasten.fuellenWertevorrat();
		context.put("uId", Werkzeugkasten.wertevorrat.get("UID"));
		String now = sdf.format(Calendar.getInstance().getTime());
		context.put("tsInsert", now);
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		t.merge(context, writer);
		/* show the World */
		System.out.println(writer.toString());

	}

}
