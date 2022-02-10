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
import java.sql.Date;
import java.util.List;

@WebServlet(urlPatterns = "/MakeCommentServlet")
public class MakeCommentServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MakeCommentServlet routineID：" + req.getParameter("routineID")); //测试
        System.out.println("score:" + req.getParameter("score") + "content:" + req.getParameter("content")); //测试

        int score = Integer.parseInt(req.getParameter("score"));
        String content = req.getParameter("content");
        int routineID = Integer.parseInt(req.getParameter("routineID"));
        long orderID = 0;
        Client client = (Client) req.getSession().getAttribute("user");
        Routine routine = serve.searchRoutineByID(routineID);
        RequestResult data = new RequestResult();

        List<Order> orders = serve.searchOrders((int) client.getId());
        for (Order order : orders) {
            if (order.getRoutineID() == routineID) {
                orderID = order.getOrderID();
            }
        }
        //原子性？
        if (serve.insertComment((int) client.getId(), routineID, content, score) == 1) {
            if (serve.orderCommentStatusUpdate(orderID) == 1) {
                data.setErrorCode(100);
                data.setResult("评论成功！");
                resp.getWriter().print(JSON.toJSONString(data));
                resp.getWriter().flush();
                resp.getWriter().close();
                return;
            }
        }
        data.setErrorCode(200);
        data.setResult("评论失败，请重新评论");
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
