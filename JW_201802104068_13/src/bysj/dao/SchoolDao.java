package bysj.dao;


import bysj.domain.School;

import java.util.Collection;
import java.util.TreeSet;

public final class SchoolDao {
	private static SchoolDao schoolDao = new SchoolDao();
	private static Collection<School> schools;
	static{
		schools = new TreeSet<School>();
		School school = new School(1,"土木工程","01","");
		schools.add(school);
		schools.add(new School(2,"管理工程","02","最好的学院"));
		schools.add(new School(3,"市政工程","03",""));
		schools.add(new School(4,"艺术","04",""));
	}
	
	private SchoolDao(){}
	
	public static SchoolDao getInstance(){
		return schoolDao;
	}

	public Collection<School> findAll(){
		return SchoolDao.schools;
	}
	
	public School find(Integer id){
		School desiredSchool = null;
		for (School school : schools) {
			if(id.equals(school.getId())){
				desiredSchool =  school; 
			}
		}
		return desiredSchool;
	}
	
	public boolean update(School school){
		schools.remove(school);
		return schools.add(school);		
	}
	
	public boolean add(School school){
		return schools.add(school);		
	}

	public boolean delete(Integer id){
		School school =this.find(id);
		return this.delete(school);
	}
	
	public boolean delete(School school){
		return SchoolDao.schools.remove(school);
	}
}
