package dev.lumen.data;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.lumen.App;
import dev.lumen.models.Researcher;
import dev.lumen.models.Student;
import dev.lumen.models.Thesis;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;
import javafx.collections.ObservableList;

public class ResearcherDAO {
    public static final String TABLE = "tblthesisresearchers";
    public static final DBService DB = App.DB_THESIS;

    private static final ObservableList<Thesis> thesisList = App.COLLECTIONS_REGISTRY.getList("THESIS");
    private static final ObservableList<Student> studentList = App.COLLECTIONS_REGISTRY.getList("STUDENT");

    public static Researcher data(CachedRowSet crs) {
        try {

            Student studentID = studentList.stream()
                    .filter(student -> {
                        try {
                            return student.getStudentID() == (crs.getInt("RID"));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).findFirst().get();

            Thesis thesisID = thesisList.stream()
                    .filter(student -> {
                        try {
                            return student.getThesisID() == (crs.getInt("TID"));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).findFirst().get();

            String role = crs.getString("Role");

            return new Researcher(studentID, thesisID, role);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static DBParam[] paramList(Researcher researcher) {
        List<DBParam> paramList = new LinkedList<>();

        paramList.add(new DBParam(DBType.NUMERIC, "RID", researcher.getStudentID().getStudentID()));

        paramList.add(new DBParam(DBType.NUMERIC, "TID", researcher.getThesisID().getThesisID()));

        paramList.add(new DBParam(DBType.TEXT, "Role", researcher.getRole()));

        return paramList.toArray(new DBParam[0]);
    }

    public static List<Researcher> getResearcherList() {
        CachedRowSet crs = DB.select(TABLE);
        List<Researcher> list = new LinkedList<>();
        try {
            while (crs.next()) {
                Researcher researcher = data(crs);
                if (researcher != null) {
                    list.add(researcher);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insert(Researcher researcher) {
        
        DB.insert(TABLE, paramList(researcher));
    }

    public static void delete(Researcher researcher) {
        DB.delete(TABLE, new DBParam(DBType.NUMERIC, "RID", researcher.getStudentID().getStudentID()));
    }

    public static void update(Researcher researcher) {

        DBParam[] params = paramList(researcher);

        for (int i = 0; i <= 2; i++) {
            DB.update(TABLE, new DBParam(DBType.NUMERIC, "RID",
                    researcher.getStudentID().getStudentID()), params[i]);
        }

    }
}
