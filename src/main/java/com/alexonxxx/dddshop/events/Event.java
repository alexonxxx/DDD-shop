package com.alexonxxx.dddshop.events;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance
@DiscriminatorColumn(name="EVENT_TYPE")
public class Event implements Serializable {

    @Id
    @GeneratedValue
    private Long sequenceNumber;

    private String aggregate;

    public Event(String aggregate) {
        this.aggregate = aggregate;
    }

    Event() {}
}
