//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2019.04.19 um 05:29:41 PM CEST 
//


package redCat.Testtool.Impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für GeneratorEingabeType complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="GeneratorEingabeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Info" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="VeloFile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AttributDef" type="{http://www.example.org/GenEingabe}AttributDefType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeneratorEingabeType", propOrder = {
    "info",
    "veloFile",
    "attributDef"
})
public class GeneratorEingabeType {

    @XmlElement(name = "Info", required = true)
    protected String info;
    @XmlElement(name = "VeloFile", required = true)
    protected String veloFile;
    @XmlElement(name = "AttributDef", required = true)
    protected AttributDefType attributDef;

    /**
     * Ruft den Wert der info-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInfo() {
        return info;
    }

    /**
     * Legt den Wert der info-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInfo(String value) {
        this.info = value;
    }

    /**
     * Ruft den Wert der veloFile-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVeloFile() {
        return veloFile;
    }

    /**
     * Legt den Wert der veloFile-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVeloFile(String value) {
        this.veloFile = value;
    }

    /**
     * Ruft den Wert der attributDef-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link AttributDefType }
     *     
     */
    public AttributDefType getAttributDef() {
        return attributDef;
    }

    /**
     * Legt den Wert der attributDef-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributDefType }
     *     
     */
    public void setAttributDef(AttributDefType value) {
        this.attributDef = value;
    }

}
