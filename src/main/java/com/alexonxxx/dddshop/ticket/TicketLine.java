package com.alexonxxx.dddshop.ticket;

import com.alexonxxx.dddshop.shared.Money;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TicketLine {

    @Id
    @GeneratedValue
    private Long id;

    private Money price;

    public TicketLine(Money price) {
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }

    TicketLine(){}
}
