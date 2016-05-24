package gofive.models.bl;
import gofive.models.bl.consts.Industry;
import gofive.models.bl.indicator.Indicator;
import gofive.util.StockClassLoader;
import gofive.vo.StockVO;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 股票类别（领域模型）
 * 持有某一类型全部股票代码
 *
 * Created by xu on 2016/3/29.
 */
public class StockClass {
    Industry industry;              //类别
    String industryName;            //类别名
    String[] list;                  //当前类下全部股票id 列表
    HashMap<String,Stock> stockInfo;//当前类下全部股票具体信息，key = id , value = datapo(默认两天)

    public static void main(String[] args) {
        StockClass sc = new StockClass(Industry.CAIKUANG);
        System.out.println("avg:" + sc.getAvgUp(5));
    }

    public StockVO[] getStockList() {
        ArrayList<StockVO> stockVOs = new ArrayList<>();
        Iterator i = stockInfo.entrySet().iterator();
        while (i.hasNext()){
            Map.Entry entry = (Map.Entry) i.next();
            Stock stock = (Stock) entry;
            stockVOs.add(stock.getTodayStockVO());
        }
        return stockVOs.toArray(new StockVO[stockVOs.size()]);
    }

    /**
     * 鉴别参数id是否属于本类型
     *
     * @param id
     * @return boolean
     */
    public boolean isExist(String id){
        for(String i : list){
            if(i.equals(id)){
                return true;
            }
        }
        return false;
    }

    /**
     * 计算当前行业平均涨幅（收盘价close）
     * 1日
     * 1周
     * @return 平均涨幅
     */
    public double getAvgUp(){
        double all = 0;
        int num = 0;
        Set set = stockInfo.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()){
            Map.Entry entry = (Map.Entry) i.next();
            Stock s = (Stock) entry;
            double a = s.getAvg(1);
            if (a != -1){
                all += s.getAvg(1);
                num += 1;
            }

        }
        return all / num;
    }
    public double getAvgUp(int x){
        double all = 0;
        int num = 0;
        Set set = stockInfo.entrySet();
        Iterator i = set.iterator();
        while (i.hasNext()){
            Map.Entry entry = (Map.Entry) i.next();
            Stock s = (Stock) entry.getValue();
            double a = s.getAvg(5);
            if (a != -1){
                all += s.getAvg(5);
                num += 1;
            }

        }
        return all / num;
    }

    /**
     * 获取本类全部股票数据
     */
    private void getStocks(){//from dataservice
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        System.out.println(list.length);
        for (int i = 0 ; i < list.length; i++){
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    Stock stock = new Stock(list[index],30);
                    stockInfo.put(list[index],stock);
                }
            });
        }
        fixedThreadPool.shutdown();
        while (true){
            if (fixedThreadPool.isTerminated()){
                System.out.println("done!");
//                fixedThreadPool.shutdown();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        for (int i = 0 ; i < list.length; i ++){
//            System.out.println("stock: " + i);
//            Stock stock = new Stock(list[i]);
//            stockInfo.put(list[i],stock);
//        }
    }

    public Industry getIndustry(){
        return industry;
    }

    public String getIndustryName(){return industryName;}

    public int getStockNum(){
        return stockInfo.size();
    }

    private void getList(){//from dataservice
        StockClassLoader stockClassLoader = new StockClassLoader();
        list = stockClassLoader.getStocksByIndustry(industry);
    }

    public StockClass(Industry industry){
        this.industry = industry;
        industryName = StockClassLoader.getClassName(industry);
        System.out.println("industry:" + industryName);
        stockInfo = new HashMap<>();
        getList();
        getStocks();
    }
}
