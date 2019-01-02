package my.execute;

public class ExecutorException extends RuntimeException {
    private static final long serialVersionUID = 4898686611676070892L;

    public ExecutorException() {
    }

    public ExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutorException(String message) {
        super(message);
    }

    public ExecutorException(Throwable cause) {
        super(cause);
    }
}