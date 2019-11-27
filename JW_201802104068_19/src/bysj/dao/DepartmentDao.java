package bysj.dao;


import bysj.domain.Department;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class DepartmentDao {
	private static DepartmentDao departmentDao=new DepartmentDao();
	private DepartmentDao(){}

	public static DepartmentDao getInstance(){
		return departmentDao;
	}


	public Collection<Department> findAll(){
		Collection<Department> departments = new TreeSet<Department>();
		try{
			//获得数据库连接对象
			Connection connection = JdbcHelper.getConn();
			//在该连接上创建语句盒子对象
			Statement stmt = connection.createStatement();
			//执行SQL查询语句并获得结果集对象
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM Department");
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
				//根据数据库中的数据,创建Department类型的对象
				Department department = new Department(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getString("no"), resultSet.getString("remarks"),SchoolDao.getInstance().find(resultSet.getInt("school_id")));
				//添加到集合departments中
				departments.add(department);
			}
			connection.close(); }catch (SQLException e){
			e.printStackTrace();
		}
		return departments;
	}

	public Department find(Integer id){
		Department desiredDepartment = null;
		for (Department department : this.findAll()) {
			if(id.equals(department.getId())){
				desiredDepartment =  department;
				break;
			}
		}
		return desiredDepartment;
	}

	public boolean update(Department department) throws SQLException{
		Connection connection = JdbcHelper.getConn();
		String updateDepartment_sql = "UPDATE Department set description='艺术学院'where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateDepartment_sql);
		preparedStatement.setInt(1,department.getId());
		System.out.println("修改了第"+department.getId());
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement, connection);
		return affectedRowNum>0;
	}

	public boolean add(Department department) throws ClassNotFoundException, SQLException {
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		//根据连接对象准备语句对象,如果SQL语句为多行,注意语句不同部分之间要有空格
		String addDepartment_sql = "INSERT INTO department(id,description,no,remarks,school_id) values"+"(?,?,?,?,?)";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(addDepartment_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, department.getId());
		preparedStatement.setString(2,department.getDescription());
		preparedStatement.setString(3, department.getNo());
		preparedStatement.setString(4, department.getRemarks());
		preparedStatement.setInt(5, department.getSchool().getId());
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement, connection);
		//如果影响的行数大于1,则返回true,否则返回false
		return affectedRowNum > 0;
	}

	public boolean delete(Integer id) throws SQLException {
		Department department = this.find(id);
		Connection connection = JdbcHelper.getConn();
		String deleteDepartment_sql = "DELETE from Department where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(deleteDepartment_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, id);
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement,connection);
		System.out.println("删除了" + affectedRowNum + "条记录");
		return this.delete(department);
	}

	public boolean delete(Department department){
		return this.findAll().remove(department);
	}

	public static void main(String[] args) throws SQLException{
		Department department = departmentDao.find(1);
		departmentDao.update(department);
		Department department1 = departmentDao.find(1);
		System.out.println("id:" + department1.getId() + "description" + department1.getDescription() + "no:" + department1.getNo() + "remarks" + department1.getRemarks());
	}
}

