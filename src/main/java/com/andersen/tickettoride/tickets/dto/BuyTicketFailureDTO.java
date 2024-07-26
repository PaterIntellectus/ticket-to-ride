package com.andersen.tickettoride.tickets.dto;

import com.andersen.tickettoride.utils.CurrencyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyTicketFailureDTO extends AbstractBuyTicketDto {
    @JsonProperty("lack_of")
    private double lackOf;


    public BuyTicketFailureDTO(double lackOf, CurrencyEnum currency) {
        super("failure", currency);
        setLackOf(lackOf);
    }


    public double getLackOf() {
        return lackOf;
    }

    public void setLackOf(double lackOf) {
        this.lackOf = lackOf;
    }
}
