package Formula1;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class Formula1 {

    private List<Pilots> pilotsList = new LinkedList<Pilots>();

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

    public String getPilotsInfo()
    {
        String resString = "Всего пилотов: " + pilotsList.size()+"\n";
        int i = 1;
        for (Pilots pilot: pilotsList)
        {
            resString = resString.concat("---------------\nПилот №" + i + ":");
            resString = resString.concat(pilot.toString()+"---------------\n");
            i++;
        }
        return resString;
    }
}
