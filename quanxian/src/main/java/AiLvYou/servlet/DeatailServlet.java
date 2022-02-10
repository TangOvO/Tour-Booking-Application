package AiLvYou.servlet;

import AiLvYou.entity.Comment;
import AiLvYou.entity.Detail;
import AiLvYou.entity.Discount;
import AiLvYou.entity.Routine;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/DeatailServlet")
public class DeatailServlet extends HttpServlet {
    UserServeImp service = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("routineID"));
        //System.out.println("DetailServlet:" + id); //测试
        Routine routine = service.searchRoutineByID(id);
        RequestResult result = new RequestResult();
        List<Comment> comments = service.searchCommentByRoutineID(id);
        Discount discount = service.discount(id);

        //System.out.println("测试routine：" + routine.toString()); //测试

        Object[] os = new Object[]{routine, comments, discount};
        result.setErrorCode(100);
        result.setResult(os);

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        //System.out.println("DetailServlet：" + JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat)); //测试
        resp.getWriter().print(JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
