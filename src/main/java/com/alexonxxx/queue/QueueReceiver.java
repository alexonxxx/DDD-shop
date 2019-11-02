package com.alexonxxx.queue;

public interface QueueReceiver<T> {

    void receive(T s);
}
