package dev.lumen.data;

import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.lumen.App;
import dev.lumen.models.Account;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;

public class AccountDAO {
    public static final String TABLE = "accounts";
    public static final DBService DB = App.DB_THESIS;

    public static Account data(CachedRowSet crs) {
        try {
            String username = crs.getString("username");
            String password = crs.getString("password");

            return new Account(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static DBParam[] paramList(Account account) {
        List<DBParam> paramList = new LinkedList<>();

        paramList.add(new DBParam(DBType.TEXT, "username", account.getUsername()));
        paramList.add(new DBParam(DBType.TEXT, "password", account.getPassword()));

        return paramList.toArray(new DBParam[0]);
    }

    public static List<Account> getAccountList() {
        CachedRowSet crs = DB.select(TABLE);
        List<Account> list = new LinkedList<>();
        try {
            while (crs.next()) {
                Account account = data(crs);
                if (account != null) {
                    list.add(account);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insert(Account account) {
        DB.insert(TABLE, paramList(account));
    }

    public static void delete(Account account) {
        DB.delete(TABLE, new DBParam(DBType.TEXT, "username", account.getUsername()));
    }

    public static void update(Account account) {

        DBParam[] params = paramList(account);

        for (int i = 0; i <= 1; i++) {
            DB.update(TABLE, new DBParam(DBType.TEXT, "username",
                    account.getUsername()), params[i]);
        }

    }
}
