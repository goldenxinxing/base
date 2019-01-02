package my.execute;

public interface Callback<Event> {
    void onSuccess(Event var1);

    void onError(Event var1, Throwable var2);
}
