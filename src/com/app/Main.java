package com.app;

import formula1.Formula1;
import formula1.ParserXMLForFormula1;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args)
    {
          mainM();
        //Gen();
    }

    private static void mainM ()
    {
        Formula1 formula1 = new Formula1();
        ParserXMLForFormula1 parserXMLForFormula1 = new ParserXMLForFormula1("./Resources/Formula 1.xml", 2);//"Formula 1 Big.xml", 1);////
        try{
            if(parserXMLForFormula1.parseDOM(formula1))
            {
                //System.out.println("Размер P: " + formula1.getSizeP());
                //System.out.println("Размер B: " + formula1.getSizeB());
                System.out.println(formula1.getPilotsInfo());
                System.out.println(formula1.getBolidsInfo());

                System.out.println();

                try(FileWriter fileWR = new FileWriter("JsonExport.json"))
                {
                    String json = formula1.getJSON();
                    fileWR.write(json);
                    fileWR.flush();
                    System.out.println("JSON записан в файл JsonExport.json");
                }catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void Gen()
    {
        try (FileWriter fileWriter = new FileWriter("Formula 1 Big.xml"))
        {
            fileWriter.write("<Formula_1 xmlns=\"https://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"https://www.w3schools.com ./formula1.xsd\">");
            for(int i = 0; i < 1400000; i++)
            {
                fileWriter.append("<Pilots><Personal_pilot_number>2</Personal_pilot_number><Name>Стоффель</Name>" +
                        "<Surname>Вандорн</Surname><Date_of_birth>1991-03-26</Date_of_birth></Pilots><Pilots>" +
                        "<Personal_pilot_number>3</Personal_pilot_number><Name>Даниэль</Name><Surname>Риккардо</Surname>" +
                        "<Date_of_birth>1989-07-01</Date_of_birth></Pilots><Pilots><Personal_pilot_number>5</Personal_pilot_number>" +
                        "<Name>Себастьян</Name><Surname>Фетель</Surname><Date_of_birth>1987-07-03</Date_of_birth></Pilots>" +
                        "<Bolids><Name_bolid>Ferrari SF71H</Name_bolid><Name_engine>Ferrari 062 EVO 1.6 V6t</Name_engine>" +
                        "<Name_chassis>Ferrari SF71H</Name_chassis><Year_bolid>2018</Year_bolid></Bolids>" +
                        "<Bolids><Name_bolid>Force India VJM11</Name_bolid><Name_engine>Force India VJM11</Name_engine>" +
                        "<Name_chassis>Mercedes M09 EQ Power+ 1.6 V6T</Name_chassis><Year_bolid>2018</Year_bolid></Bolids>");
            }
            fileWriter.append("\n</Formula_1>");
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void ReadDOM()
    {
        try
        {
            File fXmlFile = new File("1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("staff");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Staff id : " + eElement.getAttribute("id"));
                    System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
