//201802104068高睿超
package dao;

import domain.Degree;


import java.util.Collection;
import java.util.TreeSet;

public final class DegreeDao {
    private static DegreeDao degreeDao = new DegreeDao();
    private static Collection<Degree> degrees;
    static {
        degrees = new TreeSet<Degree>();
        Degree degree = new Degree(1,"博士","01","");
        degrees.add(degree);
        degrees.add(new Degree(2, "硕士", "02", ""));
        degrees.add(new Degree(3, "学士", "03", ""));
    }
    private DegreeDao(){}
    public static DegreeDao getInstance(){return degreeDao;}

    public Collection<Degree> findAll(){
        return DegreeDao.degrees;
    }
}

