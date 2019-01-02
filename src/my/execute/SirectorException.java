package my.execute;

public class SirectorException extends RuntimeException {
    private static final long serialVersionUID = 4898686611676070892L;

    public SirectorException() {
    }

    public SirectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SirectorException(String message) {
        super(message);
    }

    public SirectorException(Throwable cause) {
        super(cause);
    }
}