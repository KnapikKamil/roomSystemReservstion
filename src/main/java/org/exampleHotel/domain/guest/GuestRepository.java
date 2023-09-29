package org.exampleHotel.domain.guest;


import org.exampleHotel.domain.guest.Gender;
import org.exampleHotel.domain.guest.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private  final List<Guest>guests = new ArrayList<>();
    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(firstName, lastName, age, gender);
            guests.add(newGuest);
            return newGuest;
    }

    public List<Guest> getAll() {
        return this.guests;
    }
}