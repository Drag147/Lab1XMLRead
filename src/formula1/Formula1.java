package formula1;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.LinkedList;

public class Formula1 {

    private List<Pilots> pilotsList = new LinkedList<>();

    private List<Bolids> bolidsList = new LinkedList<>();

    public boolean addNewPilots(List<String> params) {
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

    public boolean addNewBolids(List<String> params) {
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

    public int getSizeP()
    {
        return pilotsList.size();
    }
    public int getSizeB()
    {
        return bolidsList.size();
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
        String json = "{\"Formula_1\": {\"Pilots\": [";

        for(Pilots pilots: pilotsList)
        {
            json = json.concat(pilots.getJSONstring());
            if(pilotsList.get(pilotsList.size()-1)!=pilots)
            {
                json += ",";
            }
        }
        json += "],\"Bolids\": [";
        for(Bolids bolids: bolidsList)
        {
            json = json.concat(bolids.getJSONstring());
            if(bolidsList.get(bolidsList.size()-1)!=bolids)
            {
                json += ",";
            }
        }
        json += "]}}";

        return  json;
    }
}
