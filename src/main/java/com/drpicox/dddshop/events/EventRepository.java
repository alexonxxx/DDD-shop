package com.drpicox.dddshop.events;

import com.drpicox.dddshop.cashregister.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findByAggregateOrderBySequenceNumberAsc(String aggregate);
}
