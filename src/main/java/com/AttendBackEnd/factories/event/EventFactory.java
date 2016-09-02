package com.AttendBackEnd.factories.event;

import com.AttendBackEnd.domain.event.Event;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventFactory {
    public static Event getEvent (String tagline ,String host,String name )
    {
        return new Event.Builder()
                .host(host)
                .name(name)
                .tagline(tagline)
                .build();
    }
}
