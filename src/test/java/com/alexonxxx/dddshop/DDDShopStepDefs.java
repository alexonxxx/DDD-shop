package com.alexonxxx.dddshop;

import com.alexonxxx.dddshop.cashregister.CashRegisterCtrl;
import com.alexonxxx.dddshop.cashregister.CashRegisterId;
import com.alexonxxx.dddshop.item.ItemCtrl;
import com.alexonxxx.dddshop.item.ItemId;
import com.alexonxxx.dddshop.shared.Money;
import com.alexonxxx.queue.Queue;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class DDDShopStepDefs {

    @Autowired
    private Queue queue;

    @Autowired
    private ItemCtrl itemCtrl;

    @Autowired
    private CashRegisterCtrl cashRegisterCtrl;


    private Map<String, ItemId> items = new HashMap<>();
    private Map<String, CashRegisterId> cashRegisters = new HashMap<>();
    private Map<String, Money> moneys = new HashMap<>();


    @Given("item (\\w+) with price (\\d+) in money")
    public void item_X_with_price_Y(String itemName, int price) {
        ItemId itemId = itemCtrl.createItem(new Money(price));
        queue.deliverMessages();
        items.put(itemName, itemId);
    }

    @Given("cash register (\\w+)$")
    public void cash_register_X(String cashRegisterName) {
        CashRegisterId cashRegisterId = cashRegisterCtrl.createCashRegister();
        queue.deliverMessages();
        cashRegisters.put(cashRegisterName, cashRegisterId);
    }

    @When("record item (\\w+) at cash register (\\w+)")
    public void record_item_X_at_cash_register_Y(String itemName, String cashRegisterName) {
        ItemId itemId = items.get(itemName);
        CashRegisterId cashRegisterId = cashRegisters.get(cashRegisterName);

        cashRegisterCtrl.recordItem(cashRegisterId, itemId);
        queue.deliverMessages();
    }

    @And("end cash register (\\w+) item recording")
    public void end_cash_register_X_item_recording(String cashRegisterName) {
        CashRegisterId cashRegisterId = cashRegisters.get(cashRegisterName);
        cashRegisterCtrl.endItemRecords(cashRegisterId);
        queue.deliverMessages();
    }

    @And("(\\w+) is cash register (\\w+) total")
    public void X_is_cash_register_total(String totalName, String cashRegisterName) {
        CashRegisterId cashRegisterId = cashRegisters.get(cashRegisterName);
        Money total = cashRegisterCtrl.getTotal(cashRegisterId);
        moneys.put(totalName, total);
    }

    @And("record (\\w+) as delivered money at cash register (\\w+)")
    public void X_is_cash_register_change(int money, String cashRegisterName) {
        CashRegisterId cashRegisterId = cashRegisters.get(cashRegisterName);
        Money cashDelivered = new Money(money);
        cashRegisterCtrl.recordCashDelivered(cashRegisterId, cashDelivered);
        queue.deliverMessages();
    }

    @And("(\\w+) is cash register (\\w+) change")
    public void X_is_cash_register_change(String changeName, String cashRegisterName) {
        CashRegisterId cashRegisterId = cashRegisters.get(cashRegisterName);
        Money change = cashRegisterCtrl.getChange(cashRegisterId);
        moneys.put(changeName, change);
    }

    @Then("(\\w+) is (\\d+)")
    public void assert_X_is_Y(String moneyName, int amount) {
        Money money = moneys.get(moneyName);
        assertEquals(new Money(amount), money);
    }

    @Then("cash register (\\w+) is ready to record a new item")
    public void cash_register_X_is_ready_to_record_a_new_item(String cashRegisterName) {
        CashRegisterId cashRegisterId = cashRegisters.get(cashRegisterName);
        boolean isReady = cashRegisterCtrl.isReadyToRecordANewItem(cashRegisterId);
        assertEquals(true, isReady);
    }
}
