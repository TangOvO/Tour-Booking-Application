package AiLvYou.servlet;

import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        RequestResult result = new RequestResult();

        if (name.equals("admin") && pwd.equals("admin")) {
            result.setResult("success");
            result.setErrorCode(100);
            response.getWriter().print(JSON.toJSONString(result));
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            result.setResult("");
            result.setErrorCode(1);
            response.getWriter().print(JSON.toJSONString(result));
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
