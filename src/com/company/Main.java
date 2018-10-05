package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        User user = new User();
        try(FileReader file = new FileReader("1.xml"); Scanner scan  = new Scanner(file))
        {
            while (scan.hasNextLine())
            {
                String tmp = findTag(scan.nextLine().trim(), user);
                if(!tmp.equals(""))
                {
                    System.out.println(tmp);
                }
            }
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }

        System.out.println("\n-------------------------------------------\n");

        System.out.println("F:" + user.getFirstname());
        System.out.println("L:" + user.getLastname());
        System.out.println("N:" + user.getNickname());
    }

    private static String findTag(String str, User user)
    {
        int indStart1 = str.indexOf("<");
        int indEnd1 =  str.indexOf(">");
        int indStart2 = str.lastIndexOf("<");
        int indVopr = str.indexOf("?");
        int indSlesh = str.indexOf("/");
        if(indVopr==-1)
        {
            if (indStart1 == indStart2 && indSlesh==-1)
            {
                return "Element: "+str.substring(indStart1+1, indEnd1);
            }
            else
            {
                if(indStart1+1 < indEnd1 && indEnd1+1 < indStart2)
                {
                    String elemName = str.substring(indStart1 + 1, indEnd1);
                    if (elemName.equals("firstname")) {
                        user.setFirstname(str.substring(indEnd1 + 1, indStart2));
                    }
                    if (elemName.equals("lastname")) {
                        user.setLastname(str.substring(indEnd1 + 1, indStart2));
                    }
                    if (elemName.equals("nickname")) {
                        user.setNickname(str.substring(indEnd1 + 1, indStart2));
                    }
                    return "Value: " + str.substring(indEnd1 + 1, indStart2);
                }
                else
                {
                    return "";
                }
            }
        }
        else
        {
            return "Params";
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
