package AiLvYou.servlet;

import AiLvYou.entity.Client;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserServeImp userServe = new UserServeImp();
        RequestResult result = new RequestResult();

        System.out.println("LoginServlet测试：" + request.getParameter("name") + "-" + request.getParameter("pwd"));

        Client user = userServe.Login(request.getParameter("name"), request.getParameter("pwd"));

        if (user != null) {
            request.getSession().setAttribute("user", user);
            result.setResult("success");
            result.setErrorCode(100);
            response.getWriter().print(JSON.toJSONString(result));
            response.getWriter().flush();
            response.getWriter().close();
        } else {
            result.setResult("not found");
            response.getWriter().print(JSON.toJSONString(result));
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
