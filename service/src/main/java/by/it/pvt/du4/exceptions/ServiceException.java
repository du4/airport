
package by.it.pvt.du4.exceptions;

public class ServiceException extends Exception {

    private Exception exception;

    public ServiceException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
