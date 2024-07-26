package com.andersen.tickettoride.routes;

import com.andersen.tickettoride.cities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route r WHERE " +
            "r.origin = :origin AND r.destination = :destination OR " +
            "r.origin = :destination AND r.destination = :origin")
    Route findByCities(
            @Param("origin") City origin,
            @Param("destination") City destination
    );

    @Query("SELECT r FROM Route r WHERE " +
            "r.origin = :city OR " +
            "r.destination = :city")
    List<Route> findAllByCity(@Param("city") City city);

}
