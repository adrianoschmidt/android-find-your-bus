package br.com.localhost8080.findyourbus.dto;

public class BusDepartureDTO implements Comparable<BusDepartureDTO> {

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
        return this.getCalendar() + " - " + this.getTime();
    }

    /**
     * TODO: implement to compare by calendar and after by time.
     */
    @Override
    public int compareTo(BusDepartureDTO another) {
        return 0;
    }
}
