package com.second.theatretics;

public class SeattrackType {
    int seattrack_id;
    int seat_day;
    int seat_id;
    int o_id;
    int confirmation;

    public int getSeattrack_id() {
        return seattrack_id;
    }

    public void setSeattrack_id(int seattrack_id) {
        this.seattrack_id = seattrack_id;
    }

    public int getSeat_day() {
        return seat_day;
    }

    public void setSeat_day(int seat_day) {
        this.seat_day = seat_day;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }

    public SeattrackType(int seattrack_id, int seat_day, int seat_id, int o_id, int confirmation) {
        this.seattrack_id = seattrack_id;
        this.seat_day = seat_day;
        this.seat_id = seat_id;
        this.o_id = o_id;
        this.confirmation = confirmation;
    }
    public SeattrackType( int seat_day, int seat_id, int o_id, int confirmation) {
        this.seat_day = seat_day;
        this.seat_id = seat_id;
        this.o_id = o_id;
        this.confirmation = confirmation;
    }
    public SeattrackType() {

    }
}
