//201802104044杨轲
package bysj.controller.department;

import bysj.service.DepartmentService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/department/delete.ctl")
public class DeleteDepartmentControl extends HttpServlet {
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //读取参数id
        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        //删除学位
        try{
            DepartmentService.getInstance().delete(id);
        }catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("delete doGet");
        //创建JSON对象
        JSONObject resp = new JSONObject();
        //加入数据信息
        resp.put("MSG", "OK");
        //响应
        response.getWriter().println(resp);
    }
}