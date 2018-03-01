package com.drpicox.dddshop;

import com.drpicox.dddshop.cashregister.*;
import com.drpicox.dddshop.events.Event;
import com.drpicox.dddshop.item.ItemId;
import com.drpicox.dddshop.shared.Money;
import com.drpicox.queue.Queue;
import com.drpicox.queue.QueueReceiver;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EventProcessor {

    private Queue fakeQueue;

    protected EventProcessor() {
        fakeQueue = new Queue();

        Class myClass = this.getClass();
        Method[] methods = myClass.getDeclaredMethods();
        for (Method method: methods) {
            ProcessesEvent annotation = method.getAnnotation(ProcessesEvent.class);
            Class[] parameters = method.getParameterTypes();
            if (annotation != null) {
                int modifiers = method.getModifiers();
                if ((modifiers & (Modifier.PUBLIC | Modifier.PROTECTED)) == 0) {
                    throw new RuntimeException("@ProcessEvent method '" + method.getName() + "' must be public or protected (" + method + ")");
                }
                if (parameters.length != 1) {
                    throw new RuntimeException("@ProcessEvent method '" + method.getName() + "' does not have exactly one parameter (" + method + ")");
                }
                fakeQueue.receive(parameters[0], (event) -> {
                    try {
                        method.invoke(this, event);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
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
