package AiLvYou.dao;

import AiLvYou.entity.Client;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseDao {

    //存放表字段，键是表名，值是List，List里面存放字段名
    public static Map<String, List<String>> colName = new ConcurrentHashMap<>();
    public static Map<String, List<String>> typeName = new ConcurrentHashMap<>();

    //存放字段是否是自增，如果是自增为true，否则为false,键是表名，值是List，List里面存放自增标志
    public static Map<String, List<Boolean>> isAuto = new ConcurrentHashMap<>();

    public static void setDesc(Connection conn, String tableName) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("select * from " + tableName + " limit 0, 1");
            //System.out.println(ps.toString()); //测试
            rs = ps.executeQuery();
            //Returns the number of columns in this <code>ResultSet</code> object.
            ResultSetMetaData data = rs.getMetaData();
            List<String> names = new ArrayList<>();
            List<String> types = new ArrayList<>();
            List<Boolean> isAutos = new ArrayList<>();

            for (int i = 1; i <= data.getColumnCount(); i++) {
                names.add(data.getColumnName(i));
                types.add(data.getColumnTypeName(i));
                isAutos.add(data.isAutoIncrement(i));
            }

            colName.put(tableName, names);
            typeName.put(tableName, types);
            isAuto.put(tableName, isAutos);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.close(rs);
            Utils.close(ps);
        }
    }

    private static void info(String sql, String tableName, Object val) {
        System.out.println("[SQL]信息：");
        System.out.println(sql);
        List<String> names = colName.get(tableName);
        for (int i = 0; i < names.size(); i++) {
            System.out.print(names.get(i) + "：");
            System.out.println(Utils.getMethod(names.get(i), val));
        }
        System.out.println("-----------------------[SQL]信息---------------------");
    }

    /**
     * 插入方法
     * @param conn
     * @param tableName 表名
     * @param val 插入数据的实体类，用于反射getMethod（）
     * @return sql执行后影响的行数
     */
    public static int insert(Connection conn, String tableName, Object val) {
        StringBuffer sb = new StringBuffer();
        String tname = tableName.toUpperCase();
        sb.append("insert into ").append(tableName);
        sb.append("(");
        StringBuffer sb1 = new StringBuffer();
        if (!colName.containsKey(tname)) {
            setDesc(conn, tname);
        }
        List<String> names = colName.get(tname);
        List<Boolean> autos = isAuto.get(tname);

        for (int i = 0; i < names.size(); i++) {
            if (!autos.get(i)) {
                sb.append(names.get(i)).append(",");
                sb1.append("?,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb1.deleteCharAt(sb1.length() - 1);
        sb.append(") values (").append(sb1).append(")");

        //info(sb.toString(), tname, val);

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sb.toString());
            int pos = 1;
            for (int i = 0; i < names.size(); i++) {
                if (!autos.get(i)) {
                    ps.setObject(pos++, Utils.getMethod(names.get(i), val));
                }
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Utils.close(ps);
        }
        return -1;
    }

    /**
     * 删除方法
     * @param conn
     * @param tableName
     * @param where "id=?"
     * @param values ？的值，顺序需与前面where的填写对应起来
     * @return sql执行后影响的行数
     */
    public static int delete(Connection conn, String tableName, String where, List<?> values) {
        StringBuffer sql = new StringBuffer();
        sql.append("delete from ").append(tableName);
        if (null != where && !where.trim().equals("")) {
            sql.append(" where ").append(where);
            //System.out.println("BaseDao_delete:" + sql);

            PreparedStatement ps = null;
            try {
                ps = conn.prepareStatement(sql.toString());
                if (values != null && values.size() != 0) {
                    for (int i = 0; i < values.size(); i++) {
                        ps.setObject(i+1, values.get(i));
                    }
                    //System.out.println("BaseDao_delete:" + ps.toString());//测试
                    return ps.executeUpdate();
                                            }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                Utils.close(ps);
            }
        }
        return -1;
    }

    /**
     * 传入实体类来更新方法，主键自增才能使用改方法
     * @param conn
     * @param tableName
     * @param val 更新的实体类
     * @return
     */
    public static int update(Connection conn, String tableName, Object val) {
        PreparedStatement ps = null;
        StringBuffer sql = new StringBuffer();
        sql.append("update ").append(tableName).append(" set ");
        if (!colName.containsKey(tableName.toUpperCase())) {
            setDesc(conn, tableName.toUpperCase());
        }
        List<String> names = colName.get(tableName.toUpperCase());
        List<Boolean> autos = isAuto.get(tableName.toUpperCase());
        int autoPos = 0;

        //删除空值不拼入ps
        /**
         * 循环中删除数组、列表的值，切记循环的条件是未改动之前的，继续操作就会出错
         */
        for (int i = 0; i < names.size(); i++) {
            if (Utils.getMethod(names.get(i), val) == null) {
                names.remove(i);
            } else if ("".equals(Utils.getMethod(names.get(i), val))) {
                names.remove(i);
            }
        }
        //System.out.println("2---------------------" + names.toString()); //测试
        for (int i = 0; i < names.size(); i++) {
            if (!autos.get(i)) {
                    sql.append(names.get(i)).append("=?,");
            } else {
                autoPos = i;
            }
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(" where ").append(names.get(autoPos)).append("=?");

        //info(sql.toString(), tableName.toUpperCase(), val);

        try {
            ps = conn.prepareStatement(sql.toString());
            //System.out.println("baseDao update测试 ps未赋值：" + ps.toString());//测试
            for (int i = 0; i < names.size(); i++) {
                if (i != autoPos) {

                    ps.setObject(i, Utils.getMethod(names.get(i), val));
                }
            }
            ps.setObject(names.size(), Utils.getMethod(names.get(autoPos), val));
            //System.out.println("baseDao update测试 ps：" + ps.toString());//测试
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 使用sql语句更新
     * @param con
     * @param sql
     * @param val
     * @return
     */
    public static int update(Connection con, String sql, Object[] val) {
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql) ;
            if (val != null) {
                for (int i = 0 ; i < val.length ; i++) {
                    pst.setObject(i+1, val[i]) ;
                }
            }
            return pst.executeUpdate() ;
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        } finally {
            Utils.close(pst) ;
        }
    }

    /**
     * 查找多个方法
     * @param conn
     * @param c
     * @param tableName 表名
     * @param sql
     * @param values
     * @param <T>
     * @return
     */
    public static <T> List<T> search(Connection conn, Class<?> c, String tableName, String sql, List<?> values) {
        List<T> list = new ArrayList<>();
        String tname = tableName.toUpperCase();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            if (values != null && values.size() != 0) {
                for (int i = 0; i < values.size(); i++) {
                    ps.setObject(i + 1, values.get(i));
                }
            }
            rs = ps.executeQuery();
            if (!colName.containsKey(tname)) {
                setDesc(conn, tname);
            }
            List<String> names = colName.get(tname);
            List<String> types = typeName.get(tname);
            while (rs.next()) {
                Object o = Utils.newObject(c);
                for (int i = 0; i < names.size(); i++) {
                    String filedName = names.get(i);
                    String type = types.get(i);
                    //System.out.println("BaseDao:" + filedName + "-" + type); //测试

                    if ("TINYINT".equals(type)) {
                        //System.out.println("测试bool");
                        boolean b = rs.getBoolean(filedName);
                        Utils.setMethod(filedName, o, b);
                    } else if (!"DATE".equals(type)) {
                        Utils.setMethod(filedName, o, rs.getObject(filedName));
                    } else {
                        Object s = rs.getObject(filedName);
                        //Utils.setMethod(filedName, o, s != null ? s.toString() : null);
                        Utils.setMethod(filedName, o, s != null ? s : null);
                    }
                }
                list.add((T)o);
            }
        } catch (SQLException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Utils.close(rs);
            Utils.close(ps);
        }
        return list;
    }


    /**
     * 查找单个
     * @param conn
     * @param c
     * @param tableName
     * @param sql
     * @param values
     * @param <T>
     * @return
     */
    public static <T> T searchObj(Connection conn, Class<?> c, String tableName, String sql, List<?> values) {
        String tname = tableName.toUpperCase();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object o = null;
        try {
            ps = conn.prepareStatement(sql);
            if (values != null && values.size() != 0) {
                for (int i = 0; i < values.size(); i++) {
                    ps.setObject(i + 1, values.get(i));
                }
            }
            rs = ps.executeQuery();
            if (!colName.containsKey(tname)) {
                setDesc(conn, tname);
            }
            List<String> names = colName.get(tname) ;
            List<String> types = typeName.get(tname) ;
            if (rs.next()) {
                o = Utils.newObject(c);
                for (int i = 0; i < names.size(); i++) {
                    String fieldName = names.get(i);
                    String type = types.get(i) ;
                    if (!"DATE".equals(type))
                        Utils.setMethod(fieldName, o, rs.getObject(fieldName)) ;
                    else {
                        Object s = rs.getObject(fieldName) ;
                        Utils.setMethod(fieldName, o, s != null?s.toString():null) ;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            Utils.close(rs);
            Utils.close(ps);
        }
        return (T) o;
    }

    public static void main(String[] args) {

        List<Client> clients = search(Utils.getMysqlCon(), Client.class, "client", "select * from client", null);
        for (Client client : clients) {
            System.out.println(client.getName());
            System.out.println(client.getPhoneNumber());
        }
    }

}
