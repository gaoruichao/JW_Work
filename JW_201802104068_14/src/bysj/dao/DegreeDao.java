package bysj.dao;

import bysj.domain.Degree;

import java.util.Collection;
import java.util.TreeSet;

public final class DegreeDao {
	private static DegreeDao degreeDao=
			new DegreeDao();
	private DegreeDao(){}
	public static DegreeDao getInstance(){
		return degreeDao;
	}
	private static Collection<Degree> degrees;
	static{
		degrees = new TreeSet<Degree>();
		Degree doctor = new Degree(1,
				"博士","01","");
		degrees.add(doctor);
		degrees.add(new Degree(2,
				"硕士","02",""));
		degrees.add(new Degree(3,
				"学士","03",""));
	}

	public Collection<Degree> findAll(){
		return DegreeDao.degrees;
	}

	public Degree find(Integer id){
		Degree desiredDegree = null;
		for (Degree degree : degrees) {
			if(id.equals(degree.getId())){
				desiredDegree =  degree;
				break;
			}
		}
		return desiredDegree;
	}

	public boolean update(Degree degree){
		degrees.remove(degree);
		return degrees.add(degree);
	}

	public boolean add(Degree degree){
		return degrees.add(degree);
	}

	public boolean delete(Integer id){
		Degree degree = this.find(id);
		return this.delete(degree);
	}

	public boolean delete(Degree degree){
		return degrees.remove(degree);
	}
}

