package gofive.models;

import gofive.models.db.DBase;
import gofive.models.db.User;
import gofive.util.MD5Util;

/**
 * Created by coral on 16-5-11.
 */
public class UserModel {

    public static final int SUCCESS = 1, FAILED = 0, NOTEXIST = -1;

    public static int login(String account, String passwd) {
        DBase[] result = User.query().where("account = '" + account + "'");
        if (result.length == 0) return NOTEXIST;
        User user = (User) result[0];
        if (user.login(passwd)) return SUCCESS;
        return FAILED;
    }

    public static boolean register(String account, String passwd) {
        DBase[] result = User.query().where("account = '" + account + "'");
        if (result.length > 0) return false;
        new User(account, passwd).save();
        return true;
    }
}
