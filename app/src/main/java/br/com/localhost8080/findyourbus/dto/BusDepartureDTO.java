package br.com.localhost8080.findyourbus.dto;

import java.io.Serializable;

public class BusDepartureDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BusDepartureCalendarEnum calendar;
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusDepartureCalendarEnum getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = BusDepartureCalendarEnum.valueOf(calendar);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return this.getTime();
    }

}
