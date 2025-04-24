import java.util.prefs.Preferences;

public class SettingsManager {
    private static final Preferences prefs = Preferences.userNodeForPackage(SettingsManager.class);

    // Account settings keys
    private static final String KEY_FULLNAME = "fullName";
    private static final String KEY_DOB = "dateOfBirth";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_ACCOUNT_CREATED = "accountCreated";
    private static final String KEY_CURRENCY = "currency";
    private static final String KEY_TWO_FACTOR = "twoFactorAuth";

    // App preferences keys
    private static final String KEY_DARK_MODE = "darkMode";
    private static final String KEY_FONT_SIZE = "fontSize";
    private static final String KEY_THEME = "theme";
    private static final String KEY_LANGUAGE = "language";
    private static final String KEY_DATE_FORMAT = "dateFormat";
    private static final String KEY_TIME_FORMAT = "timeFormat";
    private static final String KEY_STARTUP = "startOnBoot";
    private static final String KEY_START_MINIMIZED = "startMinimized";
    private static final String KEY_AUTO_BACKUP = "autoBackup";
    private static final String KEY_DEFAULT_VIEW = "defaultView";
    private static final String KEY_CHART_TYPE = "chartType";
    private static final String KEY_ANIMATIONS = "animationsLevel";

    // Notification settings keys
    private static final String KEY_NOTIFY_BILLS = "notifyBills";
    private static final String KEY_NOTIFY_SUBSCRIPTIONS = "notifySubscriptions";
    private static final String KEY_NOTIFY_WEEKLY = "notifyWeekly";
    private static final String KEY_NOTIFY_OVERSPEND = "notifyOverspend";
    private static final String KEY_NOTIFY_MOTIVATION = "notifyMotivation";
    private static final String KEY_NOTIFY_FREQUENCY = "notifyFrequency";
    private static final String KEY_NOTIFY_TIME = "notifyTime";
    private static final String KEY_NOTIFY_EMAIL = "notifyEmail";
    private static final String KEY_NOTIFY_DESKTOP = "notifyDesktop";
    private static final String KEY_NOTIFY_PUSH = "notifyPush";
    private static final String KEY_QUIET_HOURS = "quietHours";
    private static final String KEY_QUIET_FROM = "quietFrom";
    private static final String KEY_QUIET_TO = "quietTo";

    // Account settings methods
    public static void saveAccountSettings(String fullName, String dob, String username,
                                           String email, String phone, String address,
                                           String currency, boolean twoFactor) {
        prefs.put(KEY_FULLNAME, fullName);
        prefs.put(KEY_DOB, dob);
        prefs.put(KEY_USERNAME, username);
        prefs.put(KEY_EMAIL, email);
        prefs.put(KEY_PHONE, phone);
        prefs.put(KEY_ADDRESS, address);
        prefs.put(KEY_CURRENCY, currency);
        prefs.putBoolean(KEY_TWO_FACTOR, twoFactor);
    }

    public static String getFullName() {
        return prefs.get(KEY_FULLNAME, "");
    }

    public static String getDateOfBirth() {
        return prefs.get(KEY_DOB, "");
    }

    public static String getUsername() {
        return prefs.get(KEY_USERNAME, "");
    }

    public static String getEmail() {
        return prefs.get(KEY_EMAIL, "");
    }

    public static String getPhone() {
        return prefs.get(KEY_PHONE, "");
    }

    public static String getAddress() {
        return prefs.get(KEY_ADDRESS, "");
    }

    public static String getAccountCreated() {
        return prefs.get(KEY_ACCOUNT_CREATED, "");
    }

    public static String getCurrency() {
        return prefs.get(KEY_CURRENCY, "USD ($)");
    }

    public static boolean getTwoFactorAuth() {
        return prefs.getBoolean(KEY_TWO_FACTOR, false);
    }

    // App preferences methods
    public static void saveAppPreferences(boolean darkMode, String fontSize, String language,
                                          String dateFormat, String timeFormat, boolean startOnBoot,
                                          boolean startMinimized, boolean autoBackup,
                                          String defaultView, String chartType, int animations) {
        prefs.putBoolean(KEY_DARK_MODE, darkMode);
        prefs.put(KEY_FONT_SIZE, fontSize);
        prefs.put(KEY_LANGUAGE, language);
        prefs.put(KEY_DATE_FORMAT, dateFormat);
        prefs.put(KEY_TIME_FORMAT, timeFormat);
        prefs.putBoolean(KEY_STARTUP, startOnBoot);
        prefs.putBoolean(KEY_START_MINIMIZED, startMinimized);
        prefs.putBoolean(KEY_AUTO_BACKUP, autoBackup);
        prefs.put(KEY_DEFAULT_VIEW, defaultView);
        prefs.put(KEY_CHART_TYPE, chartType);
        prefs.putInt(KEY_ANIMATIONS, animations);
    }

    public static boolean getDarkMode() {
        return prefs.getBoolean(KEY_DARK_MODE, false);
    }

    public static String getFontSize() {
        return prefs.get(KEY_FONT_SIZE, "Medium");
    }

    public static String getLanguage() {
        return prefs.get(KEY_LANGUAGE, "English");
    }

    public static String getDateFormat() {
        return prefs.get(KEY_DATE_FORMAT, "MM/DD/YYYY");
    }

    public static String getTimeFormat() {
        return prefs.get(KEY_TIME_FORMAT, "12-hour (AM/PM)");
    }

    public static boolean getStartOnBoot() {
        return prefs.getBoolean(KEY_STARTUP, false);
    }

    public static boolean getStartMinimized() {
        return prefs.getBoolean(KEY_START_MINIMIZED, false);
    }

    public static boolean getAutoBackup() {
        return prefs.getBoolean(KEY_AUTO_BACKUP, true);
    }

    public static String getDefaultView() {
        return prefs.get(KEY_DEFAULT_VIEW, "Monthly");
    }

    public static String getChartType() {
        return prefs.get(KEY_CHART_TYPE, "Pie Chart");
    }

    public static int getAnimationsLevel() {
        return prefs.getInt(KEY_ANIMATIONS, 50);
    }

    // Notification settings methods
    public static void saveNotificationSettings(boolean notifyBills, boolean notifySubscriptions,
                                                boolean notifyWeekly, boolean notifyOverspend,
                                                boolean notifyMotivation, String frequency,
                                                String notifyTime, boolean notifyEmail,
                                                boolean notifyDesktop, boolean notifyPush,
                                                boolean quietHours, String quietFrom, String quietTo) {
        prefs.putBoolean(KEY_NOTIFY_BILLS, notifyBills);
        prefs.putBoolean(KEY_NOTIFY_SUBSCRIPTIONS, notifySubscriptions);
        prefs.putBoolean(KEY_NOTIFY_WEEKLY, notifyWeekly);
        prefs.putBoolean(KEY_NOTIFY_OVERSPEND, notifyOverspend);
        prefs.putBoolean(KEY_NOTIFY_MOTIVATION, notifyMotivation);
        prefs.put(KEY_NOTIFY_FREQUENCY, frequency);
        prefs.put(KEY_NOTIFY_TIME, notifyTime);
        prefs.putBoolean(KEY_NOTIFY_EMAIL, notifyEmail);
        prefs.putBoolean(KEY_NOTIFY_DESKTOP, notifyDesktop);
        prefs.putBoolean(KEY_NOTIFY_PUSH, notifyPush);
        prefs.putBoolean(KEY_QUIET_HOURS, quietHours);
        prefs.put(KEY_QUIET_FROM, quietFrom);
        prefs.put(KEY_QUIET_TO, quietTo);
    }

    public static boolean getNotifyBills() {
        return prefs.getBoolean(KEY_NOTIFY_BILLS, true);
    }

    public static boolean getNotifySubscriptions() {
        return prefs.getBoolean(KEY_NOTIFY_SUBSCRIPTIONS, true);
    }

    public static boolean getNotifyWeekly() {
        return prefs.getBoolean(KEY_NOTIFY_WEEKLY, true);
    }

    public static boolean getNotifyOverspend() {
        return prefs.getBoolean(KEY_NOTIFY_OVERSPEND, true);
    }

    public static boolean getNotifyMotivation() {
        return prefs.getBoolean(KEY_NOTIFY_MOTIVATION, true);
    }

    public static String getNotifyFrequency() {
        return prefs.get(KEY_NOTIFY_FREQUENCY, "Weekly");
    }

    public static String getNotifyTime() {
        return prefs.get(KEY_NOTIFY_TIME, "9:00 AM");
    }

    public static boolean getNotifyEmail() {
        return prefs.getBoolean(KEY_NOTIFY_EMAIL, true);
    }

    public static boolean getNotifyDesktop() {
        return prefs.getBoolean(KEY_NOTIFY_DESKTOP, true);
    }

    public static boolean getNotifyPush() {
        return prefs.getBoolean(KEY_NOTIFY_PUSH, false);
    }

    public static boolean getQuietHours() {
        return prefs.getBoolean(KEY_QUIET_HOURS, false);
    }

    public static String getQuietFrom() {
        return prefs.get(KEY_QUIET_FROM, "22:00");
    }

    public static String getQuietTo() {
        return prefs.get(KEY_QUIET_TO, "07:00");
    }

    // Reset all settings to default
    public static void resetAllSettings() {
        try {
            prefs.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Reset specific settings group
    public static void resetAccountSettings() {
        try {
            prefs.remove(KEY_FULLNAME);
            prefs.remove(KEY_DOB);
            prefs.remove(KEY_USERNAME);
            prefs.remove(KEY_EMAIL);
            prefs.remove(KEY_PHONE);
            prefs.remove(KEY_ADDRESS);
            prefs.remove(KEY_CURRENCY);
            prefs.remove(KEY_TWO_FACTOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetAppPreferences() {
        try {
            prefs.remove(KEY_DARK_MODE);
            prefs.remove(KEY_FONT_SIZE);
            prefs.remove(KEY_LANGUAGE);
            prefs.remove(KEY_DATE_FORMAT);
            prefs.remove(KEY_TIME_FORMAT);
            prefs.remove(KEY_STARTUP);
            prefs.remove(KEY_START_MINIMIZED);
            prefs.remove(KEY_AUTO_BACKUP);
            prefs.remove(KEY_DEFAULT_VIEW);
            prefs.remove(KEY_CHART_TYPE);
            prefs.remove(KEY_ANIMATIONS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void resetNotificationSettings() {
        try {
            prefs.remove(KEY_NOTIFY_BILLS);
            prefs.remove(KEY_NOTIFY_SUBSCRIPTIONS);
            prefs.remove(KEY_NOTIFY_WEEKLY);
            prefs.remove(KEY_NOTIFY_OVERSPEND);
            prefs.remove(KEY_NOTIFY_MOTIVATION);
            prefs.remove(KEY_NOTIFY_FREQUENCY);
            prefs.remove(KEY_NOTIFY_TIME);
            prefs.remove(KEY_NOTIFY_EMAIL);
            prefs.remove(KEY_NOTIFY_DESKTOP);
            prefs.remove(KEY_NOTIFY_PUSH);
            prefs.remove(KEY_QUIET_HOURS);
            prefs.remove(KEY_QUIET_FROM);
            prefs.remove(KEY_QUIET_TO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}