package org.exampleHotel.domain.guest;

import org.exampleHotel.domain.guest.Gender;
import org.exampleHotel.domain.guest.dto.GuestDTO;

public class Guest {
    private final int id;
   private final String firstName;
   private final String lastName;
   private final int age;
    private final Gender gender;

    public int getId() {
        return id;
    }

    Guest(int id, String firstName, String lastName, int age, Gender gender){
        this.id = id;
       this.firstName = firstName;
       this.lastName = lastName;
       this.age = age;
       this.gender = gender;
   }
    public String getInfo(){
        return   String.format("%d %s %s (%d) %s ",this.id, this.firstName, this.lastName, this.age, this.gender);
    }
    String toCSV(){
       return String.format("%d,%s,%s,%d,%s%s",this.id, this.firstName, this.lastName, this.age, this.gender, System.getProperty("line.separator"));
    }
    public GuestDTO createDTO(){
        return new GuestDTO(this.id, this.firstName, this.lastName, this.age, this.gender);
    }
}
