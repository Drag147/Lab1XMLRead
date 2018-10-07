package com.company;

import Formula1.Formula1;
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

        Formula1 formula1 = new Formula1();
        String[] namesClass = {"Pilots", "Bolids", "Teams", "Grand_prix", "Results_qualification", "Results_race"};
        int startClassind, endClassind;
        boolean writeClass = false;
        int nowClassIndex=0;
        String[] paramsForClass = new String[namesClass.length];
        for (int i =0; i<paramsForClass.length;i++)
        {
            paramsForClass[i]="";
        }
        try(FileReader file = new FileReader("./Resources/Formula 1.xml"); Scanner scan  = new Scanner(file))
        {
            while (scan.hasNextLine())
            {
                boolean tmpBool = writeClass;
                String line = scan.nextLine().trim();
                for (int i =0; i<namesClass.length; i++) {
                    startClassind = line.indexOf("<"+namesClass[i]+">");
                    endClassind = line.indexOf("</"+namesClass[i]+">");
                    if(startClassind!=-1)
                    {
                        writeClass = true;
                        System.out.println("Начало класса: " +namesClass[i]+"\n");
                        nowClassIndex=i;
                        paramsForClass[i]="";
                        break;
                    }
                    if(endClassind!=-1)
                    {
                        writeClass = false;
                        System.out.println("Конец класса: " +namesClass[i]+"\n");
                        if(i==0)
                        {
                            if(formula1.addNewPilotsFromXML(paramsForClass[i]))
                            {
                                System.out.println("Добавлено");
                            }
                            else
                            {
                                System.out.println("Ошибка");
                            }
                        }
                        break;
                    }
                }
                if(writeClass && tmpBool) {
                    paramsForClass[nowClassIndex] = paramsForClass[nowClassIndex].concat(line+"\n");
                }
            }


            System.out.println(formula1.getPilotsInfo());
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
