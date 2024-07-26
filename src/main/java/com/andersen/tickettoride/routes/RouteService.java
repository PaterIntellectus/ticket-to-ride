package com.andersen.tickettoride.routes;

import com.andersen.tickettoride.cities.City;
import com.andersen.tickettoride.segments.Segment;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class RouteService {
    private final RouteRepository routeRepository;


    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }


    public Route find(City origin, City destination) {
        final var persistedRoute = routeRepository.findByCities(origin, destination);

        return Objects.requireNonNullElseGet(
                persistedRoute,
                () -> createRoute(origin, destination)
        );
    }

    private Route createRoute(City origin, City destination) {
        final var route = new Route(
                origin,
                destination,
                getShortestPath(origin, destination)
        );

        routeRepository.save(route);

        return route;
    }

    private List<Segment> getShortestPath(City origin, City destination) {
        if (origin.equals(destination)) {
            return List.of();
        }

        return createRoutesMap(origin).get(destination);
    }

    private HashMap<City, List<Segment>> createRoutesMap(City origin) {
        final var map = new HashMap<City, List<Segment>>();
        map.put(origin, new ArrayList<>());

        fillRoutesMap(map, origin);

        return map;
    }

    private void fillRoutesMap(
            HashMap<City, List<Segment>> distanceMap,
            City origin
    ) {
        final var allOriginRoutes = routeRepository.findAllByCity(origin);

        for (final var route : allOriginRoutes) {
            final var nextCity =
                    route.getOrigin() == origin
                            ? route.getDestination()
                            : route.getOrigin();

            final var segmentsFromOrigin = Stream.concat(
                    distanceMap.get(origin).stream(),
                    route.getSegments().stream()
            ).toList();

            if (distanceMap.containsKey(nextCity) &&
                distanceMap.get(nextCity).size() <= segmentsFromOrigin.size()
            ) {
                continue;
            }

            distanceMap.put(nextCity, segmentsFromOrigin);

            fillRoutesMap(distanceMap, nextCity);
        }
    }

    // TODO: more optimized version
    //  which is gonna stop searching
    //  after getting the shortest path
    //  from origin to destination

}
