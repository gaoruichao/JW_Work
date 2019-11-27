package cn.edu.sdjzu.xg.bysj.controller.basic.teacher;

import cn.edu.sdjzu.xg.bysj.domain.Degree;
import cn.edu.sdjzu.xg.bysj.domain.Teacher;
import cn.edu.sdjzu.xg.bysj.service.DegreeService;
import cn.edu.sdjzu.xg.bysj.service.TeacherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * 将所有方法组织在一个Controller(Servlet)中
 */
@WebServlet("/teacher.ctl")
public class TeacherController extends HttpServlet {

    /**
     * POST,http://49.233.189.50/JW_201802104068_24/teacher.ctl
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置响应字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        //设置请求字符编码为UTF-8
        request.setCharacterEncoding("UTF-8");
        //根据request对象，获得代表参数的JSON字串
        String teacher_json = JSONUtil.getJSON(request);

        //将JSON字串解析为Teacher对象
        Teacher teacherToAdd = JSON.parseObject(teacher_json, Teacher.class);
        System.out.println(teacherToAdd);
        try {
            //增加加Teacher对象
            TeacherService.getInstance().add(teacherToAdd);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        //响应
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        //加入数据信息
        message.put("statusCode", "200");
        message.put("message", "增加成功");
        message.put("data", null);

        //响应message到前端
        response.getWriter().println(message);
    }

    /**
     * DELETE,http://49.233.189.50/JW_201802104068_24/teacher.ctl
     * 删除一个对象：根据来自前端请求的id，删除数据库表中id的对应记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        //读取参数id
        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        try{
        //到数据库表中删除
        TeacherService.getInstance().delete(id);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }

        //响应
        response.setContentType("html/text;charset=UTF8");
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        //加入数据信息
        message.put("statusCode", "200");
        message.put("message", "删除成功");
        message.put("data", null);

        //响应message到前端
        response.getWriter().println(message);
    }


    /**
     * PUT,http://49.233.189.50/JW_201802104068_24/teacher.ctl
     * 修改一个对象：将来自前端请求的JSON对象，更新数据库表中相同id的记录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置响应字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        //设置请求字符编码为UTF-8
        request.setCharacterEncoding("UTF-8");
        String teacher_json = JSONUtil.getJSON(request);
        //将JSON字串解析为Teacher对象
        Teacher teacherToAdd = JSON.parseObject(teacher_json, Teacher.class);
        try{
        //增加加Teacher对象
        TeacherService.getInstance().update(teacherToAdd);
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }


        //响应
        response.setContentType("html/text;charset=UTF8");
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        //加入数据信息
        message.put("statusCode", "200");
        message.put("message", "更新成功");
        message.put("data", null);

        //响应message到前端
        response.getWriter().println(message);
    }

    /**
     * GET,http://49.233.189.50/JW_201802104068_24/teacher.ctl
     * 响应一个或所有对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置响应字符编码为UTF-8
        response.setContentType("text/html;charset=UTF-8");
        //读取参数id
        String id_str = request.getParameter("id");
        //如果id = null, 表示响应所有对象，否则响应id指定的对象
        if(id_str == null){
            responseTeachers(response);
        }else{
            int id = Integer.parseInt(id_str);
            responseTeacher(id, response);
        }
    }
    //响应一个对象
    private void responseTeacher(int id, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //根据id查找
            Teacher teacher = TeacherService.getInstance().find(id);
        String teacher_json = JSON.toJSONString(teacher);
        //响应到前端
        response.getWriter().println(teacher_json);}
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    //响应所有对象
    private void responseTeachers(HttpServletResponse response)
            throws ServletException, IOException {
        //获得所有
        Collection<Teacher> teachers = TeacherService.getInstance().findAll();
        String teachers_json = JSON.toJSONString(teachers);
        //响应message到前端
        response.getWriter().println(teachers_json);
    }

}
