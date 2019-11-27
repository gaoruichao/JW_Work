package bysj.dao;


import bysj.domain.ProfTitle;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class ProfTitleDao {
	private static ProfTitleDao profTitleDao=new ProfTitleDao();
	private ProfTitleDao(){}
	public static ProfTitleDao getInstance(){
		return profTitleDao;
	}
	public Collection<ProfTitle> findAll(){
		Collection<ProfTitle> profTitles = new TreeSet<ProfTitle>();
		try{
			//获得数据库连接对象
			Connection connection = JdbcHelper.getConn();
			//在该连接上创建语句盒子对象
			Statement stmt = connection.createStatement();
			//执行SQL查询语句并获得结果集对象
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM ProfTitle");
			//若结果存在下一条，执行循环体
			while (resultSet.next()) {
				//打印结果集中记录的id字段
				System.out.print(resultSet.getInt("id"));
				System.out.print(",");
				//打印结果集中记录的no字段
				System.out.print(resultSet.getString("no"));
				System.out.print(",");
				//打印结果集中记录的description字段
				System.out.print(resultSet.getString("description"));
				System.out.print(",");
				//打印结果集中记录的remarks字段
				System.out.print(resultSet.getString("remarks"));
				//根据数据库中的数据,创建ProfTitle类型的对象
				ProfTitle profTitle = new ProfTitle(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getString("no"), resultSet.getString("remarks"));
				//添加到集合profTitles中
				profTitles.add(profTitle);
			}
			connection.close(); }catch (SQLException e){
			e.printStackTrace();
		}
		return profTitles;
	}

	public ProfTitle find(Integer id){
		ProfTitle desiredProfTitle = null;
		for (ProfTitle profTitle : this.findAll()) {
			if(id.equals(profTitle.getId())){
				desiredProfTitle =  profTitle;
				break;
			}
		}
		return desiredProfTitle;
	}

	public boolean update(ProfTitle profTitle) throws SQLException{
		Connection connection = JdbcHelper.getConn();
		String updateDepartment_sql = "UPDATE PROFTITLE set description='副教授'where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateDepartment_sql);
		preparedStatement.setInt(1,profTitle.getId());
		System.out.println("修改了第"+profTitle.getId());
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement, connection);
		return affectedRowNum>0;
	}

	public boolean add(ProfTitle profTitle) throws SQLException{
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		//根据连接对象准备语句对象,如果SQL语句为多行,注意语句不同部分之间要有空格
		String addProfTitle_sql = "INSERT INTO department(id,description,no,remarks,school_id) values"+"(?,?,?,?,?)";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(addProfTitle_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, profTitle.getId());
		preparedStatement.setString(2,profTitle.getDescription());
		preparedStatement.setString(3, profTitle.getNo());
		preparedStatement.setString(4, profTitle.getRemarks());
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement, connection);
		//如果影响的行数大于1,则返回true,否则返回false
		return affectedRowNum > 0;
	}

	public boolean delete(Integer id) throws SQLException{
		ProfTitle profTitle = this.find(id);
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		//根据连接对象准备语句对象,如果SQL语句为多行,注意语句不同部分之间要有空格
		String deleteProfTitle_sql = "DELETE from ProfTitle where id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteProfTitle_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, id);
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement,connection);
		System.out.println("删除了" + affectedRowNum + "条记录");
		return this.delete(profTitle);
	}

	public boolean delete(ProfTitle profTitle){
		return this.findAll().remove(profTitle);
	}
	public static void main(String[] args) throws SQLException{
		ProfTitle profTitle = profTitleDao.find(1);
		profTitleDao.update(profTitle);
		ProfTitle profTitle1 = profTitleDao.find(1);
		System.out.println("id:"+profTitle1.getId() + "no:" + profTitle1.getNo() + "description:" + profTitle1.getDescription() + "remarks:" + profTitle1.getRemarks());
	}
}

