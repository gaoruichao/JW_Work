package bysj.controller.basic.degree;

import bysj.service.DegreeService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/degree/delete.ctl")
public class DeleteDegreeControl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //读取参数id
        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        try{
        //删除学位
        DegreeService.getInstance().delete(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("delete doGet" + id);
        //创建JSON对象
        JSONObject resp = new JSONObject();

        //加入数据信息
        resp.put("MSG", "OK");
        //响应
        response.getWriter().println(resp);
    }
}
