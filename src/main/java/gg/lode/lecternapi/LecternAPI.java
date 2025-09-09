package gg.lode.lecternapi;

public class LecternAPI {
    private static ILecternAPI api;

    public static void setApi(ILecternAPI api) {
        LecternAPI.api = api;
    }

    public static ILecternAPI getApi() {
        return api;
    }
}
