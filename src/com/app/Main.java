package com.app;

import formula1.Formula1;
import parsers.ParserXMLForFormula1;
import parsers.MySAXParser;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileWriter;
import java.text.Normalizer;

public class Main {

    public static void main(String[] args)
    {
        Formula1 formula1 = new Formula1();
        String file1 = "./Resources/Formula 1.xml";
        String file2 = "./Resources/Formula 1 one line.xml";
        String file3 = "Formula 1 Big.xml";
        mainSAX(formula1, file1);
        //mainMDOM(formula1, file2);
        //toJson(formula1, file2);
        //Gen();
    }

    private static void mainSAX (Formula1 formula1, String fileName){

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            factory.setValidating(true);

            if(factory.isValidating()) {

                MySAXParser saxP = new MySAXParser(formula1);

                parser.parse(new File(fileName), saxP);

                //System.out.println("Размер P: " + formula1.getSizeP());
                //System.out.println("Размер B: " + formula1.getSizeB());
                System.out.println(formula1.getPilotsInfo());
                System.out.println(formula1.getBolidsInfo());

                System.out.println();
            }
            else{
                System.out.println("NO VALIDATING");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void mainMDOM (Formula1 formula1, String fileName)
    {
        ParserXMLForFormula1 parserXMLForFormula1 = new ParserXMLForFormula1(fileName);
        try{
            if(parserXMLForFormula1.parseDOM(formula1))
            {
                //System.out.println("Размер P: " + formula1.getSizeP());
                //System.out.println("Размер B: " + formula1.getSizeB());
                System.out.println(formula1.getPilotsInfo());
                System.out.println(formula1.getBolidsInfo());

                System.out.println();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void toJson(Formula1 formula1) {
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
}
