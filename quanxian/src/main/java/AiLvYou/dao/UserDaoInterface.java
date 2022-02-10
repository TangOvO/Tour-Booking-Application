package AiLvYou.dao;

import AiLvYou.entity.*;
import AiLvYou.servlet.FeatureImgServlet;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

public interface UserDaoInterface {

    public List<Map<String, Object>> queryA114();

    /**
     * 用户
     */
    //返回所有用户
    public List<Client> LoginSearch(Client client);

    //用户名查询用户
    public Client searchClientByName(String name);

    //用户ID查询用户
    public Client searchClientByID(int clientID);

    //插入用户
    public int insertClient(Client client);

    //删除用户
    public int deleteClient(Client client);

    //修改用户信息
    public int updateClient(Client client);

    /**
     * 订单客户信息 Guest
     */
    //更新guest
    public int updateGuest(Guest guest);

    //插入Guest信息
    public int insertGuest(Guest guest);

    //删除Guest信息
    public int deleteGuest(Guest guest);

    public int deleteGuestByIdentity(long identity);

    //删除订单全部guest
    public int deleteGuest(long orderID);

    //查找Guest
    public Guest searchGuest(int orderID, int identity);

    //orderID查找Guest
    public List<Guest> searchGuest(long orderID);

    /**
     * order。订单分类查询service层处理
     */
    //查询订单
    public List<Order> searchOrders();

    //查询某客户（clientID）全部订单
    public List<Order> searchOrders(int clientID);

    //修改order时用于查询该order
    public Order searchOrders(int clientID, int routineID);

    //orderID查询该order
    public Order searchOrder(long orderID);

    //查询未评价订单
    public List<Order> searchOrdersUncomment(int clientID);



    //修改订单
    public int updateOrder(Order order, String fieldName);

    //增加新订单
    public int insertOrder(Order order);

    //routineID查询order
    public List<Order> searchOrderByRoutine(int routnieID);

    //更新order
    public int updateOrder(Order order);

    //删除order
    public int deleteOrder(long orderID);

    /**
     * 历史记录
     */
    //查询历史记录
    public List<History> searchHistory(int clientID);

    //插入新历史记录
    public int insertHistory(History history);

    //更新历史记录
    public int updateHistory(History history);

    /**
     * 路线
     */
    //查询全部路线
    public List<Routine> allRoutines();

    //id查询路线
    public Routine searchRoutine(int id);

    //路线信息更新
    public int updateRoutine(Routine routine);

    //routineID查询该线路的全部详细信息detail
    public List<Detail> details(int routineID);

    //routineID查询该线路的全部特色图片
    public List<RoutineFeatureImg> featureImgs(int routineID);

    //featureImgs根据List<>RoutineFeatureImg
    public int UpdateFeatureImgs(List<RoutineFeatureImg> list);

    //根据routineID删除全部RoutineFeatureImg
    public int deleteFeatureImgs(int routineID);

    //插入一条RoutineFeatureImg
    public int insertRoutineFeatureImg(RoutineFeatureImg featureImg);

    //路线个数
    public int numOfRoutines();

    //分页查询routine
    public List<Routine> allRoutinesPage(int currPage, int numPerPage);

    //删除路线
    public int deleteRoutine(int routineID);

    //新增路线
    public int newRoutine(Routine routine);

    /**
     * 评论
     */
    //增加评论
    public int insertComment(Comment comment);

    //查询评论
    public List<Comment> searchComments(String fieldName, int id);

    /**
     * 优惠信息
     */
    //routineID查询优惠信息
    public Discount searchDiscount(int routineID);

    //更新优惠信息
    public int updateDiscount(Discount discount);

    /**
     * Detail操作
     */
    //根据routineID更新全部detail
    public int updateAllDetail(List<Detail> details);

    //根据routineID删除全部detail
    public int deleteAllDetail(int routineID);

    //删除 比较routineID和time删除一条detail
    public int deleteDetail(Detail detail);

    //更新一条detail 根据routineID time定位
    public int updateDetail(Detail detail);

    //插入一条detail
    public int insertDetail(Detail detail);

    /**
     * 分类
     */
    //分页查询category
    public List<Category> allCategoryPage(int currPage, int numPerPage);

    //查询分类 type字段分类
    public List<Category> searchCategory(String type);

    //查询全部分类
    public List<Category> searchCategory();

    //根据categoryID查询routine
    public List<Routine> srarchRoutineByCategory(int categoryID);

    //根据categoryID删除Category
    public int deleteCategory(int categoryID);

    //更新一条category
    public int updateCategory(Category category);

    //插入一条category
    public int insertCateGory(Category category);

    //插入一条CategoryRoutine
    public int insertCategoryRoutine(CategoryRoutine categoryRoutine);

    //删除一条CategoryRoutine
    public int deleteCategoryRoutine(CategoryRoutine categoryRoutine);
}
