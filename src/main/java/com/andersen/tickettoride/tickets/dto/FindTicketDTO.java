package com.andersen.tickettoride.tickets.dto;

import com.andersen.tickettoride.utils.CurrencyEnum;

public class FindTicketDTO {
    private int segments;
    private double price;
    private CurrencyEnum currency;


    public FindTicketDTO(int segments, double price, CurrencyEnum currency) {
        setSegments(segments);
        setPrice(price);
        setCurrency(currency);
    }


    public int getSegments() {
        return segments;
    }

    public void setSegments(int segments) {
        this.segments = segments;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }
}
