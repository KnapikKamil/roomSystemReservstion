package org.exampleHotel.domain.reservation;

import org.exampleHotel.domain.guest.Guest;
import org.exampleHotel.domain.reservation.dto.ReservationDTO;
import org.exampleHotel.domain.room.Room;

import java.time.LocalDateTime;

public class Reservation {

    private final long id;
    private final Room room;
    private final Guest guest;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Reservation(long id, Room room, Guest guest, LocalDateTime from, LocalDateTime to) {
        this.id = id;
        this.room = room;
        this.guest = guest;
        this.from = from;
        this.to = to;
    }

    String toCSV() {
        return String.format("%s,%s,%s,%s,%s%s", this.id, getRoomId(), getGuestId(), this.from.toString(), this.to.toString(), System.getProperty("line.separator"));
    }

    public String getInfo() {
        return String.format("%s,%s,%s,%s,%s", this.id, getRoomId(), getGuestId(), this.from.toString(), this.to.toString());
    }

    long getId() {
        return this.id;
    }

    public ReservationDTO getAsDTO() {
        return new ReservationDTO(this.id, this.from, this.to,getRoomId(), getRoomNumber(), getGuestId(), getGuestName());
    }

     long getRoomId() {
        if (this.room != null) {
            return  this.room.getId();
        } else {
            return 0;
        }
    }

     int getRoomNumber() {
        if (this.room != null) {
            return this.room.getNumber();
        } else {
            return 0;
        }
    }

     long getGuestId() {
        if (this.guest != null) {
            return this.guest.getId();
        } else {
            return 0;
        }
    }

     String getGuestName() {
        if (this.guest != null) {
            return this.guest.getFirstName() + " " + this.guest.getLastName();
        } else {
            return "";
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

}
