package br.com.localhost8080.findyourbus.dto;

import java.util.List;

public class BusListDTO {

    private List<BusDTO> rows;
    private String rowsAffected;

    public List<BusDTO> getRows() {
        return rows;
    }

    public void setRows(List<BusDTO> rows) {
        this.rows = rows;
    }

    public String getRowsAffected() {
        return rowsAffected;
    }

    public void setRowsAffected(String rowsAffected) {
        this.rowsAffected = rowsAffected;
    }
}
