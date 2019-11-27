package bysj.dao;


import bysj.domain.Department;
import bysj.domain.School;

import java.util.Collection;
import java.util.TreeSet;

public final class DepartmentDao {
	private static Collection<Department> departments;
	static {
		School managementSchool = null;

		managementSchool = SchoolDao.getInstance().find(2);

		departments = new TreeSet<Department>();
		Department department = new Department(1, "工程管理", "0201", "",
				managementSchool);
		departments.add(department);
		departments
				.add(new Department(2, "信息管理", "0202", "", managementSchool));
		departments
				.add(new Department(3, "工程造价", "0203", "", managementSchool));
		departments
				.add(new Department(4, "工业工程", "0204", "", managementSchool));
	}

	private static DepartmentDao departmentDao=new DepartmentDao();
	private DepartmentDao(){}

	public static DepartmentDao getInstance(){
		return departmentDao;
	}


	public Collection<Department> findAll(){
		return DepartmentDao.departments;
	}

	public Department find(Integer id){
		Department desiredDepartment = null;
		for (Department department : departments) {
			if(id.equals(department.getId())){
				desiredDepartment =  department;
				break;
			}
		}
		return desiredDepartment;
	}

	public boolean update(Department department){
		departments.remove(department);
		return departments.add(department);
	}

	public boolean add(Department department){
		return departments.add(department);
	}

	public boolean delete(Integer id){
		Department department = this.find(id);
		return this.delete(department);
	}

	public boolean delete(Department department){
		return departments.remove(department);
	}
}

