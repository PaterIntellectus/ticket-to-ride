package com.andersen.tickettoride.tickets;

import com.andersen.tickettoride.cities.CityService;
import com.andersen.tickettoride.routes.RouteService;
import com.andersen.tickettoride.tickets.dto.BuyTicketFailureDTO;
import com.andersen.tickettoride.tickets.dto.BuyTicketSuccessDTO;
import com.andersen.tickettoride.tickets.dto.FindTicketDTO;
import com.andersen.tickettoride.utils.CurrencyEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final CityService cityService;
    private final RouteService routeService;


    public TicketController(
            TicketService ticketService,
            CityService cityService,
            RouteService routeService
    ) {
        this.ticketService = ticketService;
        this.cityService = cityService;
        this.routeService = routeService;
    }


    @GetMapping("/price")
    public ResponseEntity<?> findTicket(
            @RequestParam String departure,
            @RequestParam String arrival
    ) {
        if (departure.equals(arrival)) {
            return ResponseEntity
                    .badRequest()
                    .body("Departure and the Arrival points cannot be the same!");
        }

        try {
            final var origin = cityService.findByName(departure);
            final var destination = cityService.findByName(arrival);
            final var route = routeService.find(origin, destination);

            return ResponseEntity.ok(
                    new FindTicketDTO(
                            route.getSegments().size(),
                            ticketService.calculatePrice(route.getSegments().size()),
                            CurrencyEnum.GBP
                    )
            );
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/buy")
    public ResponseEntity<?> buyTicket(
            @RequestParam String departure,
            @RequestParam String arrival,
            @RequestParam CurrencyEnum currency,
            @RequestParam(name = "traveler_amount") double travelerAmount,
            @RequestParam(name = "traveler_name") String travelerName
    ) {
        if (departure.equals(arrival)) {
            return ResponseEntity
                    .badRequest()
                    .body("Departure and the Arrival points cannot be the same!");
        }

        try {
            final var origin = cityService.findByName(departure);
            final var destination = cityService.findByName(arrival);
            final var route = routeService.find(origin, destination);

            final var ticket = new Ticket(
                    route,
                    travelerName,
                    ticketService.calculatePrice(route.getSegments().size())
            );

            final var change = travelerAmount - ticket.getPrice();

            if (change > 0) {
                ticketService.saveTicket(ticket);

                return ResponseEntity.ok(
                        new BuyTicketSuccessDTO(
                                change,
                                currency
                        )
                );
            } else {
                return ResponseEntity.ok(
                        new BuyTicketFailureDTO(
                                -change,
                                currency
                        )
                );
            }
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }
}
