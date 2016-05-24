package gofive.models.db;

import gofive.core.DBAdmin;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 数据表基类
 * 提供访问控制、表和域的管理、执行sql语句的能力。
 * Created by coral on 16-5-2.
 */
public abstract class DBase {

    public int id;

    static int lastId = 0;  // 保存主键的最新值。

    public DBase() {
        id = ++lastId;
    }

    public DBase(String tableName) {
        id = ++lastId;
    }

    public DBase[] where(String whereStatment) {
        return where(whereStatment, "");
    }

    /**
     * 执行查询，并将结果包装为对象。
     *
     * @param whereStatment where条件语句
     * @param options 附加的条件语句，将会被直接拼接到where后面
     * @return 查询的结果。
     */
    public DBase[] where(String whereStatment, String options) {
        String query = "select * from " + getTableName();
        if (whereStatment != null && !whereStatment.equals("")) {
            query += " where(" + whereStatment + ")";
        }
        if (options != null && !options.equals("")) {
            query += options + ";";
        }
        ResultSet rs = DBAdmin.query(query);
        ArrayList<DBase> result = new ArrayList<>();
        ArrayList<Field> fields = new ArrayList<>();
        Collections.addAll(fields, getClass().getFields());
        try {
            while (rs.next()) {
                DBase tmp = this.getClass().newInstance();
                for (Field field: getClass().getFields()) {
                    switch (field.getType().getSimpleName()) {
                        case "Integer":
                        case "int":
                            field.setInt(tmp, rs.getInt(field.getName()));
                            break;
                        case "Character":
                        case "char":
                            field.setChar(tmp, (char) rs.getShort(field.getName()));
                            break;
                        case "String":
                            field.set(tmp, rs.getString(field.getName()));
                            break;
                        case "double":
                        case "Double":
                        case "Float":
                        case "float":
                            field.setDouble(tmp, rs.getDouble(field.getName()));
                            break;
                        case "Date":
                            field.set(tmp, rs.getDate(field.getName()));
                    }
                }
                result.add(tmp);
            }
            rs.close();

        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result.toArray(new DBase[result.size()]);
    }

    public void setValue(String key, Object value) {
        Field field = null;
        try {
            field = getClass().getField(key);
            field.set(this, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存当前数据项。如果已经存在于表中，则把原数据覆盖为最新值。
     */
    public void save() {
        ResultSet rs = DBAdmin.query("select * from " + getTableName() + " where (id = " + id + ");");
        try {
            String values = "";
            ArrayList<Field> fields = new ArrayList<>();
            Collections.addAll(fields, this.getClass().getFields());
            for (Field field : fields) {
                if (!values.equals("")) values += ", ";
                switch (field.getType().getSimpleName()) {
                    case "Integer":
                    case "int":
                        values += field.getName() + '=' + field.getInt(this);
                        break;
                    case "Character":
                    case "char":
                        values += field.getName() + '=' + "'" + ((char) field.getShort(this)) + "'";
                        break;
                    case "[C":
                    case "String":
                        values += field.getName() + '=' + "'" + field.get(this) + "'";
                        break;
                    case "double":
                    case "Double":
                    case "Float":
                    case "float":
                        values += field.getName() + '=' + field.getDouble(this);
                        break;
                    case "Date":
                        values += field.getName() + '=' + "'" + field.get(this).toString() + "'";
                }
            }
            if (!rs.next()) {
                DBAdmin.execute("insert into " + getTableName() + " set " + values + ";");
            } else {
                DBAdmin.execute("update " + getTableName() + " set " + values + " where id = " + id + ";");
            }
        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTableName() {
        return this.getClass().getSimpleName();
    }


    public Object get(String key) {
        try {
            Field field = this.getClass().getField(key);
            return field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
