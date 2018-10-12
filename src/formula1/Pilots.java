package formula1;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pilots {
    private short PersonalPilotNumber;
    private String Name;
    private String Surname;
    private LocalDate DateBirthDay;

    public Pilots(short PersonalPilotNumber, String Name, String Surname, LocalDate DateBirthDay)
    {
        this.PersonalPilotNumber = PersonalPilotNumber;
        this.Name = Name;
        this.Surname = Surname;
        this.DateBirthDay = DateBirthDay;
    }

    String toString(String formatDateOutput)
    {
        String resString = "\nЛичный номер пилота: " + this.PersonalPilotNumber;
        resString += "\nИмя пилота: " + this.Name;
        resString += "\nФамилия пилота: " + this.Surname;
        resString += "\nДата рождения: " + this.DateBirthDay.format(DateTimeFormatter.ofPattern(formatDateOutput)) + "\n";

        return resString;
    }

    public String toString()
    {
        return toString("yyyy-mm-dd");
    }

    String getJonString()
    {
//        return "\t\t{\n" +"\t\t\t\"Personal_pilot_number\": \""+ PersonalPilotNumber +"\",\n" +
//                "\t\t\t\"Name\": \"" + Name + "\",\n" +"\t\t\t\"Surname\": \"" + Surname + "\",\n" +
//                "\t\t\t\"Date_of_birth\": \"" + new SimpleDateFormat("yyyy-mm-dd").format(this.DateBirthDay) + "\"\n\t\t}";
        return "{\"Personal_pilot_number\": \""+ PersonalPilotNumber +"\",\"Name\": \"" + Name + "\",\"Surname\": \"" + Surname +
                "\",\"Date_of_birth\": \"" + new SimpleDateFormat("yyyy-mm-dd").format(this.DateBirthDay) + "\"}";
    }
}
