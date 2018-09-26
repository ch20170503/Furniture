package com.hc.bean.UserInfoList;

public class UserUpdataList {

    private String Id;
    private String UserName;
    private boolean IsLocked;
    private boolean LockoutEnabled;
    private String OrganizeName;
    private String NickName;
    private String RealName;
    private String Email;
    private String PhoneNumber;
    private String CreatedTime;
    private int LoginCount;
    private String Organize_Id;
    private String PermissionList;
    private int Level;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public boolean isLocked() {
        return IsLocked;
    }

    public void setLocked(boolean locked) {
        IsLocked = locked;
    }

    public boolean isLockoutEnabled() {
        return LockoutEnabled;
    }

    public void setLockoutEnabled(boolean lockoutEnabled) {
        LockoutEnabled = lockoutEnabled;
    }

    public String getOrganizeName() {
        return OrganizeName;
    }

    public void setOrganizeName(String organizeName) {
        OrganizeName = organizeName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getCreatedTime() {
        return CreatedTime;
    }

    public void setCreatedTime(String createdTime) {
        CreatedTime = createdTime;
    }

    public int getLoginCount() {
        return LoginCount;
    }

    public void setLoginCount(int loginCount) {
        LoginCount = loginCount;
    }

    public String getOrganize_Id() {
        return Organize_Id;
    }

    public void setOrganize_Id(String organize_Id) {
        Organize_Id = organize_Id;
    }

    public String getPermissionList() {
        return PermissionList;
    }

    public void setPermissionList(String permissionList) {
        PermissionList = permissionList;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }
}
