package com.andersen.tickettoride.tickets.dto;

import com.andersen.tickettoride.utils.CurrencyEnum;

public class BuyTicketSuccessDTO extends AbstractBuyTicketDto {
    private double change;


    public BuyTicketSuccessDTO(double change, CurrencyEnum currency) {
        super("success", currency);
        setChange(change);
    }


    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
}
