package com.andersen.tickettoride.tickets.dto;

import com.andersen.tickettoride.utils.CurrencyEnum;

public abstract class AbstractBuyTicketDto {
    private String result;
    private CurrencyEnum currency;


    public AbstractBuyTicketDto(String result, CurrencyEnum currency) {
        setResult(result);
        setCurrency(currency);
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }
}
