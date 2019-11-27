package test;

import bysj.dao.DepartmentDao;
import bysj.domain.Department;
import bysj.domain.School;
import bysj.service.SchoolService;

import java.sql.SQLException;

public class DepartmentDaoTest {
    private static DepartmentDao departmentDao= DepartmentDao.getInstance();
    public static void main(String[] args) throws SQLException,ClassNotFoundException {
        //获取school对象，以便为departmentToAdd的school属性赋值
        School school = SchoolService.getInstance().find(2);
        //创建departmentToAdd对象
        Department departmentToAdd = new Department("信息管理","0202","",school);
        //创建Dao对象
        //DepartmentDao departmentDao = new DepartmentDao();
        //执行Dao对象的方法
        //Department addedDepartment = departmentDao.addWithStoreProcedure(departmentToAdd);
        //打印添加后返回的对象
        //System.out.println(addedDepartment);
        System.out.println("添加Department成功");
    }
}
