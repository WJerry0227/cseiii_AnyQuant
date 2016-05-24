package gofive.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by coral on 2016/3/30 0030.
 */
public class HttpRequest {

    public static String get(String url) {
        String result = "";//访问返回结果

        BufferedReader read = null;//读取访问结果

        try {
            //创建url
            URL realurl = new URL(url);
            //打开连接
            URLConnection connection = realurl.openConnection();
            //建立连接
            connection.connect();
            //定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (read != null) {//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
