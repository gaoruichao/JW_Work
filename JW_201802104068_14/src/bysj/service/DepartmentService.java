package bysj.service;


import bysj.dao.DepartmentDao;
import bysj.domain.Department;
import bysj.domain.School;

import java.util.Collection;
import java.util.HashSet;

public final class DepartmentService {
    private static DepartmentDao departmentDao= DepartmentDao.getInstance();
    private static DepartmentService departmentService=new DepartmentService();
    private DepartmentService(){}

    public static DepartmentService getInstance(){
        return departmentService;
    }

    public Collection<Department> getAll(){
        return departmentDao.findAll();
    }

    public Collection<Department> getAll(School school){
        Collection<Department> departments = new HashSet<Department>();
        for(Department department: departmentDao.findAll()){
            if(department.getSchool()==school){
                departments.add(department);
            }
        }
        return departments;
    }

    public Department find(Integer id){
        return departmentDao.find(id);
    }

    public boolean update(Department department){
        return departmentDao.update(department);
    }

    public boolean add(Department department){
        return departmentDao.add(department);
    }

    public boolean delete(Integer id){
        Department department = this.find(id);
        return departmentDao.delete(department);
    }

    public boolean delete(Department department){
        return departmentDao.delete(department);
    }
}

