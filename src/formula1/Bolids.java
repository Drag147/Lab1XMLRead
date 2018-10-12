package formula1;

import java.time.Year;
import java.time.format.DateTimeFormatter;

public class Bolids {

    private String nameBolid;
    private String nameEngine;
    private String nameChassis;
    private Year yearBolid;

    public Bolids(String nameBolid, String nameEngine, String nameChassis, Year yearBolid)
    {
        this.nameBolid = nameBolid;
        this.nameEngine = nameEngine;
        this.nameChassis = nameChassis;
        this.yearBolid = yearBolid;
    }

    public String toString()
    {
        String resString = "\nНазвание болида: " + this.nameBolid;
        resString += "\nНазвание движка: " + this.nameEngine;
        resString += "\nНазвание шасси: " + this.nameChassis;
        resString += "\nГод болида: " + this.yearBolid.toString() + "\n";

        return resString;
    }

    String getJonString()
    {
        return "{\"Name_bolid\": \""+ nameBolid +"\",\"Name_engine\": \"" + nameEngine + "\",\"Name_chassis\": \"" + nameChassis + "\",\"Year_bolid\": \""
                + this.yearBolid.format(DateTimeFormatter.ofPattern("yyyy")) + "\"}";
    }
}
