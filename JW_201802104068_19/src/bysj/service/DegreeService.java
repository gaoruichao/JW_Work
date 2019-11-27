package bysj.service;


import bysj.dao.DegreeDao;
import bysj.domain.Degree;

import java.sql.SQLException;
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

    public boolean update(Degree degree) throws SQLException,ClassNotFoundException{
        return degreeDao.update(degree);
    }

    public boolean add(Degree degree) throws SQLException {
        return degreeDao.add(degree);
    }

    public boolean delete(Integer id) throws SQLException{
        Degree degree = this.find(id);
        System.out.println(degree.getNo());
        degreeDao.delete(id);//根据id从集合中删除
        return degreeDao.delete(degree);//从数据库中删除
    }

    public boolean delete(Degree degree){
        return degreeDao.delete(degree);
    }
}

