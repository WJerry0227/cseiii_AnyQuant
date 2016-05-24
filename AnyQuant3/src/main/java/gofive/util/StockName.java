package gofive.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 获取股票名称的工具类
 * Created by mist on 16-3-1.
 */
public class StockName {
    private static StockName name;

    private  HashMap<String, String> stockNameList = null;

    /**
     * 获取股票名称。
     *
     * @param id 需要获取名称的股票编号。格式为6位数字。
     * @return 股票名称字符串。 如果不存在则返回null
     */
    public   String getName(String id) {
        if (name == null) {name = new StockName();}

        if (id.length() == 8) {
            id = id.substring(2);
        }
        String name = stockNameList.get(id);
        if (name == null) {
            System.err.println("Stock's id not found " + id);
        }
        return name;
    }

    private StockName(){
        loadNameList();
    }

    public static StockName getInstance(){
        if (name == null)
            name = new StockName();
        return name;
    }
    /**
     * 从stock-name.txt中加载股票名称列表
     */
    private  void loadNameList() {
    	//System.out.println(path);
        if (stockNameList != null) return;
        stockNameList = new HashMap<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new BufferedInputStream(new FileInputStream("stock_name.txt")));
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                String[] cp = line.split("[-]");
                stockNameList.put(cp[0], cp[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
