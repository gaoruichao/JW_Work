//201802104068高睿超
package service;

import dao.DegreeDao;
import domain.Degree;

import java.util.Collection;

public final class DegreeService {
    private static DegreeDao degreeDao = DegreeDao.getInstance();
    private static DegreeService degreeService = new DegreeService();

    public static DegreeService getInstance(){return degreeService;}

    public Collection<Degree> findAll(){
        return degreeDao.findAll();
    }
}
