package my.execute;

public class TimeoutException extends ExecutorException {
    private static final long serialVersionUID = -5423490721470482068L;

    public TimeoutException() {
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(Throwable cause) {
        super(cause);
    }
}
