package com.andersen.tickettoride.segments;

import com.andersen.tickettoride.routes.Route;
import com.andersen.tickettoride.utils.AbstractEntityGenerated;
import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "segments")
public class Segment extends AbstractEntityGenerated {
    @ManyToMany(mappedBy = "segments")
    private Set<Route> routes;


    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }


    @Override
    public String toString() {
        return String.format(
                "Segment(id: %d, routes: %s)",
                getId(), getRoutes().stream().map(AbstractEntityGenerated::getId).collect(Collectors.toSet())
        );
    }
}
