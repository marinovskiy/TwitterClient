package ua.marinovskiy.twitterclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    public static final String KEY_ACCESS_TOKEN = "key_access_token";
    public static final String KEY_USER_ID = "key_user_id";
    public static final String KEY_USER_NAME = "key_user_name";
    public static final String KEY_IS_EXPIRED = "key_is_expired";

    private static SharedPreferences prefs = null;

    private Prefs() {
    }

    public static void init(Context applicationContext) {
        if (prefs == null) {
            prefs = applicationContext.getSharedPreferences(applicationContext.getPackageName(),
                    Context.MODE_PRIVATE);
        }
    }

    public static void setAccessToken(String accessToken) {
        setString(KEY_ACCESS_TOKEN, accessToken);
    }

    public static void setUserId(long userId) {
        setLong(KEY_USER_ID, userId);
    }

    public static void setUserName(String userName) {
        setString(KEY_USER_NAME, userName);
    }

    public static void setExpired(boolean isExpired) {
        setBoolean(KEY_IS_EXPIRED, isExpired);
    }

    public static String getAccessToken() {
        return getString(KEY_ACCESS_TOKEN);
    }

    public static long getUserId() {
        return getLong(KEY_USER_ID);
    }

    public static String getUserName() {
        return getString(KEY_USER_NAME);
    }

    public static boolean isExpired() {
        return getBoolean(KEY_IS_EXPIRED);
    }

    private static long getLong(String key) {
        return prefs.getLong(key, 0);
    }

    private static boolean getBoolean(String key) {
        return prefs.getBoolean(key, false);
    }

    private static String getString(String key) {
        return prefs.getString(key, null);
    }

    private static void setLong(String key, long value) {
        prefs.edit()
                .putLong(key, value)
                .apply();
    }

    private static void setBoolean(String key, boolean value) {
        prefs.edit()
                .putBoolean(key, value)
                .apply();
    }

    private static void setString(String key, String value) {
        prefs.edit()
                .putString(key, value)
                .apply();
    }

}
