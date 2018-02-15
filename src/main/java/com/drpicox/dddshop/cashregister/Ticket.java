package com.drpicox.dddshop.cashregister;

import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {

    @Id @GeneratedValue
    private Long id;

    private Money cashDelivered;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TicketLine> lines;

    public void recordItem(ItemId itemId, Money price) {
        lines.add(new TicketLine(price));
    }

    public Money getTotal() {
        Money total = new Money(0);
        for (TicketLine line: lines) {
            total = total.plus(line.getPrice());
        }
        return total;
    }

    public void recordCashDelivered(Money cashDelivered) {
        this.cashDelivered = cashDelivered;
    }

    public Money getChange() {
        return cashDelivered.minus(getTotal());
    }

}
