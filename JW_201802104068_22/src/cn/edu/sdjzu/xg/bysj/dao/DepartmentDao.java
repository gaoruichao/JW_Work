package cn.edu.sdjzu.xg.bysj.dao;

import cn.edu.sdjzu.xg.bysj.domain.Department;
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
	//返回结果集对象
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
				//打印结果集中记录的school_id字段
				System.out.println(SchoolDao.getInstance().find(resultSet.getInt("school_id")));
				//根据数据库中的数据,创建Department类型的对象
				Department department = new Department(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getString("no"), resultSet.getString("remarks"),SchoolDao.getInstance().find(resultSet.getInt("school_id")));
				//添加到集合departments中
				departments.add(department);
			}
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return departments;
	}
	public Department find(Integer id) throws SQLException{
		//声明一个Department类型的变量
		Department department = null;
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		String deleteDepartment_sql = "SELECT * FROM Department WHERE id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteDepartment_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1,id);
		//执行预编译语句
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()){
			department = new Department(resultSet.getInt("id"),resultSet.getString("description"),resultSet.getString("no"),resultSet.getString("remarks"),SchoolDao.getInstance().find(resultSet.getInt("school_id")));
		}
		//关闭资源
		JdbcHelper.close(resultSet,preparedStatement,connection);
		return department;
	}
	public boolean add(Department department) throws SQLException,ClassNotFoundException{
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		String addDepartment_sql = "INSERT INTO department (description,no,remarks,school_id) VALUES"+" (?,?,?,?)";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(addDepartment_sql);
		//为预编译参数赋值
		preparedStatement.setString(1,department.getDescription());
		preparedStatement.setString(2,department.getNo());
		preparedStatement.setString(3,department.getRemarks());
		preparedStatement.setInt(4,department.getSchool().getId());
		//执行预编译语句，获取添加记录行数并赋值给affectedRowNum
		int affectedRowNum=preparedStatement.executeUpdate();
		System.out.println("添加了"+affectedRowNum+"行记录");
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRowNum>0;
	}
	//delete方法，根据department的id值，删除数据库表中对应的department对象
	public boolean delete(int id) throws ClassNotFoundException,SQLException{
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		String deleteDepartment_sql = "DELETE FROM department WHERE id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteDepartment_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1,id);
		//执行预编译语句，获取删除记录行数并赋值给affectedRowNum
		int affectedRows = preparedStatement.executeUpdate();
		System.out.println("删除了"+affectedRows+"行记录");
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRows>0;
	}
	public boolean update(Department department) throws ClassNotFoundException,SQLException{
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		String updateDepartment_sql = " update department set description=?,no=?,remarks=? where id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(updateDepartment_sql);
		//为预编译参数赋值
		preparedStatement.setString(1,department.getDescription());
		preparedStatement.setString(2,department.getNo());
		preparedStatement.setString(3,department.getRemarks());
		preparedStatement.setInt(4,department.getId());
		//执行预编译语句，获取改变记录行数并赋值给affectedRowNum
		int affectedRows = preparedStatement.executeUpdate();
		System.out.println("修改了"+affectedRows+"行记录");
		//关闭资源
		JdbcHelper.close(preparedStatement,connection);
		return affectedRows>0;
	}
}
