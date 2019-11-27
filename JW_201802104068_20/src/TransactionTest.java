import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {
    public static void main(String[] args){
        //在运行该类之前，先在数据库中将表school的no字段添加唯一性约束
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = JdbcHelper.getConn();
            //关闭自动提交
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO school(description,no)VALUES(?,?)");
            preparedStatement.setString(1, "管理学院");
            preparedStatement.setString(2, "22");
            //执行INSERT语句
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("INSERT INTO school(description,no)VALUES(?,?)");
            preparedStatement.setString(1, "土木学院");
            //第二条记录的no字段值设为02，由于数据库中在执行该类前已存在no为02的记录，所以违反no字段的唯一约束
            preparedStatement.setString(2, "02");
            //执行INSERT语句
            preparedStatement.executeUpdate();
            //提交当前连接所做的操作
            connection.commit();
        }catch (SQLException e){
            System.out.println(e.getMessage()+"\n errorCode="+e.getErrorCode());
            try{
                //回滚当前连接所做的操作
                if (connection != null){
                    connection.rollback();
                }
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        }finally {
            try{
                //恢复自动提交
                if (connection != null){
                    connection.setAutoCommit(true);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            //关闭资源
            JdbcHelper.close(preparedStatement, connection);
        }
    }
}
