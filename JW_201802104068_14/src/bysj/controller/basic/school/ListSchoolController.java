package bysj.controller.basic.school;

import bysj.domain.School;
import bysj.service.SchoolService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

//映射的url分两部分：实体名称(学院)和动作名称(列表),ctl说明这个url是一个controller(servlet)
@WebServlet("/school/list.ctl")
public class ListSchoolController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获得所有学院
        Collection<School> schools = SchoolService.getInstance().findAll();
        //初始化json字串
        StringBuilder sb = new StringBuilder("{");
        //json数组元素的名字
        sb.append(Helper.quote("schools"));
        sb.append(":");
        sb.append("[");//整个数组的开始符号
        //通过集合元素生成json数组元素
        for(School school:schools) {
                    sb.append("{")//“{”是数组元素的结束符号，
                            .append(Helper.quote("id")).append(":").append(Helper.quote(""+school.getId()))
                            .append(",")//数组中的数据项用逗号分开
                            .append(Helper.quote("no")).append(":").append(Helper.quote(school.getNo()))
                            .append(",")//数组中的数据项用逗号分开
                            .append(Helper.quote("description")).append(":").append(Helper.quote(school.getDescription()))
                    .append("},");//“}”是数组元素的结束符号，每个数组元素后追加一个逗号
        }
        //删除最后一个逗号
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");//整个数组结束符号
        sb.append("}");
//        System.out.println(sb);

        response.getWriter().println(sb);
        /**
         * {
         *   "schools": [
         *     {
         *       "no": "01",
         *       "description": "土木工程"
         *     },
         *     {
         *       "no": "02",
         *       "description": "管理工程"
         *     },
         *     {
         *       "no": "03",
         *       "description": "市政工程"
         *     },
         *     {
         *       "no": "04",
         *       "description": "艺术"
         *     }
         *   ]
         * }
         */
    }
}
