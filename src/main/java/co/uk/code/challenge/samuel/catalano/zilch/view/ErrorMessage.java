package co.uk.code.challenge.samuel.catalano.zilch.view;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

/**
 * @author Samuel Catalano
 * @since 2 March, 2020
 */

@JsonPropertyOrder({"status", "code", "message"})
public class ErrorMessage implements Serializable {

    private String status;
    private String message;
    private Integer code;

    public ErrorMessage status(final String status) {
        this.status = status;
        return this;
    }

    public ErrorMessage message(final String message) {
        this.message = message;
        return this;
    }

    public ErrorMessage code(final Integer code) {
        this.code = code;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "status='" + this.status + '\'' +
                ", message='" + this.message + '\'' +
                ", code=" + this.code +
                '}';
    }
}