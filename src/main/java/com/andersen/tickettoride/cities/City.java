package com.andersen.tickettoride.cities;

import com.andersen.tickettoride.utils.AbstractEntityGenerated;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cities")
public class City extends AbstractEntityGenerated {
    @Column(unique = true)
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(getId(), city.getId()) &&
               Objects.equals(getName(), city.getName());
    }

    @Override
    public String toString() {
        return String.format(
                "City(id: %d, name: %s)",
                getId(), getName()
        );
    }
}
