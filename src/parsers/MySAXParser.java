package parsers;

import formula1.Formula1;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MySAXParser extends DefaultHandler {

    private String nowTag;
    private List<String> params = new LinkedList<>();
    private List<String> namesClass = Arrays.asList("Pilots", "Bolids", "Teams",
            "Grand_prix", "Results_qualification", "Results_race");

    private Formula1 formula1;

    public MySAXParser(Formula1 formula1) {
        this.formula1 = formula1;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    // namespaceURI — это пространство имен
    // localName — локальное имя элемента,
    // qName- имя тега/поля
    // atts — атрибуты данного элемента.
    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        if(namesClass.contains(qName)){
            //это класс
            nowTag = qName;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        params.add(new String(ch, start, length));
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (qName.equals(nowTag)) {
            switch (nowTag) {
                case "Pilots":
                    formula1.addNewPilots(params);
                    break;
                case "Bolids":
                    formula1.addNewBolids(params);
                    break;
                default:
                    throw new SAXException();
            }
            nowTag = "";
            params.clear();
        }
    }

    @Override
    public void endDocument() {
    }


}