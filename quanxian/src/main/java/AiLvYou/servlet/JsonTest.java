package AiLvYou.servlet;

import AiLvYou.entity.Guest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
        /*
        Guest guest = new Guest();
        guest.setOrderID(1);
        guest.setIdentity(123456);
        guest.setName("测试");
        guest.setPhoneNumber(123123);
        guest.setStatus("儿童");

        String jGuest = JSON.toJSONString(guest);
        System.out.println(JSON.toJSON(guest));
        Guest guest1 = JSON.parseObject(jGuest, Guest.class);
        System.out.println(guest1.toString());

         */

        //String str = ["{\"orderID\":\"202063172842\",\"name\":\"a1\",\"identity\":\"\",\"phoneNumber\":\"\",\"status\":\"成人\"}","{\"orderID\":\"202063172842\",\"name\":\"a2\",\"identity\":\"\",\"phoneNumber\":\"\",\"status\":\"成人\"}","{\"orderID\":\"202063172842\",\"name\":\"a3\",\"identity\":\"\",\"phoneNumber\":null,\"status\":\"儿童\"}"];
        String s = "[\"{\\\"orderID\\\":\\\"202063172842\\\",\\\"name\\\":\\\"a1\\\",\\\"identity\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\",\\\"status\\\":\\\"成人\\\"}\",\"{\\\"orderID\\\":\\\"202063172842\\\",\\\"name\\\":\\\"a2\\\",\\\"identity\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\",\\\"status\\\":\\\"成人\\\"}\",\"{\\\"orderID\\\":\\\"202063172842\\\",\\\"name\\\":\\\"a3\\\",\\\"identity\\\":\\\"\\\",\\\"phoneNumber\\\":null,\\\"status\\\":\\\"儿童\\\"}\"]";
        List<Guest> list = JSON.parseArray(s, Guest.class);
        for (Guest guest : list) {
            System.out.println(guest.getName());
        }
        System.out.println(JSON.parseArray(s));
    }
}
