package org.exampleHotel.ui.text;

import org.exampleHotel.exceptions.OnlyNumberException;
import org.exampleHotel.exceptions.WrongOptionException;
import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.guest.GuestService;
import org.exampleHotel.domain.room.Room;
import org.exampleHotel.domain.room.RoomService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final GuestService guestService = new GuestService();
    private final RoomService roomService = new RoomService();

    private void readNewGuestData(Scanner input) {
        System.out.println("Tworzymy nowego gościa.");

        try {
            System.out.println("Podaj imię: ");
            String firstName = input.next();
            System.out.println("Podaj nazwisko: ");
            String lastName = input.next();
            System.out.println("Podaj wiek: ");
            int age = input.nextInt();
            System.out.println("Wybierz płeć:");
            System.out.println("\n\t1. Kobieta 2. Mężczyzna 3. Nie binarna");
            int genderOption = input.nextInt();
            if (genderOption != 1 && genderOption != 2 && genderOption != 3) {
                throw new WrongOptionException("Wrong option in gender selection");
            }
            Guest newGuest = guestService.createNewGuest(firstName, lastName, age, genderOption);
            System.out.println("Dodano nowego gościa " + newGuest.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use only numbers when chosing gender");
        }
    }

    private void readNewRoomData(Scanner input) {
        System.out.println("Tworzymy nowy pokój.");
        try {
            System.out.println("Numer: ");
            int number = input.nextInt();
            int[] bedTypes = chooseBedType(input);
            Room newRoom = roomService.createNewRoom(number, bedTypes);
            System.out.println("Dodano nowy pokój: " + newRoom.getInfo());
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Use numbers when creating new room");
        }
    }

    static private int[] chooseBedType(Scanner input) {
        System.out.println("Ile łóżek w pokoju?: ");
        int bedNumber = input.nextInt();
        int[] bedTypes = new int[bedNumber];
        for (int i = 0; i < bedNumber; i = i + 1) {
            System.out.println("Typy łóżek: ");
            System.out.println("\t1. Pojedyncze");
            System.out.println("\t2. Podwójne");
            System.out.println("\t3. Królewskie");
            int bedTypeOption = input.nextInt();
            bedTypes[i] = bedTypeOption;
        }
        return bedTypes;
    }

    public void showSystemInfo(String hotelName, int systemVersion, boolean isDeveloperVersion) {
        System.out.print("Witam w systemie rezerwacji dla hotelu " + hotelName);
        System.out.println("Aktualna wersja systemu: " + systemVersion);
        System.out.println("Wersja developerska: " + isDeveloperVersion);
        System.out.println("\n=========================\n");
    }

    public void showMainMenu() {
        Scanner input = new Scanner(System.in);
        try {
            performerAction(input);
        } catch (WrongOptionException | OnlyNumberException e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Kod błędu: " + e.getCode());
            System.out.println("Komunikat błędu: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Wystąpił niespodziewany błąd");
            System.out.println("Nieznany kod błędu");
            System.out.println("Komunikat błędu: " + e.getMessage());
        }
    }


    private void performerAction(Scanner input) {

        int option = -1;
        while (option != 0) {
            option = getActionFromUser(input);

            if (option == 0) {
                System.out.println("Wychodzę z aplikacji");
            }else if (option == 1) {
                readNewGuestData(input);
            } else if (option == 2) {
                readNewRoomData(input);
            } else if (option == 3) {
                showAllGuests();
            } else if (option == 4) {
                showAllRooms();
            }else {
                throw new WrongOptionException("Wrong option in main menu");
            }
        }
    }

    private void showAllRooms() {
List<Room>rooms = this.roomService.getAllRooms();
for (Room room : rooms){
    System.out.println(room.getInfo());
}
    }

    private void showAllGuests() {
        List<Guest>guests = this.guestService.getAllGuests();
        for (Guest guest : guests){
            System.out.println(guest.getInfo());
        }
    }


    private int getActionFromUser(Scanner in) {
        System.out.println("0 - Wyjście z aplikacji");
        System.out.println("1 - Dodaj nowego gościa.");
        System.out.println("2 - Dodaj nowy pokój.");
        System.out.println("3 - Wypisz wszystkich gości.");
        System.out.println("4 - Wypisz wszystkie pokoje. ");
        System.out.println("Wybierz opcję: ");
        int option;
        try {
            option = in.nextInt();
        } catch (InputMismatchException e) {
            throw new OnlyNumberException("Wrong use only numbers in main menu");
        }
        return option;
    }
}

