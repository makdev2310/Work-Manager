package SignIn_SignUp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * DONE
 */
public class SaveSharedPreference {
    static final String PREF_USER_NAME= "username";
    static final String PREF_EMAIL= "email";
    static final String PREF_ROLE= "role";
    static final String PREF_AVATAR= "avatar";
    static final String PREF_TOKEN= "token";
    static final String PREF_ID= "_id";
    static final String PREF_BOSS = "boss";

    static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUser(Context ctx, String userName, String email, String avatar, String _id, String role, String token, boolean isBoss) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.putString(PREF_EMAIL, email);
        editor.putString(PREF_ROLE, role);
        editor.putString(PREF_AVATAR, avatar);
        editor.putString(PREF_TOKEN, token);
        editor.putString(PREF_ID, _id);
        editor.putBoolean(PREF_BOSS, isBoss);
        editor.commit();
    }

    public static void setPrefToken(Context ctx, String token){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_TOKEN, token);
    }

    public static String getRole(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_ROLE, "");
    }

    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }
    public static String getEmailAddress(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_EMAIL, "");
    }
    public static String getAvatar(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_AVATAR, "");
    }
    public static String getId(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_ID, "");
    }
    public static void clearUser(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
    public static String getPrefToken(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_TOKEN, "");
    }
    public static boolean getPrefIsBoss(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_BOSS, false);
    }
}
