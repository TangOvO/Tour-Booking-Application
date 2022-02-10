package AiLvYou.servlet;

import AiLvYou.dao.MyBatisDao;
import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Comment;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/RoutineListServlet")
public class RoutineListServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();
    UserDaoImp dao = new UserDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryID = req.getParameter("categoryID");
        List<Routine> routines = new ArrayList<>();
        if (categoryID == null) {
            routines = serve.allRoutines();
        } else {
            routines = dao.srarchRoutineByCategory(Integer.parseInt(categoryID));
        }

        List scores = new ArrayList();
        List numOfComment = new ArrayList();
        List<Comment> comments = null;
        for (int i = 0; i < routines.size(); i++) {
            comments = serve.searchCommentByRoutineID((int) routines.get(i).getId());
            int num = 0;
            double score;
            double sum = 0;
            for (Comment comment : comments) {
                sum += comment.getScore();
                num++;
            }
            //BigDecimal decimal = new BigDecimal(sum / num);
            if (comments.size() != 0) {
                scores.add(sum / num);
            } else {
                System.out.println("sum:" + sum);
                scores.add(sum);
            }
            numOfComment.add(num);
        }
        Object[] os = new Object[] {routines, scores, numOfComment};
        RequestResult result = new RequestResult();
        result.setErrorCode(100);
        result.setResult(os);
        resp.getWriter().print(JSON.toJSONString(result));
        resp.getWriter().flush();
        resp.getWriter().close();
        System.out.println(JSON.toJSONString(result)); //测试
    }
}
