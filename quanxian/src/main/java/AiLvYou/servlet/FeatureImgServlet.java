package AiLvYou.servlet;

import AiLvYou.entity.Routine;
import AiLvYou.entity.RoutineFeatureImg;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/FeatureImgServlet")
public class FeatureImgServlet extends HttpServlet {
    UserServeImp serve = new UserServeImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("routineID"));
        List<RoutineFeatureImg> imgs = serve.featureImgs(id);
        List<RoutineFeatureImg> sortedImgs = new ArrayList<>();
        //排序
        Map<Integer, RoutineFeatureImg> tempMap = new HashMap<>();
        for (RoutineFeatureImg img : imgs) {
            tempMap.put(img.getNo(), img);
        }
        for (int i = 1; i <= imgs.size(); i++) {
            if (tempMap.containsKey(i)) {
                sortedImgs.add(tempMap.get(i));
            }
        }

        //测试顺序是否正确
        /*
        for (RoutineFeatureImg img : sortedImgs) {
            System.out.println(img.getNo() + "--" + img.getImg());
        }

         */
        RequestResult data = new RequestResult();
        data.setResult(200);
        data.setResult(sortedImgs);
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
