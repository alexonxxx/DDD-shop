package com.drpicox.dddshop;

import com.drpicox.dddshop.cashregister.*;
import com.drpicox.dddshop.events.Event;
import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;
import com.drpicox.queue.Queue;
import com.drpicox.queue.QueueReceiver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EventProcessor {

    private Queue fakeQueue;

    protected EventProcessor() {
        fakeQueue = new Queue();
    }

    protected <T> void register(Class<T> messageClass, QueueReceiver<T> receiver) {
        fakeQueue.receive(messageClass, receiver);
    }

    public void apply(List<Event> events) {
        events.forEach(event -> this.apply(event));
    }

    public void apply(Event event) {
        fakeQueue.send(event);
        fakeQueue.deliverMessages();
    }

}
