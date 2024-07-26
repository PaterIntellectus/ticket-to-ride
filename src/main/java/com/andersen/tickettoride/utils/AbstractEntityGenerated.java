package com.andersen.tickettoride.utils;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntityGenerated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    public AbstractEntityGenerated() {}
    public AbstractEntityGenerated(long id) {
        setId(id);
    }


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
