package com.example.godbin.join;

public class SearchData {
    String hotel_name;
    String hotel_address;

    public SearchData(String hotel_name, String hotel_address){
        this.hotel_name=hotel_name;
        this.hotel_address=hotel_address;
    }

    public String getHotel_address() {
        return hotel_address;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }
}
