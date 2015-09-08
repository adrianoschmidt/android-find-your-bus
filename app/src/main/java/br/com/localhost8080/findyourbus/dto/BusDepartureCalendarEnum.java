package br.com.localhost8080.findyourbus.dto;

public enum BusDepartureCalendarEnum {
    WEEKDAY("Weekday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    private String description;

    BusDepartureCalendarEnum(String description) {
        this.description = description;
    }

    public static String[] stringValues() {
        String[] array = new String[3];

        array[0] = WEEKDAY.description;
        array[1] = SATURDAY.description;
        array[2] = SUNDAY.description;

        return array;
    }
}
