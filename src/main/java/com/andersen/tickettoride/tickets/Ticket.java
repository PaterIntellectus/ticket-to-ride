package com.andersen.tickettoride.tickets;


import com.andersen.tickettoride.routes.Route;
import com.andersen.tickettoride.utils.AbstractEntityGenerated;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket extends AbstractEntityGenerated {
    @ManyToOne
    private Route route;
    private String travelerName;
    private double price;


    public Ticket() {}
    public Ticket(Route route, String travelerName, double price) {
        setRoute(route);
        setTravelerName(travelerName);
        setPrice(price);
    }


    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getTravelerName() {
        return travelerName;
    }

    public void setTravelerName(String traveler) {
        this.travelerName = traveler;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return String.format(
                "Ticket(id: %d, traveler: %s, route_id: %d, price: %f)",
                getId(), getTravelerName(), getRoute().getId(), getPrice()
        );
    }
}
