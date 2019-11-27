package bysj.dao;

import bysj.domain.Degree;
import bysj.domain.Department;
import bysj.domain.ProfTitle;
import bysj.domain.Teacher;
import bysj.service.DepartmentService;

import java.util.Collection;
import java.util.TreeSet;

public final class TeacherDao {
	private static TeacherDao teacherDao=new TeacherDao();
	private TeacherDao(){}
	public static TeacherDao getInstance(){
		return teacherDao;
	}
	private static Collection<Teacher> teachers;
	static{
		ProfTitle assProf = ProfTitleDao.getInstance().find(2);
		ProfTitle lecture = ProfTitleDao.getInstance().find(3);
		Degree phd = DegreeDao.getInstance().find(1);
		Degree master = DegreeDao.getInstance().find(2);
		Department misDept = DepartmentService.getInstance().find(2);
		teachers = new TreeSet<Teacher>();
		Teacher teacher = new Teacher(1,"苏同",assProf,phd,misDept);
		teachers.add(teacher);
		teachers.add(new Teacher(2,"刘霞",lecture,phd,misDept));
		teachers.add(new Teacher(3,"王方",assProf,phd,misDept));
		teachers.add(new Teacher(4,"刘峰",assProf,master,misDept));
	}
	public Collection<Teacher> findAll(){
		return TeacherDao.teachers;
	}
	public Teacher find(Integer id){
		Teacher desiredTeacher = null;
		for (Teacher teacher : teachers) {
			if(id.equals(teacher.getId())){
				desiredTeacher =  teacher; 
				break;
			}
		}
		return desiredTeacher;
	}
	public boolean update(Teacher teacher){
		teachers.remove(teacher);
		return teachers.add(teacher);		
	}
	public boolean add(Teacher teacher){
		return teachers.add(teacher);		
	}
	public boolean delete(Integer id){
		Teacher teacher = this.find(id);
		return this.delete(teacher);
	}
	public boolean delete(Teacher teacher){
		return teachers.remove(teacher);
	}
}
