package bysj.dao;


import bysj.domain.ProfTitle;

import java.util.Collection;
import java.util.TreeSet;

public final class ProfTitleDao {
	private static ProfTitleDao profTitleDao=new ProfTitleDao();
	private ProfTitleDao(){}
	public static ProfTitleDao getInstance(){
		return profTitleDao;
	}
	private static Collection<ProfTitle> profTitles;
	static{
		profTitles = new TreeSet<ProfTitle>();
		ProfTitle ProfTitle = new ProfTitle(1,"教授","01","");
		profTitles.add(ProfTitle);
		profTitles.add(new ProfTitle(2,"副教授","02",""));
		profTitles.add(new ProfTitle(3,"讲师","03",""));
		profTitles.add(new ProfTitle(4,"助教","04",""));
	}
	public Collection<ProfTitle> findAll(){
		return ProfTitleDao.profTitles;
	}

	public ProfTitle find(Integer id){
		ProfTitle desiredProfTitle = null;
		for (ProfTitle profTitle : profTitles) {
			if(id.equals(profTitle.getId())){
				desiredProfTitle =  profTitle;
				break;
			}
		}
		return desiredProfTitle;
	}

	public boolean update(ProfTitle profTitle){
		profTitles.remove(profTitle);
		return profTitles.add(profTitle);
	}

	public boolean add(ProfTitle profTitle){
		return profTitles.add(profTitle);
	}

	public boolean delete(Integer id){
		ProfTitle profTitle = this.find(id);
		return this.delete(profTitle);
	}

	public boolean delete(ProfTitle profTitle){
		return profTitles.remove(profTitle);
	}
}

