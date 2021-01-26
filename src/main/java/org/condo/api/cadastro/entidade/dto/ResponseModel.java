package org.condo.api.cadastro.entidade.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseModel<T> {

    private List<T> data;
    private StatusResponse status;

    public ResponseModel() {
        this.data = new ArrayList<>();
    }

    public ResponseModel(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }
}
