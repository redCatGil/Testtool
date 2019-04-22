package redCat.Testtool.Impl;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.*;

public class XMLTest {

	public XMLTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Objekte für das XML füllen
		AttributDefType adt = new AttributDefType();
		AttributType att1 = new AttributType();
		att1.setTemplateName("uId");
		att1.setTyp("String");
		att1.setWert("ddc1tf");
		adt.getAttribut().add(att1);
		AttributType att2 = new AttributType();
		att2.setTemplateName("version");
		att2.setTyp("String");
		att2.setWert("0");
		adt.getAttribut().add(att2);
		AttributType att3 = new AttributType();
		att3.setTemplateName("mandant");
		att3.setTyp("String");
		att3.setWert("WV");
		adt.getAttribut().add(att3);
		AttributType att4 = new AttributType();
		att4.setTemplateName("quelle");
		att4.setTyp("String");
		att4.setWert("Schufa");
		adt.getAttribut().add(att4);
		AttributType att5 = new AttributType();
		att5.setTemplateName("tsInsert");
		att5.setTyp("TS");
		att5.setWert("now");
		adt.getAttribut().add(att5);
		AttributType att6 = new AttributType();
		att6.setTemplateName("kndAuskuftId");
		att6.setTyp("ID");
		att6.setWert("new");
		adt.getAttribut().add(att6);
		GeneratorEingabeType ge = new GeneratorEingabeType();
		ge.setAttributDef(adt);
		ge.setVeloFile("resources/main/sql/SchufaTestDaten.vm");
		ge.setInfo("erstes XML für Generatorentest");
		
		
		JAXB.marshal(ge, System.out);
		Path pfad = Paths.get("resources/main/xml/insertBoniGen1.xml");
		try {
			Writer out = Files.newBufferedWriter(pfad, StandardCharsets.UTF_8);
			JAXB.marshal(ge, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
