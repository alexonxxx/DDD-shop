package com.alexonxxx.dddshop.events;

import com.alexonxxx.dddshop.cashregister.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    public List<Event> findByAggregateOrderBySequenceNumberAsc(String aggregate);
}
