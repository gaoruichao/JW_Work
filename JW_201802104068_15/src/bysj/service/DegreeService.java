package bysj.service;


import bysj.dao.DegreeDao;
import bysj.domain.Degree;

import java.util.Collection;

public final class DegreeService {
    private static DegreeDao degreeDao
            = DegreeDao.getInstance();
    private static DegreeService degreeService
            =new DegreeService();
    private DegreeService(){}

    public static DegreeService getInstance(){
        return degreeService;
    }

    public Collection<Degree> findAll(){
        return degreeDao.findAll();
    }

    public Degree find(Integer id){
        return degreeDao.find(id);
    }

    public boolean update(Degree degree){
        return degreeDao.update(degree);
    }

    public boolean add(Degree degree){
        return degreeDao.add(degree);
    }

    public boolean delete(Integer id){
        Degree degree = this.find(id);
        return degreeDao.delete(degree);
    }

    public boolean delete(Degree degree){
        return degreeDao.delete(degree);
    }
}

