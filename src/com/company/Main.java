package com.company;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static User user = new User();

    public static void main(String[] args) {

        try(FileReader file = new FileReader("1.xml"); Scanner scan  = new Scanner(file))
        {
            while (scan.hasNextLine())
            {
                String tmp = findTag(scan.nextLine().trim());
                if(!tmp.equals(""))
                {
                    System.out.println(tmp);
                }
            }
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        System.out.println("-------------------------------------------");

        System.out.println("F:" + user.getFirstname());
        System.out.println("L:" + user.getLastname());
        System.out.println("N:" + user.getNickname());
    }

    public static String findTag(String str)
    {
        int indStart1 = str.indexOf("<");
        int indStart2 =  str.indexOf(">");
        int indEnd1 = str.lastIndexOf("<");
        int indEnd2 = str.lastIndexOf(">");
        int indVopr = str.indexOf("?");
        int indSlesh = str.indexOf("/");
        if(indVopr==-1)
        {
            if (indStart1 == indEnd1 && indSlesh==-1)
            {
                return "Element: "+str.substring(indStart1+1, indStart2);
            }
            else
            {
                String elemName = str.substring(indStart1+1, indStart2);
                if(elemName.equals("firstname"))
                {
                    user.setFirstname(str.substring(indStart2+1, indEnd1));
                }
                if(elemName.equals("lastname"))
                {
                    user.setLastname(str.substring(indStart2+1, indEnd1));
                }
                if(elemName.equals("nickname"))
                {
                    user.setNickname(str.substring(indStart2+1, indEnd1));
                }
                return "Value: "+str.substring(indStart2+1, indEnd1);
            }
        }
        if(indVopr!=-1)
        {
            return "Params";
        }
        return "";
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
