package com.second.theatretics;

public class SeatType {
    int seat_id;
    int seat_number;
    int seat_occupied;
    int seat_fare;
    int stt_id;

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public int getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(int seat_number) {
        this.seat_number = seat_number;
    }

    public int getSeat_occupied() {
        return seat_occupied;
    }

    public void setSeat_occupied(int seat_occupied) {
        this.seat_occupied = seat_occupied;
    }

    public int getSeat_fare() {
        return seat_fare;
    }

    public void setSeat_fare(int seat_fare) {
        this.seat_fare = seat_fare;
    }

    public int getStt_id() {
        return stt_id;
    }

    public void setStt_id(int stt_id) {
        this.stt_id = stt_id;
    }
    public SeatType()
    {

    }
    public SeatType(int seat_id,int seat_number,int seat_occupied,int seat_fare,int stt_id)
    {

        this.seat_id=seat_id;
        this.seat_number=seat_number;
        this.seat_occupied=seat_occupied;
        this.seat_fare=seat_fare;
        this.stt_id=stt_id;

    }

    public SeatType(int seat_number,int seat_occupied,int seat_fare,int stt_id)
    {
        this.seat_number=seat_number;
        this.seat_occupied=seat_occupied;
        this.seat_fare=seat_fare;
        this.stt_id=stt_id;
    }
}
