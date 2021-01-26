package org.condo.api.cadastro.entidade.dto;

import java.io.Serializable;

public class StatusResponse implements Serializable {

    private Long code;
    private String message;

    public StatusResponse() {
    }

    public StatusResponse(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
