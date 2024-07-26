package com.andersen.tickettoride.tickets;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;


    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public double calculatePrice(int segmentsCount) {
        if (segmentsCount < 0) {
            throw new IllegalArgumentException("Segment count cannot be negative");
        }

        if (segmentsCount == 0) {
            return 0;
        }

        double price = 0;

        int groupsOfThree = segmentsCount / 3;
        price += groupsOfThree * 10;
        segmentsCount = segmentsCount % 3;

        int groupsOfTwo = segmentsCount / 2;
        price += groupsOfTwo * 7;
        segmentsCount = segmentsCount % 2;

        price += segmentsCount * 5;

        return price;
    }
}
