package bysj.service;


import bysj.dao.SchoolDao;
import bysj.domain.Department;
import bysj.domain.School;

import java.util.Collection;

public final class SchoolService {
	private static SchoolDao schoolDao= SchoolDao.getInstance();
	private static SchoolService schoolService=new SchoolService();
	
	
	public static SchoolService getInstance(){
		return schoolService;
	}

	public Collection<School> findAll(){
		return schoolDao.findAll();
	}
	
	public School find(Integer id){
		return schoolDao.find(id);
	}

	public boolean update(School school){
		return schoolDao.update(school);
	}
	
	public boolean add(School school){
		return schoolDao.add(school);
	}

	public String delete(Integer id){
		School school = this.find(id);
		return this.delete(school);
	}
	
//	public boolean delete(School school){
//		//获得所有下一级单位（Department）
//		Collection<Department> departmentSet =
//				DepartmentService.getInstance().getAll(school);
//		//若没有二级单位，则能够删除
//		if(departmentSet.size()==0){
//			return schoolDao.delete(school);
//		}else {
//			return false;
//		}
//	}

	public String delete(School school){
		//获得所有下一级单位（Department）
		Collection<Department> departmentSet =
				DepartmentService.getInstance().getAll(school);
		//若没有二级单位，则能够删除
		if(departmentSet.size()==0){
			schoolDao.delete(school);
			return "DELETED";
		}else {
			return "{\"result\":\"having departments\"}";
		}
	}
}
