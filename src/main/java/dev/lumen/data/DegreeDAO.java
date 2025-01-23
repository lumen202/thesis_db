package dev.lumen.data;

import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.lumen.App;
import dev.lumen.models.Degree;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;

public class DegreeDAO {
    public static final String TABLE = "degree";
    public static final DBService DB = App.DB_THESIS;

    public static Degree data(CachedRowSet crs) {
        try {
            int degreeID = crs.getInt("degreeID");
            String degree = crs.getString("Degree");

            return new Degree(degreeID, degree);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static DBParam[] paramList(Degree degree) {
        List<DBParam> paramList = new LinkedList<>();

        paramList.add(new DBParam(DBType.NUMERIC, "degreeID", degree.getDegreeID()));
        paramList.add(new DBParam(DBType.TEXT, "Degree", degree.getDegree()));

        return paramList.toArray(new DBParam[0]);
    }

    public static List<Degree> getDegreeList() {
        CachedRowSet crs = DB.select(TABLE);
        List<Degree> list = new LinkedList<>();
        try {
            while (crs.next()) {
                Degree degree = data(crs);
                if (degree != null) {
                    list.add(degree);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insert(Degree degree) {
        DB.insert(TABLE, paramList(degree));
    }

    public static void delete(Degree degree) {
        DB.delete(TABLE, new DBParam(DBType.NUMERIC, "degreeID", degree.getDegreeID()));
    }

    public static void update(Degree degree) {

        DBParam[] params = paramList(degree);

        for (int i = 0; i <= 17; i++) {
            DB.update(TABLE, new DBParam(DBType.NUMERIC, "degreeID",
                    degree.getDegreeID()), params[i]);
        }

    }
}
