package AiLvYou.servlet;

import AiLvYou.dao.UserDaoImp;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/CancleOrderServlet")
public class CancleOrderServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CancleOrderServlet:" + req.getParameter("id")); //测试
        long orderID = Long.parseLong(req.getParameter("orderID"));
        RequestResult data = new RequestResult();

        if (serve.deleteOrderAndGuest(orderID)) {
            data.setErrorCode(200);
            data.setResult("删除订单成功！");
            resp.getWriter().print(JSON.toJSONString(data));
            resp.getWriter().flush();
            resp.getWriter().close();
        }

    }
}
