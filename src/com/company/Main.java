package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String[] namesClass = {"Pilots", "Bolids", "Teams", "Grand_prix", "Results_qualification", "Results_race"};
        int startClassind, endClassind;
        boolean writeClass = false;
        String pilotClass = "";
        try(FileReader file = new FileReader("./Resources/Formula 1.xml"); Scanner scan  = new Scanner(file))
        {
            while (scan.hasNextLine())
            {
                boolean tmpBool = writeClass;
                String line = scan.nextLine().trim();
                for (String x:namesClass) {
                    startClassind = line.indexOf("<"+x+">");
                    endClassind = line.indexOf("</"+x+">");
                    if(startClassind!=-1)
                    {
                        writeClass = true;
                        System.out.println("Начало класса: " + x+"\n");
                        break;
                    }
                    if(endClassind!=-1)
                    {
                        writeClass = false;
                        System.out.println("Конец класса: " +x+"\n");
                        System.out.println("Переменные класса: " +pilotClass+"\n");
                        break;
                    }
                }
                if(writeClass && tmpBool) {
                    pilotClass = pilotClass.concat(line);
                }
            }
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static void ReadDOM()
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
