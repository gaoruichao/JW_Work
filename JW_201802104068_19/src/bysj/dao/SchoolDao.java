package bysj.dao;


import bysj.domain.Degree;
import bysj.domain.School;
import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class SchoolDao {
	private static SchoolDao schoolDao = new SchoolDao();
	
	private SchoolDao(){}
	
	public static SchoolDao getInstance(){
		return schoolDao;
	}

	public Collection<School> findAll(){
		Collection<School> schools = new TreeSet<School>();
		try{
			//获得数据库连接对象
			Connection connection = JdbcHelper.getConn();
			//在该连接上创建语句盒子对象
			Statement stmt = connection.createStatement();
			//执行SQL查询语句并获得结果集对象
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM School");
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
				//根据数据库中的数据,创建School类型的对象
				School school = new School(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getString("no"), resultSet.getString("remarks"));
				//添加到集合schools中
				schools.add(school);
			}
			connection.close(); }catch (SQLException e){
			e.printStackTrace();
		}
		return schools;
	}
	
	public School find(Integer id){
		School desiredSchool = null;
		Collection<School> schools = this.findAll();
		for (School school : schools) {
			if(id.equals(school.getId())){
				desiredSchool =  school; 
			}
		}
		return desiredSchool;
	}
	
	public boolean update(School school){
		this.findAll().remove(school);
		return this.findAll().add(school);
	}

	public School addWithSP(School school) throws ClassNotFoundException, SQLException {
		//加载驱动程序
		Class.forName("com.mysql.cj.jdbc.Driver");
		String  url = "jdbc:mysql://localhost:3306/bysj?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
		String user = "root";
		String password = "nideweixiao";
		//获得数据库连接对象
		Connection connection = DriverManager.getConnection(url,user,password);
		//根据连接对象准备可调用语句对象,sp_addSchool为存储过程名称,后面为4个参数
		CallableStatement callableStatement = connection.prepareCall("{CALL sp_addSchool(?,?,?,?)}");
		//将第5个参数设置为输出参数,类型为长整数(数据库的数据类型)
		callableStatement.registerOutParameter(4, Types.BIGINT);
		callableStatement.setString(1, school.getDescription());
		callableStatement.setString(2, school.getNo());
		callableStatement.setString(3, school.getRemarks());
		//执行可调用语句callableStatement
		callableStatement.execute();
		//获得第4个参数的值,数据库为该记录自动生成的id
		int id = callableStatement.getInt(4);
		//为参数school的id字段赋值
		school.setId(id);
		callableStatement.close();
		connection.close();
		return school;
	}
	public boolean delete(Integer id) throws SQLException {
		School school = this.find(id);
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		//根据连接对象准备语句对象,如果SQL语句为多行,注意语句不同部分之间要有空格
		String deleteSchool_sql = "DELETE from school where id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteSchool_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, school.getId());
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement,connection);
		System.out.println("删除了" + affectedRowNum + "条记录");
		return this.delete(school);
	}
	
	public boolean delete(School school){
		return this.findAll().remove(school);
	}
}
