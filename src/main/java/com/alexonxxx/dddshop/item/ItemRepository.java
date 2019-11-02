package com.alexonxxx.dddshop.item;

import com.alexonxxx.dddshop.cashregister.CashRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
