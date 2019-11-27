//201802104068高睿超
package service;

import dao.SchoolDao;
import domain.School;

import java.util.Collection;

public final class SchoolService {
    private static SchoolDao schoolDao = SchoolDao.getInstance();
    private static SchoolService schoolService = new SchoolService();

    public static SchoolService getInstance(){return schoolService;}

    public Collection<School> findAll(){
        return schoolDao.findAll();
    }
}
