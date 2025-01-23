package dev.lumen.data;

import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.lumen.App;
import dev.lumen.models.Student;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;

public class StudentDAO {
    public static final String TABLE = "tblstudents";
    public static final DBService DB = App.DB_THESIS;

    public static Student data(CachedRowSet crs) {
        try {
            int studentID = crs.getInt("ID");
            String firstname = crs.getString("FirstName");
            String surname = crs.getString("Surname");
            String middleInitial = crs.getString("MI");

            return new Student(studentID, firstname, surname, middleInitial);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static DBParam[] paramList(Student student) {
        List<DBParam> paramList = new LinkedList<>();

        paramList.add(new DBParam(DBType.NUMERIC, "ID", student.getStudentID()));
        paramList.add(new DBParam(DBType.TEXT, "FirstName", student.getFirstname()));
        paramList.add(new DBParam(DBType.TEXT, "Surname", student.getSurname()));
        paramList.add(new DBParam(DBType.TEXT, "MI", student.getMiddleInitial()));

        return paramList.toArray(new DBParam[0]);
    }

    public static List<Student> getStudentList() {
        CachedRowSet crs = DB.select(TABLE);
        List<Student> list = new LinkedList<>();
        try {
            while (crs.next()) {
                Student student = data(crs);
                if (student != null) {
                    list.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void insert(Student student) {
        
        DB.insert(TABLE, paramList(student));
    }

    public static void delete(Student student) {
        DB.delete(TABLE, new DBParam(DBType.NUMERIC, "ID", student.getStudentID()));
    }

    public static void update(Student student) {

        DBParam[] params = paramList(student);

        for (int i = 0; i <= 3; i++) {
            DB.update(TABLE, new DBParam(DBType.NUMERIC, "ID",
                    student.getStudentID()), params[i]);
        }

    }
}
