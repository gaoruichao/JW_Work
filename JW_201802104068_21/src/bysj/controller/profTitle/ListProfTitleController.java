package bysj.controller.profTitle;

import bysj.domain.ProfTitle;
import bysj.service.ProfTitleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/profTitle/list.ctl")
public class ListProfTitleController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Collection<ProfTitle> profTitles = ProfTitleService.getInstance().findAll();
        String profTitles_str = JSON.toJSONString(profTitles, SerializerFeature.DisableCircularReferenceDetect);
        response.getWriter().println(profTitles_str);
    }
}

