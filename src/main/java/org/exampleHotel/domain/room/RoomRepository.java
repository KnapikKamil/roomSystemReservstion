package org.exampleHotel.domain.room;

import org.exampleHotel.domain.reservation.ReservationRepository;
import org.exampleHotel.exceptions.PersistenceToFileException;
import org.exampleHotel.util.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomRepository {
    private final List<Room> rooms = new ArrayList<>();
    private final static RoomRepository instance = new RoomRepository();

    private RoomRepository(){

    }
    public static RoomRepository getInstance() {
        return instance;
    }

    Room createNewRoom(int number, BedType[] bedTypes) {


        Room newRoom = new Room(findNewId(), number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    Room addExistingRoom(int id, int number, BedType[] bedTypes) {


        Room newRoom = new Room(id, number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    List<Room> getAll() {
        return this.rooms;
    }

    public void saveAll() {
        String name = "rooms.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        StringBuilder sb = new StringBuilder("");

        for (Room room : this.rooms) {
            sb.append(room.toCSV());
        }

        try {
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "save", "room data");
        }
    }

    public void readAll() {
        String name = "rooms.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        if(!Files.exists(file)) {
            return;
        }

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] roomsAsString = data.split(System.getProperty("line.separator"));

            for (String guestAsString : roomsAsString) {
                String[] roomData = guestAsString.split(",");
                if (roomData[0] == null || roomData[0].trim().isEmpty()){
                    continue;
                }
                int id = Integer.parseInt(roomData[0]);
                int number = Integer.parseInt(roomData[1]);
                String bedTypesData = roomData[2];
                String[] bedsTypesAsString = bedTypesData.split("#");
                BedType[] bedTypes = new BedType[bedsTypesAsString.length];
                for (int i = 0; i < bedTypes.length; i++) {

                    if(bedsTypesAsString[i].equals(Properties.SINGLE_BED)) {
                        bedTypes[i] = BedType.SINGLE;
                    } else if(bedsTypesAsString[i].equals(Properties.DOUBLE_BED)) {
                        bedTypes[i] = BedType.DOUBLE;
                    } else if(bedsTypesAsString[i].equals(Properties.KING_SIZE)) {
                        bedTypes[i] = BedType.KING_SIZE;
                    }
                }
                addExistingRoom(id, number, bedTypes);
            }

        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "read", "room data");
        }

    }

    private int findNewId() {
        int max = 0;
        for (Room room : this.rooms) {
            if (room.getId() > max) {
                max = room.getId();
            }
        }
        return max + 1;
    }

    public void remove(int id) {
        int guestToBeRemovedIndex = -1;
        for (int i = 0; i < rooms.size(); i++) {
            if (this.rooms.get(i).getId() == id) {
                guestToBeRemovedIndex = i;
                break;
            }
        }
        if (guestToBeRemovedIndex > -1) {
            this.rooms.remove(guestToBeRemovedIndex);
        }
    }

    public void edit(int id, int number, BedType[] bedTypes) {
        this.remove(id);
        this.addExistingRoom(id, number, bedTypes);
    }

    public Room getById(long id) {
        for (Room room : this.rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }
}