//201802104068高睿超
package dao;

import domain.School;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public final class SchoolDao {
    private static SchoolDao schoolDao = new SchoolDao();
    private static Collection<School> schools;
    static {
        schools = new HashSet<School>();
        School school = new School(1,"土木工程","01","");
        schools.add(school);
        schools.add(new School(2, "管理工程", "02", "最好的学院"));
        schools.add(new School(3, "市政工程", "03", ""));
        schools.add(new School(4, "艺术", "04", ""));
    }
    private SchoolDao(){}
    public static SchoolDao getInstance(){return schoolDao;}

    public Collection<School> findAll(){
        return SchoolDao.schools;
    }


}
