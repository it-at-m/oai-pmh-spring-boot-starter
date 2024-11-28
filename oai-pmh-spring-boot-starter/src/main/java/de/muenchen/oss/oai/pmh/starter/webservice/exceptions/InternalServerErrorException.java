package de.muenchen.oss.oai.pmh.starter.webservice.exceptions;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
