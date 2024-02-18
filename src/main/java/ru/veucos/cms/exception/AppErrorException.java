package ru.veucos.cms.exception;

/**
 * Особая ситуация - ошибка в приложении
 */
public class AppErrorException extends RuntimeException {

    public AppErrorException() {
    }

    public AppErrorException(String message) {
        super(message);
    }

    public AppErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
