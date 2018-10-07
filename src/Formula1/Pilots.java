package Formula1;

import java.util.Date;

public class Pilots {
    private short PersonalPilotNumber;
    private String Name;
    private String Surname;
    private Date DateBirthDay;

    public Pilots()
    {
        Name = "";
        Surname = "";
        DateBirthDay = new Date();
    }

    public Pilots(short PersonalPilotNumber, String Name, String Surname, Date DateBirthDay)
    {
        this.PersonalPilotNumber = PersonalPilotNumber;
        this.Name = Name;
        this.Surname = Surname;
        this.DateBirthDay = DateBirthDay;
    }

    public short getPersonalPilotNumber() {
        return PersonalPilotNumber;
    }

    public void setPersonalPilotNumber(byte personalPilotNumber) {
        PersonalPilotNumber = personalPilotNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public Date getDateBirthDay() {
        return DateBirthDay;
    }

    public void setDateBirthDay(Date dateBirthDay) {
        DateBirthDay = dateBirthDay;
    }
}
