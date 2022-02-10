package AiLvYou.servlet;

import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(urlPatterns = "/VerificationCodeServlet")
public class VerificationCodeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestResult result = new RequestResult();
        int code = generateCode();
        result.setResult(code);
        System.out.println("VerificationCodeServlet-验证码：" + code);
        response.getWriter().print(JSON.toJSONString(result));
        response.getWriter().flush();
        response.getWriter().close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    //生成随机验证码
    private static int generateCode() {
        int verificationCode = (int) ((Math.random() * 9 + 1) * 10000);
        return verificationCode;
    }

    public static void main(String[] args) {
        //测试
        for (int i = 0; i < 10; i++) {
            System.out.println(generateCode());
        }
    }
}
