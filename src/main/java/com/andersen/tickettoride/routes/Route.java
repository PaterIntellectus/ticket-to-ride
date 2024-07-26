package com.andersen.tickettoride.routes;

import com.andersen.tickettoride.cities.City;
import com.andersen.tickettoride.segments.Segment;
import com.andersen.tickettoride.utils.AbstractEntityGenerated;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name = "routes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"origin_city_id", "destination_city_id"})
)
public class Route extends AbstractEntityGenerated {
    @ManyToOne
    @JoinColumn(name = "origin_city_id", nullable = false)
    private City origin;

    @ManyToOne
    @JoinColumn(name = "destination_city_id", nullable = false)
    private City destination;

    @ManyToMany
    @JoinTable(
            name = "segments_to_routes",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "segment_id"))
    private List<Segment> segments;


    public Route() {}
    public Route(City origin, City destination, List<Segment> segments) {
        setOrigin(origin);
        setDestination(destination);
        setSegments(segments);
    }


    public City getOrigin() {
        return origin;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }


    @Override
    public String toString() {
        return String.format(
                "Route(id: %d, origin: %s, destination: %s, segments: %s)",
                getId(), getOrigin(), getDestination(), getSegments()
        );
    }
}
