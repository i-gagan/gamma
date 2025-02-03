package design.lld.singleton;

public class Singleton2 {
    private Singleton2() {}

    private static class SingletonHelper {
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
