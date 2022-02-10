package AiLvYou.servlet;

import AiLvYou.dao.UserDaoImp;
import AiLvYou.entity.Routine;
import AiLvYou.entity.RoutineFeatureImg;
import AiLvYou.service.UserServeImp;
import AiLvYou.util.RequestResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/ImgUploadServlet")
public class ImgUploadServlet extends HttpServlet {
    UserDaoImp dao = new UserDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        /*
        流
        InputStream in = req.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str = "";
        while((str=br.readLine())!=null){
            System.out.println(str);
        }

         */
        RequestResult data = new RequestResult();

        if (request.getParameter("method") == null) {
            uploadFile(request, "D:\\apache-tomcat-9.0.35\\webapps\\quanxian_war\\images\\show\\");
            success(resp, data);
        } else if (request.getParameter("method").equals("routineUpdate")){
            String method = request.getParameter("method");
            String routineJSON = request.getParameter("routineJSON");
            Routine routine = JSON.parseObject(routineJSON, Routine.class);
            if (routineUpdate(routine) == 1) {
                success(resp, data);
            }
        } else if (request.getParameter("method").equals("featureImgUpload")) {
            //System.out.println("连接拼接的参数是：" + request.getParameter("imgType")); //测试
            uploadFile(request, "D:\\apache-tomcat-9.0.35\\webapps\\quanxian_war\\images\\featureImg\\");
            success(resp, data);
            return;
        } else if (request.getParameter("method").equals("featureImgUpdate")) {
            String featureImgsJSON = request.getParameter("featureImgsJSON");
            List<RoutineFeatureImg> list = JSONObject.parseArray(featureImgsJSON, RoutineFeatureImg.class);
            //下面测试JSONObject.parseArray json转List<Object>
            /*
            for (RoutineFeatureImg img : list) {
                System.out.println(img.toString());
            }
            */

            if (dao.UpdateFeatureImgs(list) > 0) {
                success(resp, data);
            }
        } else if (request.getParameter("method").equals("detailImgUpdate")) {
            uploadFile(request, "D:\\apache-tomcat-9.0.35\\webapps\\quanxian_war\\images\\detailImg\\");
            success(resp, data);
        } else if (request.getParameter("method").equals("CategoryPic")) {
            uploadFile(request, "D:\\apache-tomcat-9.0.35\\webapps\\quanxian_war\\images\\categoryImg\\");
            success(resp, data);
        }

    }

    /**
     * 上传方法
     * @param request
     * @param path 储存路径
     */
    private void uploadFile(HttpServletRequest request, String path) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items= upload.parseRequest(request);
            for(FileItem item: items) {
                System.out.println("上传的图片名称是：" + item.getName()); //测试
                if(!item.isFormField()) {
                    item.write(new File(path+item.getName()));
                }
                if(item.isFormField()){
                    System.out.println(item.getString());
                    System.out.println(item.getFieldName());
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //routine img更新
    private int routineUpdate(Routine routine) {
        return dao.updateRoutine(routine);
    }

    private void success(HttpServletResponse resp, RequestResult data) throws IOException {
        data.setErrorCode(200);
        resp.getWriter().print(JSON.toJSONString(data));
        resp.getWriter().flush();
        resp.getWriter().close();
    }
}
