package com.AttendBackEnd.factories.event;

import com.AttendBackEnd.domain.event.EventBasicInformation;

import java.util.Date;

/**
 * Created by Leo on 4/18/2016.
 */
public class EventBasicInformationFactory {
    public static EventBasicInformation getEventBasicInformation (String eventtype ,Date startp,Date enddate)
    {
        return new EventBasicInformation.Builder()
                .end(enddate)
                .start(startp)
                .eventtye(eventtype)
                .build();
    }
}