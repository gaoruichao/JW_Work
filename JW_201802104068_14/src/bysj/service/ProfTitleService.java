package bysj.service;


import bysj.dao.ProfTitleDao;
import bysj.domain.ProfTitle;

import java.util.Collection;

public final class ProfTitleService {
	private static ProfTitleDao profTitleDao= ProfTitleDao.getInstance();
	private static ProfTitleService profTitleService=new ProfTitleService();
	private ProfTitleService(){}

	public static ProfTitleService getInstance(){
		return profTitleService;
	}

	public Collection<ProfTitle> getAll(){
		return profTitleDao.findAll();
	}

	public ProfTitle find(Integer id){
		return profTitleDao.find(id);
	}

	public boolean update(ProfTitle profTitle){
		return profTitleDao.update(profTitle);
	}

	public boolean add(ProfTitle profTitle){
		return profTitleDao.add(profTitle);
	}

	public boolean delete(Integer id){
		ProfTitle profTitle = this.find(id);
		return profTitleDao.delete(profTitle);
	}

	public boolean delete(ProfTitle profTitle){
		return profTitleDao.delete(profTitle);
	}
}

