package br.com.localhost8080.findyourbus.dto;

public class BusStopDTO {

    private Long id;
    private String name;
    private Long sequence;
    private Long route_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }

    @Override
    public String toString() {
        return this.getSequence() + " - " + this.getName();
    }
}
