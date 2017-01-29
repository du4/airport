
package by.it.pvt.du4.exceptions;

public class ValidationException extends Exception {

    private Exception exception;

    public ValidationException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
