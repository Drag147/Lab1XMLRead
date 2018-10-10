package Formula1;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;

public class Bolids {

    private String NameBolid;
    private String NameEngine;
    private String NameChassis;
    private Date YearBolid;

    public Bolids()
    {
        NameBolid = "";
        NameEngine = "";
        NameChassis = "";
        YearBolid = new Date();
    }

    public Bolids(String NameBolid, String NameEngine, String NameChassis, Date YearBolid)
    {
        this.NameBolid = NameBolid;
        this.NameEngine = NameEngine;
        this.NameChassis = NameChassis;
        this.YearBolid = YearBolid;
    }

    public String toString()
    {
        String resString = "\nНазвание болида: " + this.NameBolid;
        resString += "\nНазвание движка: " + this.NameEngine;
        resString += "\nНазвание шасси: " + this.NameChassis;
        resString += "\nГод болида: " + new SimpleDateFormat("yyyy").format(this.YearBolid) + "\n";

        return resString;
    }

    public String getJSONstring()
    {
        return "{\"Name_bolid\": \""+ NameBolid +"\",\"Name_engine\": \"" + NameEngine + "\",\"Name_chassis\": \"" + NameChassis + "\",\"Year_bolid\": \""
                + new SimpleDateFormat("yyyy").format(this.YearBolid) + "\"}";
    }

    public void setNameBolid(String nameBolid) {
        NameBolid = nameBolid;
    }

    public String getNameBolid() {
        return NameBolid;
    }

    public void setNameEngine(String nameEngine) {
        NameEngine = nameEngine;
    }

    public String getNameEngine() {
        return NameEngine;
    }

    public void setNameChassis(String nameChassis) {
        NameChassis = nameChassis;
    }

    public String getNameChassis() {
        return NameChassis;
    }

    public void setYearBolid(Date yearBolid) {
        YearBolid = yearBolid;
    }

    public Date getYearBolid() {
        return YearBolid;
    }
}
