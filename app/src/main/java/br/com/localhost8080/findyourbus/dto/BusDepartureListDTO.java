package br.com.localhost8080.findyourbus.dto;

import java.util.List;

public class BusDepartureListDTO {

    private List<BusDepartureDTO> rows;
    private String rowsAffected;

    public List<BusDepartureDTO> getRows() {
        return rows;
    }

    public void setRows(List<BusDepartureDTO> rows) {
        this.rows = rows;
    }

    public String getRowsAffected() {
        return rowsAffected;
    }

    public void setRowsAffected(String rowsAffected) {
        this.rowsAffected = rowsAffected;
    }
}
