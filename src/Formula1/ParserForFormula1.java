package Formula1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserForFormula1 {
    private String fileName;
    private String[] namesClass = {"Pilots", "Bolids", "Teams", "Grand_prix", "Results_qualification", "Results_race"};

    private ArrayList<String> paramsClass = new ArrayList<>();

    public ParserForFormula1(String fileName)
    {
        this.fileName = fileName;
    }

    public boolean myParseFileXML(Formula1 formula1)
    {
        int startClassind, endClassind;
        boolean writeClass = false;
        try(FileReader file = new FileReader(this.fileName); Scanner scan  = new Scanner(file))
        {
            while (scan.hasNextLine())
            {
                boolean tmpBool = writeClass;
                String line = scan.nextLine().trim();
                for (String namesClas : namesClass) {
                    startClassind = line.indexOf("<" + namesClas);
                    endClassind = line.indexOf("</" + namesClas);
                    if (startClassind != -1) {
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


}
