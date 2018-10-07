package Formula1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

public class Formula1 {

    private LinkedList<Pilots> pilotsList = new LinkedList<Pilots>();

    private LinkedList<Bolids> bolidsList = new LinkedList<Bolids>();

    public boolean addNewPilots(ArrayList<String> params) {
        try {
            Pilots newPilot = new Pilots(Short.parseShort(params.get(0)), params.get(1),
                    params.get(2), new SimpleDateFormat("yyyy-dd-mm").parse(params.get(3)));
            pilotsList.add(newPilot);
            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return  false;
        }
    }

    public boolean addNewBolids(ArrayList<String> params) {
        try {
            Bolids newBolid = new Bolids(params.get(0), params.get(1), params.get(2),
                    new SimpleDateFormat("yyyy").parse(params.get(3)));
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

    public String getJSON()
    {
        String json = "{\n\t\"Formula_1\": {\n\t\"Pilots\": [\n";

        for(Pilots pilots: pilotsList)
        {
            json = json.concat(pilots.getJSONstring());
        }
        json += "\t  ],\n\t\t\"Bolids\": [\n";
        for(Bolids bolids: bolidsList)
        {
            json = json.concat(bolids.getJSONstring());
        }
        json += "\t  ]\n\t}\n}";

        return  json;
    }
}
