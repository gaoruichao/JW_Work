//201802104068高睿超
package school;


import domain.School;
import service.SchoolService;
import util.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@WebServlet("/school/list.ctl")
public class ListSchoolController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        Collection<School> schools = SchoolService.getInstance().findAll();
        StringBuilder sb = new StringBuilder("{");
        sb.append(Helper.quote("schools"));
        sb.append(":");
        sb.append("[");
        for (School school:schools){
            sb.append("{")
                    .append(Helper.quote("id")).append(":").append(school.getId())
                    .append(",")
                    .append(Helper.quote("no")).append(":").append(Helper.quote(school.getNo()))
                    .append(",")
                    .append(Helper.quote("description")).append(":").append(Helper.quote(school.getDescription()))
                    .append(",")
                    .append(Helper.quote("remarks")).append(":").append(Helper.quote(school.getRemarks()))
            .append("},");
        }
        /*Iterator iterator = schools.iterator();
        while(iterator.hasNext()) {
            Object object = iterator.next();
            School school = (School)object;
            System.out.println(school.getId() + school.getDescription() + school.getNo() + school.getRemarks());
        }*/
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        sb.append("}");
        System.out.println(sb);
        resp.getWriter().println(sb);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
