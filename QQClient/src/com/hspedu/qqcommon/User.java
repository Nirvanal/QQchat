package com.hspedu.qqcommon;

import java.io.Serializable;

public class User implements Serializable {//必须要序列化

    private  static final long serialVersionUID=1L;

    private String userId;//用户id
    private String passWd;//用户密码

    public User(String userId, String passWd) {
        this.userId = userId;
        this.passWd = passWd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }


}
