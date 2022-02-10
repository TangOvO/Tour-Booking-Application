package AiLvYou.servlet;

import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Discount;
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

@WebServlet(urlPatterns = "/RoutineDiscountUpdateServlet")
public class RoutineDiscountUpdateServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();
    UserDaoImp dao = new UserDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String routineJSON = req.getParameter("routineJSON");
        String discountJSON = req.getParameter("discountJSON");

        RequestResult data = new RequestResult();
        data.setErrorCode(100);
        Routine routine = JSON.parseObject(routineJSON, Routine.class);
        Discount discount = JSON.parseObject(discountJSON, Discount.class);

        System.out.println("RoutineDiscountUpdateServlet routine：" + routine.toString()); //测试
        System.out.println("RoutineDiscountUpdateServlet discount：" + discount.toString()); //测试

        if (dao.updateRoutine(routine) == 1 && dao.updateDiscount(discount) == 1) {
            data.setErrorCode(200);
        }
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
