package gofive.util;
import gofive.models.bl.consts.Industry;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 从文件中读取股票分类列表
 * Created by coral on 2016/3/26 0026.
 */
public class StockClassLoader {

    static HashMap<String, ArrayList<String>> stockClass = null;

    void load() {
        if (stockClass != null) {
            return;
        }
        stockClass = new HashMap<>();
        Scanner scanner = null;
        BufferedInputStream bi = null;
        FileInputStream fi = null;
        try {
            fi = new FileInputStream("stock_industries.txt");
            bi = new BufferedInputStream(fi);
            scanner = new Scanner(bi);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            stockClass = null;
            return;
        }

        String stockCls = "";

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.startsWith("*") && !line.startsWith("**")) {
                stockCls = line.substring(1);
            } else {
                if (!line.startsWith("**")) {
                    stockClass.putIfAbsent(stockCls, new ArrayList<>());
                    stockClass.get(stockCls).add(line.trim());
                }
            }
        }

        try {
            fi.close();
            bi.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] getClasses() {
        String[] cls = new String[stockClass.keySet().toArray().length];
        for (int i = 0; i < cls.length; i++) {
            cls[i] = (String) stockClass.keySet().toArray()[i];
        }
        return cls;
    }

    public String[] getStocksByIndustry(Industry industry) {
        return getStocksByIndustry(getClassName(industry));
    }

    public String[] getStocksByIndustry(String industry) {
        load();
        String[] stocks = null;
        ArrayList<String> arrStocks = stockClass.get(industry);
        if (arrStocks == null) {
            throw new NoSuchElementException("No stock class named \"" + industry + "\"");
        }
        stocks = new String[arrStocks.size()];
        for (int i = 0; i < stocks.length; i++) {
            stocks[i] = arrStocks.get(i);
        }
        return stocks;
    }

    public static String getClassName(Industry industry) {
        switch (industry) {
            case WEISHENG:
                return "卫生和社会工作(Q)";
            case PIFALINGSHOU:
                return "批发和零售业(F)";
            case ZHUSUCANYIN:
                return "住宿和餐饮业(H)";
            case DIANLIRELI:
                return "电力、热力、燃气及水生产和供应业(D)";
            case JIAOYU:
                return "教育(P)";
            case NONGLINMUYU:
                return "农、林、牧、渔业(A)";
            case JINRONG:
                return "金融业(J)";
            case JIANZHU:
                return "建筑业(E)";
            case JIAOTONGYUNSHU:
                return "交通运输、仓储和邮政业(G)";
            case CAIKUANG:
                return "采矿业(B)";
            case WENHUATIYUYULE:
                return "文化、体育和娱乐业(R)";
            case SHUILIHUANJING:
                return "水利、环境和公共设施管理业(N)";
            case ZONGHE:
                return "综合(S)";
            case FANGDICHAN:
                return "房地产业(K)";
            case ZULINGSHANGWUFUWU:
                return "租赁和商务服务业(L)";
            case ZHIZAO:
                return "制造业(C)";
            case XINXICHUANSHU:
                return "信息传输、软件和信息技术服务业(I)";
            case KEXUEYANJIUJISHU:
                return "科学研究和技术服务业(M)";
        }
        return "";
    }
}
