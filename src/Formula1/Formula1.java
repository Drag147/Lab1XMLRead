package Formula1;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class Formula1 {

    private LinkedList<Pilots> pilotsList = new LinkedList<Pilots>();

    private LinkedList<Bolids> bolidsList = new LinkedList<Bolids>();

    public boolean addNewPilotsFromXML(String params) {
        try {
            String[] splitedStr = params.split("\n");
            for(int i= 0; i<splitedStr.length; i++)
            {
                splitedStr[i] = splitedStr[i].substring(splitedStr[i].indexOf(">")+1, splitedStr[i].lastIndexOf("<"));
            }
            Pilots newPilot = new Pilots(Short.parseShort(splitedStr[0]), splitedStr[1],
                    splitedStr[2], new SimpleDateFormat("yyyy-dd-mm").parse(splitedStr[3]));
            pilotsList.add(newPilot);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return  false;
        }
    }

    public boolean addNewBolidsFromXML(String params) {
        try {
            String[] splitedStr = params.split("\n");
            for(int i= 0; i<splitedStr.length; i++)
            {
                splitedStr[i] = splitedStr[i].substring(splitedStr[i].indexOf(">")+1, splitedStr[i].lastIndexOf("<"));
            }
            Bolids newBolid = new Bolids(splitedStr[0], splitedStr[1], splitedStr[2],
                    new SimpleDateFormat("yyyy").parse(splitedStr[3]));
            bolidsList.add(newBolid);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return  false;
        }
    }

    public String getPilotsInfo()
    {
        String resString = "Всего пилотов: " + pilotsList.size()+"\n";
        int i = 1;
        for (Pilots pilot: pilotsList)
        {
            resString = resString.concat("---------------\nПилот №" + i + ":");
            resString = resString.concat(pilot.toString("dd MMMM, yyyy года")+"---------------\n");
            i++;
        }
        return resString;
    }

    public String getBolidsInfo()
    {
        String resString = "Всего болидов: " + bolidsList.size()+"\n";
        int i = 1;
        for (Bolids bolid: bolidsList)
        {
            resString = resString.concat("---------------\nБолид №" + i + ":");
            resString = resString.concat(bolid.toString()+"---------------\n");
            i++;
        }
        return resString;
    }
}
