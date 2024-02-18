package ru.veucos.cms.exception;

/**
 * Особая ситуация - пользователь не авторизован
 */

public class NoAuthenticatedException extends RuntimeException {

    public NoAuthenticatedException() {
    }

    public NoAuthenticatedException(String message) {
        super(message);
    }

    public NoAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
