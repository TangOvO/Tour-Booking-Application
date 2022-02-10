package AiLvYou.service;

import AiLvYou.entity.*;

import java.util.List;

public interface UserServeInterface {

    //登录验证
    public Client Login(String name, String pwd);

    //注册账户
    public int registerClient(Client client);

    //预定路线
    public int orderRoutine(Client client, String travelDate, long routineID, long orderID, List<Guest> guests, double payment);

    //全部路线查询
    public List<Routine> allRoutines();

    //id查路线
    public Routine searchRoutineByID(int routineID);

    //浏览历史记录查询
    public List<History> searchHistory(int clientID);

    //删除clientID全部历史记录
    //public void

    //全部订单查询
    public List<Order> searchOrders(int clientID);

    //orderID查order
    public Order searchOrder(long orderID);

    //删除订单
    public int deleteOrder(long orderID);

    public boolean deleteOrderAndGuest(long orderID);

    //待付款查询
    public List<Order> unPay(int clientID);

    //未出行查询
    public List<Order> unTravel(int clientID);

    //待点评查询
    public List<Order> unComment(int clientID);

    //增加评论
    public int insertComment(int clientID, int routineID, String content, int score);

    //clientID查询评论
    public List<Comment> searchCommentByClientID(int clientID);

    //routineID查询评论
    public List<Comment> searchCommentByRoutineID(int routineID);

    //修改订单评论status为true
    public int orderCommentStatusUpdate(long orderID);

    //修改订单支付status为true
    public int orderPayStatusUpdate(long orderID);

    //更新已建立的order信息
    public int orderPayStatusUpdate(Order order);

    //修改订单出行status为true
    public int orderTravelStatusUpdate(long orderID);

    //路线报名人数加1
    public int addRoutineActualNum(int routineID, int number);

    //新增历史记录
    public int insertHistory(int routineID, int clientID);

    //判断该clientID是否有过相同历史记录，找到返回该条记录，没有返回null
    public History existHistory(int routineID, int clientID);

    //routineID查询该线路的全部详细信息detail
    public List<Detail> details(int routineID);

    //routineID查询优惠信息
    public Discount discount(int routineID);

    //routineID查询特色的图片
    public List<RoutineFeatureImg> featureImgs(int routineID);

    //插入guest
    public int insertGuest(int orderID, String name, int identity, int phoneNumber, String status);

    //删除某个订单的全部顾客
    public int deleteGuests(long orderID);
}
