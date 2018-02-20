package com.drpicox.dddshop.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketLineRepository extends JpaRepository<TicketLine, TicketId> {
}
