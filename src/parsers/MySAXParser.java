package parsers;

import formula1.Formula1;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MySAXParser extends DefaultHandler {

    private Formula1 formula1;
    private String fileNameXml;
    private String fileNameXsd;

    private SAXParserFactory factory = SAXParserFactory.newInstance();

    private boolean writeParams = false;
    private String nowTag = "";
    private List<String> params = new LinkedList<>();
    private List<String> namesClass = Arrays.asList("Pilots", "Bolids", "Teams",
            "Grand_prix", "Results_qualification", "Results_race");


    public MySAXParser(Formula1 formula1, String fileNameXml, String fileNameXsd) {
        this.formula1 = formula1;
        this.fileNameXml = fileNameXml;
        this.fileNameXsd = fileNameXsd;

    }

    public boolean inizializate() throws SAXException {
        File xsdFile = new File(this.fileNameXsd);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(xsdFile);

        factory.setNamespaceAware(true);
        factory.setSchema(schema);

        return true;
    }

    public void parse() throws ParserConfigurationException, SAXException, IOException {
        File xmlFile = new File(this.fileNameXml);

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFile, this);
    }

    @Override
    public void startDocument() {
    }

    // namespaceURI — это пространство имен
    // localName — локальное имя элемента,
    // qName- имя тега/поля
    // atts — атрибуты данного элемента.
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts){
        if(namesClass.contains(qName)){
            //это класс
            nowTag = qName;
            this.writeParams = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(!nowTag.equals("")) {
            params.add(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) {
        if (qName.equals(nowTag)) {
            switch (nowTag) {
                case "Pilots":
                    formula1.addNewPilots(params);
                    break;
                case "Bolids":
                    formula1.addNewBolids(params);
                    break;
            }
            nowTag = "";
            params.clear();
        }
    }

    @Override
    public void endDocument() {
    }
}