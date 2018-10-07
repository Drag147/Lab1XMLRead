package Formula1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserForFormula1 {
    private String fileName;
    private String[] namesClass = {"Pilots", "Bolids", "Teams", "Grand_prix", "Results_qualification", "Results_race"};

    private ArrayList<String> paramsClass = new ArrayList<String>();

    public ParserForFormula1(String fileName)
    {
        this.fileName = fileName;
    }

    public boolean myParseFileXML(Formula1 formula1)
    {
        int startClassind, endClassind;
        boolean writeClass = false;
        try(FileReader file = new FileReader("./Resources/Formula 1.xml"); Scanner scan  = new Scanner(file))
        {
            while (scan.hasNextLine())
            {
                boolean tmpBool = writeClass;
                String line = scan.nextLine().trim();
                for (int i = 0; i < namesClass.length; i++) {
                    startClassind = line.indexOf("<" + namesClass[i]);
                    endClassind = line.indexOf("</" + namesClass[i]);
                    if (startClassind != -1) {
                        writeClass = true;
                        break;
                    }
                    if (endClassind != -1) {
                        writeClass = false;
                        switch (namesClass[i]) {
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
}
