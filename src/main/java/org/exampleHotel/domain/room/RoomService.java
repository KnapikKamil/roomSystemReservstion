package org.exampleHotel.domain.room;

import org.exampleHotel.domain.room.dto.RoomDTO;
import org.exampleHotel.exceptions.WrongOptionException;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    private final static RoomRepository repository = new RoomRepository();
    public Room createNewRoom(int number, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for(int i=0;i<bedTypesOptions.length;i=i+1) {
            BedType bedType;
            switch (bedTypesOptions[i]) {
                case 1:
                    bedType = BedType.SINGLE;
                    break;
                case 2:
                    bedType = BedType.DOUBLE;
                    break;
                case 3:
                    bedType = BedType.KING_SIZE;
                    break;
                default:
                    throw new WrongOptionException("Wrong option when selecting bed type");
            }
            bedTypes[i] = bedType;
        }
        return repository.createNewRoom(number, bedTypes);
    }
     public List<Room> getAllRooms(){
        return  repository.getAll();
    }
    public void saveAll() {
        this.repository.saveAll();
    }
    public void readAll(){
        this.repository.readAll();
    }

    public void remove(int id) {
        this.repository.remove(id);
    }

    public Room getRoomById(long roomId) {
        return this.repository.getById(roomId);
    }

    public void edit(int id, int number, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for(int i=0;i<bedTypesOptions.length;i=i+1) {
            BedType bedType;
            switch (bedTypesOptions[i]) {
                case 1:
                    bedType = BedType.SINGLE;
                    break;
                case 2:
                    bedType = BedType.DOUBLE;
                    break;
                case 3:
                    bedType = BedType.KING_SIZE;
                    break;
                default:
                    throw new WrongOptionException("Wrong option when selecting bed type");
            }
            bedTypes[i] = bedType;
        }
        this.repository.edit(id,number,bedTypes);
    }
public  List<RoomDTO> getAllAsDTO(){
        List<RoomDTO> result = new ArrayList<>();
        List<Room> allRooms = repository.getAll();
        for (Room room : allRooms){
            RoomDTO roomDTO = room.generateDTO();
        }
        return result;
}


}

