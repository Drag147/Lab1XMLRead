package parsers;

import formula1.Formula1;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ParserXMLForFormula1 {
    private String fileName;
    private String[] namesClass = {"Pilots", "Bolids", "Teams", "Grand_prix", "Results_qualification", "Results_race"};
    private int Lines;

    private List<String> paramsClass = new LinkedList<>();

    public ParserXMLForFormula1(String fileName, int LineCount)
    {
        this.fileName = fileName; this.Lines = LineCount;
    }

    public ParserXMLForFormula1(String fileName)
    {
        this.fileName = fileName; this.Lines = 2;
    }

    public boolean myParseFileXML(Formula1 formula1)
    {
        int startClassind, endClassind;
        boolean writeClass = false;
        try(FileReader file = new FileReader(this.fileName); Scanner scan  = new Scanner(file))
        {
            if(Lines==1)
            {
                scan.useDelimiter("><");
            }
            while (Lines==1 ? scan.hasNext() : scan.hasNextLine() )
            {
                boolean tmpBool = writeClass;
                String line;
                if(Lines==1)
                {
                    line = scan.next().trim();
                }
                else
                {
                    line = scan.nextLine().trim();
                }

                for (String namesClas : namesClass) {
                    startClassind = line.indexOf(namesClas);
                    endClassind = line.indexOf("/"+namesClas);
                    if (startClassind != -1 && endClassind==-1) {
                        writeClass = true;
                        break;
                    }
                    if (endClassind != -1) {
                        writeClass = false;
                        switch (namesClas) {
                            case "Pilots":
                                formula1.addNewPilots(paramsClass);
                                break;
                            case "Bolids":
                                formula1.addNewBolids(paramsClass);
                                break;
                            case "Teams":

                                break;
                            case "Grand_prix":

                                break;
                            case "Results_qualification":

                                break;
                            case "Results_race":

                                break;

                        }
                        paramsClass.clear();
                        break;
                    }
                }
                if(writeClass && tmpBool) {
                    paramsClass.add(line.substring(line.indexOf(">")+1, line.lastIndexOf("<")));
                }
            }
            return true;
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
            return  false;
        }
    }

    public boolean parseDOM(Formula1 formula)
    {
        try
        {
            File fXmlFile = new File(this.fileName);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);

            if(factory.isValidating()) {
                DocumentBuilder builder = factory.newDocumentBuilder();

                Document doc = builder.parse(fXmlFile);
                doc.getDocumentElement().normalize();

                for (String nameClass : namesClass) {
                    NodeList listNodes = doc.getElementsByTagName(nameClass);

                    for (int i = 0; i < listNodes.getLength(); i++) {
                        Node node = listNodes.item(i);

                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            //System.out.println("Node name: " + node.getNodeName());
                            switch (element.getNodeName()) {
                                case "Pilots":
                                    paramsClass.clear();
                                    paramsClass.add(element.getElementsByTagName("Personal_pilot_number").item(0).getTextContent());
                                    paramsClass.add(element.getElementsByTagName("Name").item(0).getTextContent());
                                    paramsClass.add(element.getElementsByTagName("Surname").item(0).getTextContent());
                                    paramsClass.add(element.getElementsByTagName("Date_of_birth").item(0).getTextContent());
                                    formula.addNewPilots(paramsClass);
//                            System.out.println(element.getElementsByTagName("Personal_pilot_number").item(0).getTextContent());
//                            System.out.println(element.getElementsByTagName("Name").item(0).getTextContent());
//                            System.out.println(element.getElementsByTagName("Surname").item(0).getTextContent());
//                            System.out.println(element.getElementsByTagName("Date_of_birth").item(0).getTextContent());
                                    break;
                                case "Bolids":
                                    paramsClass.clear();
                                    paramsClass.add(element.getElementsByTagName("Name_bolid").item(0).getTextContent());
                                    paramsClass.add(element.getElementsByTagName("Name_engine").item(0).getTextContent());
                                    paramsClass.add(element.getElementsByTagName("Name_chassis").item(0).getTextContent());
                                    paramsClass.add(element.getElementsByTagName("Year_bolid").item(0).getTextContent());
                                    formula.addNewBolids(paramsClass);
//                                System.out.println(element.getElementsByTagName("Name_bolid").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Name_engine").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Name_chassis").item(0).getTextContent());
//                                System.out.println(element.getElementsByTagName("Year_bolid").item(0).getTextContent());
                                    break;
                            }
//                        System.out.println();
                        }
                    }
                }
                return true;
            }
            return  false;

        } catch (ParserConfigurationException | SAXException
                | IOException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
}
