package com.AttendBackEnd.domain.event;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Leo on 8/13/2016.
 */
@Entity
public class EventBasicInformation implements Serializable{
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    private Long id;
    private Date start;
    private Date end;
    private String eventtype;

    public EventBasicInformation()
    {

    }
    public EventBasicInformation (Builder builder)
    {
        this.end = builder.end;
        this.eventtype=builder.eventtype;
        this.start=builder.start;
        this.id= builder.id;
    }


    public Long getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getEventtype() {
        return eventtype;
    }

    public static class Builder
    {
        private Date start;
        private Date end;
        private String eventtype;
        private Long id;
        public Builder start (Date value)
        {
            this.start = value;
            return this;
        }
        public Builder end (Date value)
        {
            this.end = value;
            return this;
        }
        public Builder id(Long value) {
            this.id = value;
            return this;
        }
        public Builder eventtye(String value)
        {
            this.eventtype = value;
            return this;
        }

        public Builder copy(EventBasicInformation  eventBasicInformation)
        {
            this.eventtype = eventBasicInformation.eventtype;
            this.start = eventBasicInformation.start;
            this.end = eventBasicInformation.end;
            this.id=eventBasicInformation.id;
            return this;
        }

        public EventBasicInformation build()
        {
            return new EventBasicInformation(this);
        }
    }

}
