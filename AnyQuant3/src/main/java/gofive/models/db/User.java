package gofive.models.db;

import gofive.util.MD5Util;

/**
 * Created by coral on 16-5-9.
 */
public class User extends DBase {
    public String account;
    public String passwd_md5;

    public User() {}

    public User(String account, String passwd) {
        this.account = account;
        this.passwd_md5 = MD5Util.MD5(passwd);
    }

    public static User query() {
        return new User();
    }

    public void setPasswd_md5(String passwd) {
        this.passwd_md5 = MD5Util.MD5(passwd);
    }

    public boolean login(String passwd) {
        return MD5Util.MD5(passwd).equals(passwd_md5);
    }
}
