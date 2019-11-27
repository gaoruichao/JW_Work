import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DBAddTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //url为数据库连接字串
        //jdbc为协议，mysql为子协议
        //localhost:3306为数据库服务器的地址和端口
        //?后面指定编码为UTF-8
        //服务时区为中国上海
        String url = "jdbc:mysql://localhost:3306/bysj?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        //用户名
        String username = "root";
        //密码
        String password = "nideweixiao";
        //获得连接对象
        Connection connection = DriverManager.getConnection(url,username,password);
        //在该连接上创建语句盒子对象
        Statement stmt = connection.createStatement();
        //创建sql语句
        String addDegree_sql = "INSERT INTO degree (no,description,remarks) values"+" ('01','博士','')";
        //令语句盒子执行sql语句
        stmt.execute(addDegree_sql);
        //关闭stmt对象
        stmt.close();
        //关闭connection对象
        connection.close();
    }
}
