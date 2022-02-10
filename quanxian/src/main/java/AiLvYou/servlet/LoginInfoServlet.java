package AiLvYou.servlet;

import AiLvYou.entity.Client;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/LoginInfoServlet")
public class LoginInfoServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("进入LoginInfoServlet"); //测试
        HttpSession session = request.getSession();
        Client user = null;
        if (session.getAttribute("user") != null) {
            user = (Client) session.getAttribute("user");
            RequestResult result = new RequestResult();
            result.setResult(user);
            response.getWriter().print(JSON.toJSONString(result));
            System.out.println(JSON.toJSONString(result));
            response.getWriter().flush();
            response.getWriter().close();
            System.out.println("LoginInfoServlet：" + user.getName()); //测试
        } else {
            RequestResult data = new RequestResult();
            data.setErrorCode(100);
            data.setResult("未登录！");
            response.getWriter().print(JSON.toJSONString(data));
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
