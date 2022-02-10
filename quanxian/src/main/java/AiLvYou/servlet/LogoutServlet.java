package AiLvYou.servlet;

import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        RequestResult data = new RequestResult();
        data.setErrorCode(100);
        data.setResult("注销成功");
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
