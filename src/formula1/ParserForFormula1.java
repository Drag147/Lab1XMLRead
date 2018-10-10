package formula1;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserForFormula1 {
    private String fileName;
    private String[] namesClass = {"Pilots", "Bolids", "Teams", "Grand_prix", "Results_qualification", "Results_race"};
    private int Lines;

    private ArrayList<String> paramsClass = new ArrayList<>();

    public ParserForFormula1(String fileName, int LineCount)
    {
        this.fileName = fileName; this.Lines = LineCount;
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
        return true;
    }
}
