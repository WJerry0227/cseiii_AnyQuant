package gofive.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 利用opencode向AnyQuant.com发送GET请求并返回json格式字符串。
 */
public class AnyQuantHttpRequest {

    static String openCode = "8227bd36700b9f9e98d3bc63611e9688";

    public static String get(String url) {

        String result = "";//访问返回结果

        BufferedReader read = null;//读取访问结果
        try {
            //创建url
            URL realurl = new URL(url);
            //打开连接
            URLConnection connection = realurl.openConnection();
            //增加一个header
            connection.setRequestProperty("X-Auth-Code", openCode);
            connection.setReadTimeout(60000);
            //建立连接
            connection.connect();
            //定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (SocketException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException | MalformedURLException e) {
            e.printStackTrace();
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

