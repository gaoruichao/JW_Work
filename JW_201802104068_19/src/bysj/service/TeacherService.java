package bysj.service;


import bysj.dao.TeacherDao;
import bysj.domain.Teacher;

import java.sql.SQLException;
import java.util.Collection;

public final class TeacherService {
	private static TeacherDao teacherDao= TeacherDao.getInstance();
	private static TeacherService teacherService=new TeacherService();
	private TeacherService(){}
	
	public static TeacherService getInstance(){
		return teacherService;
	}
	
	public Collection<Teacher> findAll(){
		return teacherDao.findAll();
	}
	
	public Teacher find(Integer id){
		return teacherDao.find(id);
	}
	
	public boolean update(Teacher teacher) throws SQLException {
		return teacherDao.update(teacher);
	}
	
	public boolean add(Teacher teacher) throws SQLException{
		return teacherDao.add(teacher);
	}

	public boolean delete(Integer id){
		Teacher teacher = this.find(id);
		return teacherDao.delete(teacher);
	}
	
	public boolean delete(Teacher teacher){
		//如果该教师有关联的课题，不能删除
		if(teacher.getProjects().size()>0){
			return false;
		}
		return teacherDao.delete(teacher);
	}	
}