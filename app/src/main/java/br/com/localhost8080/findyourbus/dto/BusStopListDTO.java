package br.com.localhost8080.findyourbus.dto;

import java.io.Serializable;
import java.util.List;

public class BusStopListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<BusStopDTO> rows;
    private String rowsAffected;

    public List<BusStopDTO> getRows() {
        return rows;
    }

    public void setRows(List<BusStopDTO> rows) {
        this.rows = rows;
    }

    public String getRowsAffected() {
        return rowsAffected;
    }

    public void setRowsAffected(String rowsAffected) {
        this.rowsAffected = rowsAffected;
    }
}
