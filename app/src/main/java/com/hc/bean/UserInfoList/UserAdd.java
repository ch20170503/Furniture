package com.hc.bean.UserInfoList;

public class UserAdd {

        private String UserName;
        private String Password;
        private boolean IsLocked;
        private boolean LockoutEnabled;
        private String PermissionList;
        private int Level;
        private String Organize_Id;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isLocked() {
        return IsLocked;
    }

    public void setIsLocked(boolean IsLocked) {
        this.IsLocked = IsLocked;
    }

    public boolean isLockoutEnabled() {
        return LockoutEnabled;
    }

    public void setLockoutEnabled(boolean LockoutEnabled) {
        this.LockoutEnabled = LockoutEnabled;
    }

    public String getPermissionList() {
        return PermissionList;
    }

    public void setPermissionList(String PermissionList) {
        this.PermissionList = PermissionList;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int Level) {
        this. Level = Level;
    }

    public String getOrganize_Id() {
        return Organize_Id;
    }

    public void setOrganize_Id(String Organize_Id) {
        this.Organize_Id = Organize_Id;
    }
}
