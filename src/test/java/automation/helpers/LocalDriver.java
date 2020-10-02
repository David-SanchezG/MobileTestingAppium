package automation.helpers;


public class LocalDriver {

    private static ThreadLocal<String> version = new ThreadLocal<>();
    private static ThreadLocal<String> platform = new ThreadLocal<>();

    public static String getVersion() {
        return version.get();
    }

    public static void setVersion(String myVersion) {
        version.set(myVersion);
    }

    public static String getPlatform() {
        return platform.get();
    }

    public static void setPlatform(String myPlatform) {
        platform.set(myPlatform);
    }
}
