package dev.lumen.data;

import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.lumen.App;
import dev.lumen.models.Thesis;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;

public class ThesisDAO {

    public static final String TABLE = "tblthesis";
    public static final DBService DB = App.DB_THESIS;

    public static Thesis data(CachedRowSet crs) {
        try {
            int thesisID = crs.getInt("ID");
            String thesisTitle = crs.getString("Title");
            int year = crs.getInt("Year");
            int month = crs.getInt("Month");
            int degID = crs.getInt("DegID");

            return new Thesis(thesisID, thesisTitle, year, month, degID);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static DBParam[] paramList(Thesis thesis) {
        List<DBParam> paramList = new LinkedList<>();

        paramList.add(new DBParam(DBType.NUMERIC, "ID", thesis.getThesisID()));
        paramList.add(new DBParam(DBType.TEXT, "Title", thesis.getThesisTitle()));
        paramList.add(new DBParam(DBType.NUMERIC, "Month", thesis.getMonth()));
        paramList.add(new DBParam(DBType.NUMERIC, "Year", thesis.getYear()));
        paramList.add(new DBParam(DBType.NUMERIC, "DegID", thesis.getDegID()));

        return paramList.toArray(new DBParam[0]);
    }

    public static List<Thesis> getThesisList() {
        CachedRowSet crs = DB.select(TABLE);
        List<Thesis> list = new LinkedList<>();
        try {
            while (crs.next()) {
                Thesis thesis = data(crs);
                if (thesis != null) {
                    list.add(thesis);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return list;
    }

    public static void insert(Thesis thesis) {
        DB.insert(TABLE, paramList(thesis));
    }

    public static void delete(Thesis thesis) {
        DB.delete(TABLE, new DBParam(DBType.NUMERIC, "ID", thesis.getThesisID()));
    }

    public static void update(Thesis thesis) {

        DBParam[] params = paramList(thesis);

        for (int i = 0; i <= 4; i++) {
            DB.update(TABLE, new DBParam(DBType.NUMERIC, "ID",
                    thesis.getThesisID()), params[i]);
        }

    }
}
