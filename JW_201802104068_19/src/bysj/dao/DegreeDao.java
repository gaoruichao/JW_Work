package bysj.dao;

import bysj.domain.Degree;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.TreeSet;

public final class DegreeDao {
	private static DegreeDao degreeDao=
			new DegreeDao();
	private DegreeDao(){}
	public static DegreeDao getInstance(){
		return degreeDao;
	}

	public Collection<Degree> findAll(){
		Collection<Degree> degrees = new TreeSet<Degree>();
		try{
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		//在该连接上创建语句盒子对象
		Statement stmt = connection.createStatement();
		//执行SQL查询语句并获得结果集对象
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM Degree");
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
			//根据数据库中的数据,创建Degree类型的对象
			Degree degree1 = new Degree(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getString("no"), resultSet.getString("remarks"));
			//添加到集合degrees中
			degrees.add(degree1);
		}
		connection.close(); }catch (SQLException e){
			e.printStackTrace();
		}
		return degrees;
	}

	public Degree find(Integer id){
		Degree desiredDegree = null;
		for (Degree degree : this.findAll()) {
			if(id.equals(degree.getId())){
				desiredDegree =  degree;
				break;
			}
		}
		return desiredDegree;
	}

	public boolean update(Degree degree) throws SQLException{
		Connection connection = JdbcHelper.getConn();
		String updateDegree_sql = "UPDATE Degree set remarks='TheBest'where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(updateDegree_sql);
		preparedStatement.setInt(1,degree.getId());
		System.out.println("修改了第"+degree.getId());
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement, connection);
		return affectedRowNum>0;
	}

	public boolean add(Degree degree) throws SQLException{
		//获得数据库连接对象
		Connection connection = JdbcHelper.getConn();
		//根据连接对象准备语句对象,如果SQL语句为多行,注意语句不同部分之间要有空格
		String addDegree_sql = "INSERT INTO degree(id,description,no,remarks) values"+"(?,?,?,?)";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(addDegree_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, degree.getId());
		preparedStatement.setString(2,degree.getDescription());
		preparedStatement.setString(3, degree.getNo());
		preparedStatement.setString(4, degree.getRemarks());
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement, connection);
		//如果影响的行数大于1,则返回true,否则返回false
		return affectedRowNum > 0;
	}

	public boolean delete(Integer id) throws SQLException{
		Degree degree = this.find(id);
		//获得数据库连接对象
        Connection connection = JdbcHelper.getConn();
		//根据连接对象准备语句对象,如果SQL语句为多行,注意语句不同部分之间要有空格
		String deleteDegree_sql = "DELETE from degree where id=?";
		//在该连接上创建预编译语句对象
		PreparedStatement preparedStatement = connection.prepareStatement(deleteDegree_sql);
		//为预编译参数赋值
		preparedStatement.setInt(1, id);
		//执行预编译语句,用其返回值(影响的行数)为变量affectedRowNum赋值
		int affectedRowNum = preparedStatement.executeUpdate();
		JdbcHelper.close(preparedStatement,connection);
		System.out.println("删除了" + affectedRowNum + "条记录");
		return this.delete(degree);
	}

	public boolean delete(Degree degree){
		return this.findAll().remove(degree);
	}

	public static void main(String[] args) throws SQLException{
		Degree degree = degreeDao.find(1);
		degreeDao.update(degree);
		Degree degree1 = degreeDao.find(1);
		System.out.println("id:"+degree1.getId() + "no:" + degree1.getNo() + "description:" + degree1.getDescription() + "remarks:" + degree1.getRemarks());
	}
}

