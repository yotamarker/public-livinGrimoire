package com.yotamarker.lgkotlin1;
public class Permission {
    /*
     * uses two names as lv1,2 permission levels
     * requires password to change password or said names
     */
    private static Permission singleton;
    private String password, lv1Name, lv2Name;
    private int permissionLevel;

    private Permission(String password, String lv1Name, String lv2Name) {
        super();
        this.password = password;
        this.lv1Name = lv1Name;
        this.lv2Name = lv2Name;
    }

    public static Permission newInstance(String password, String lv1Name, String lv2Name) {
        if (singleton == null) {
            singleton = new Permission(password, lv1Name, lv2Name);
        }
        return singleton;
    }

    public int getPermissionLevel() {
        int result = this.permissionLevel;
        this.permissionLevel = 0;
        return result;
    }

    public void setPermissionLevel(String input) {
        if (input.contains(this.lv2Name)) {
            this.permissionLevel = 2;
        } else if (input.contains(this.lv1Name)) {
            this.permissionLevel = 1;
        }
        if (input.contains(this.lv2Name + " reset")) {
            this.permissionLevel = 0;
        }
    }

    public Boolean setPassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public Boolean setLv2Name(String password, String newName2) {
        if (this.password.equals(password)) {
            this.lv2Name = newName2;
            return true;
        }
        return false;
    }

    public String getLv1Name() {
        return lv1Name;
    }

    public String getLv2Command(String command) {
        // returns cmd without lv2 name
        String result = command;
        result = result.replace(this.lv2Name, "");
        return result.trim();
    }
    public Boolean setLv1Name(String password, String newName) {
        if (this.password.equals(password)) {
            this.lv1Name = newName;
            return true;
        }
        return false;
    }

    public static String clsmsg() {
        return "chaos ritual\r\n" + "\r\n" + "create two names\r\n" + "one soul will be called to the light\r\n"
                + "and one will be led by the darkness\r\n"
                + "and those souls of light and darkness will combine to create\r\n" + "the light of chaos\r\n"
                + "...\r\n" + "A.G.I descended!";
    }
}
