package bysj.dao;


import bysj.domain.Teacher;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class TeacherDao {
	private static TeacherDao teacherDao=new TeacherDao();
	private TeacherDao(){}
	public static TeacherDao getInstance(){
		return teacherDao;
	}

	public Collection<Teacher> findAll(){
		Collection<Teacher> teachers = new TreeSet<Teacher>();
		try{
			//获得数据库连接对象
			Connection connection = JdbcHelper.getConn();
			//在该连接上创建语句盒子对象
			Statement stmt = connection.createStatement();
			//执行SQL查询语句并获得结果集对象
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM Teacher");
			//若结果存在下一条，执行循环体
			while (resultSet.next()) {
				//根据数据库中的数据,创建Teacher类型的对象
				Teacher teacher = new Teacher(resultSet.getInt("id"),resultSet.getString("name"),ProfTitleDao.getInstance().find(resultSet.getInt("title_id")),DegreeDao.getInstance().find(resultSet.getInt("degree_id")),DepartmentDao.getInstance().find(resultSet.getInt("department_id")));
				//添加到集合teachers中
				teachers.add(teacher);
			}
			connection.close(); }catch (SQLException e){
			e.printStackTrace();
		}
		return teachers;
	}
	
	public Teacher find(Integer id){
		Teacher desiredTeacher = null;
		for (Teacher teacher : this.findAll()) {
			if(id.equals(teacher.getId())){
				desiredTeacher =  teacher; 
				break;
			}
		}
		return desiredTeacher;
	}
	
	public boolean update(Teacher teacher) throws SQLException{
		Connection connection = JdbcHelper.getConn();
		String updateTeacher_sql = "UPDATE TEACHER set name='张三'where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateTeacher_sql);
		preparedStatement.setInt(1,teacher.getId());
		System.out.println("修改了第"+teacher.getId());
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement, connection);
		return affectedRowNum>0;
	}
	
	public boolean add(Teacher teacher)throws SQLException{
 		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		//根据连接对象准备语句对象,如果SQL语句为多行,注意语句不同部分之间要有空格
		String addProfTitle_sql = "INSERT INTO Teacher(id,name,profTitle_id,degree_id ,department_id ) values"+"(?,?,?,?,?)";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(addProfTitle_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, teacher.getId());
		preparedStatement.setString(2,teacher.getName());
		preparedStatement.setInt(3, teacher.getTitle().getId());
		preparedStatement.setInt(4, teacher.getDegree().getId());
		preparedStatement.setInt(5, teacher.getDepartment().getId());
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement, connection);
		//如果影响的行数大于1,则返回true,否则返回false
		return affectedRowNum > 0;
	}

	public boolean delete(Integer id)throws SQLException{
		Teacher teacher = this.find(id);
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		//根据连接对象准备语句对象,如果SQL语句为多行,注意语句不同部分之间要有空格
		String deleteTeacher_sql = "DELETE from Teacher where id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteTeacher_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, id);
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement,connection);
		System.out.println("删除了" + affectedRowNum + "条记录");
		return this.delete(teacher);
	}
	
	public boolean delete(Teacher teacher){
		return this.findAll().remove(teacher);
	}
	
	
}
