package AiLvYou.servlet;

import AiLvYou.entity.Client;
import AiLvYou.entity.Comment;
import AiLvYou.entity.Order;
import AiLvYou.entity.Routine;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/unCommentListServlet")
public class CommentServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestResult data = new RequestResult();
        if (req.getSession().getAttribute("user") == null) {
            data.setErrorCode(200);
            data.setResult("请先登录");
            //System.out.println(JSON.toJSONString(data));  //测试
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
            return;
        }
        Client client = (Client) req.getSession().getAttribute("user");
        List<Order> orders = serve.unComment((int) client.getId());
        List<Routine> routines = new ArrayList<>();
        for (Order order : orders) {
            Routine routine = serve.searchRoutineByID((int) order.getRoutineID());
            routines.add(routine);
        }
        data.setErrorCode(100);
        data.setResult(routines);
        System.out.println(JSON.toJSONString(data)); //测试
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
