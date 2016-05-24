package gofive.models.bl;
import gofive.models.bl.indicator.*;
import gofive.models.db.DBase;
import gofive.models.db.StockInfo;
import gofive.util.StockName;
import gofive.vo.IndicatorVO;
import gofive.vo.StockVO;
import gofive.vo.chart.*;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

/**
 * 个股模型
 * Created by xu on 2016/5/14.
 */
public class Stock {//pass
//    public static void main(String[] args){
//        Stock s = new Stock("600000");
//        StockVO[] stockVO = s.getStock("2015-10-10","2015-12-10");
//        MACDChartVO[] macdChartVOs = s.getMACDChart("2015-10-10","2015-12-10");
//        BOLLChartVO[] bollChartVOs = s.getBOLLChartVO("2015-10-10","2015-12-10");
//        RSIChartVO[] rsiChartVOs = s.getRSIChartVO("2015-10-10","2015-12-10");
//        WRChartVO[] wrChartVOs = s.getWRChartVO("2015-10-10","2015-12-10");
//        KDJChartVO[] kdjChartVOs = s.getKDJChartVO("2015-10-10","2015-12-10");
//        KLineChartVO[] kLineChartVOs = s.getKLineChartVO("2015-10-10","2015-12-10");
//    }

//    public static void main(String[] args) {
//        LocalDate now = LocalDate.now();
//        now.minusDays(10);
//        System.out.println(now.toString());
//    }

//    public static void main(String[] args) {
//        Stock s = new Stock("600121");
//        System.out.println("");
//        Stock s2 = new Stock("000159");
//        System.out.println("");
//    }

    private String id;
    private String name;
    private HashMap<String,Indicator> indicators;   //所有指标 key = IndicatorType
    private DBase[] data;                           //数据
//    private StockInfo table;                        //可供查询的股票数据对象

    /**
     * 获取近期数据
     * @param id 股票编号
     * @param flag 标志位
     */
    public Stock (String id, int flag){
        StockName name = StockName.getInstance();
        this.id = id;
        this.name = name.getName(id);
        LocalDate now = LocalDate.now();
        now.minusDays(10);
        String n = now.toString();
        data = StockInfo.query(id).where("volume > 0 and date > "+n);
    }

    /**
     * 获取股票全部信息
     * @param id 股票编号
     */
    public Stock(String id){
        StockName name = StockName.getInstance();
        this.id = id;
        this.name = name.getName(id);
        data = StockInfo.query(id).where("volume > 0");
        if (data != null){
            System.out.println("get:" + id);
        }
        indicators = new HashMap<>();
    }

    public double getAvg(int day){
        if (day < data.length - 1) return -1;
        double 
    }

    public Stock(){ }

    public String getId(){
        return id;
    }

    private StockVO getStock(int index){
        DBase d = data[index];
        double increaseValue = (double)d.get("close") - (double)data[index - 1].get("close");
        double increase = increaseValue / (double)d.get("close");
        double close = (double)d.get("close");
        double swing =((double)d.get("high") - (double)d.get("low"))/ (double)(data[index - 1].get("close"));
        double open = (double)d.get("open");
        double high = (double)d.get("high");
        double low = (double) d.get("low");
        double volume = (double) d.get("volume");
        double flowIn = volume * (double)d.get("close");
//        double turnover = (double) d.get("turnover");
        double turnover = 0;
        return new StockVO((String) d.get("date"),id,name,increaseValue,increase,swing,close,open,high,low,flowIn,volume,flowIn,turnover);
    }

    public StockVO getTodayStockVO(){
        return getStock(data.length - 1);
    }

    public StockVO[] getStock(String start ,String end){
        int startIndex = getStart(start);
        int endIndex = getEnd(end);
        StockVO[] stockVOs = new StockVO[endIndex - startIndex + 1];
        for(int i = startIndex; i <= endIndex; i ++){
            stockVOs[i - startIndex] = getStock(i);
        }
        return stockVOs;
    }

    public MACDChartVO[] getMACDChart(String start ,String end) {
        int startIndex = getStart(start);
        int endIndex = getEnd(end);
        int num = endIndex - startIndex + 1;
        MACDChartVO[] chart = new MACDChartVO[num];
        double[] MACD = new double[num];
        double[] MACDsignal = new double[num];
        double[] MACDhist = new double[num];
        double[] ema12 = new double[num];
        double[] ema26 = new double[num];
        for(int i = startIndex ; i <= endIndex ;i ++){
            int index = i - startIndex;
            MACD[index] = (double) data[i].get("MACD");
            MACDsignal[index] = (double) data[i].get("MACDsignal");
            MACDhist[index] = (double) data[i].get("MACDhist");
            ema12[index] = (double) data[i].get("EMA12");
            ema26[index] = (double) data[i].get("EMA26");
            chart[index] = new MACDChartVO((String)data[i].get("date"),MACD[index],MACDsignal[index],MACDhist[index],ema12[index],ema26[index]);
        }
        Indicator macd = new MACD(MACD,MACDsignal,MACDhist,ema12,ema26);
        indicators.put("MACD",macd);
        return chart;
    }

    public KDJChartVO[] getKDJChartVO(String start ,String end) {
        int startIndex = getStart(start);
        int endIndex = getEnd(end);
        int num = endIndex - startIndex + 1;
        double[] k = new double[num];
        double[] d = new double[num];
        double[] j = new double[num];
        KDJChartVO[] chart = new KDJChartVO[num];
        for (int i = startIndex; i <= endIndex; i++){
            int index = i - startIndex;
            k[index] = (double) data[i].get("K9");
            d[index] = (double) data[i].get("D9");
            j[index] = (double) data[i].get("J9");
            chart[index] = new KDJChartVO(k[index],d[index],j[index],(String)data[i].get("date"));
        }
        Indicator KDJ = new KDJ(k,d,j);
        indicators.put("KDJ",KDJ);
        return chart;
    }

    public RSIChartVO[] getRSIChartVO(String start ,String end) {
        int startIndex = getStart(start);
        int endIndex = getEnd(end);
        int num = endIndex - startIndex + 1;
        RSIChartVO[] rsiChartVOs = new RSIChartVO[num];
        double[] rsi6 = new double[num];
        double[] rsi12 = new double[num];
        for(int i = startIndex ; i < endIndex; i ++){
//            double r6 = (double) data[i].get("RSI6");
//            double r12 = (double) data[i].get("RSI12");
//            rsi6[i - startIndex] = r6;
//            rsi12[i - startIndex] = r12;
//            rsiChartVOs[i - startIndex] = new RSIChartVO(r6,r12, (String) data[i].get("date"));
        }
//        Indicator r = new RSI(rsi);
//        indicators.put("RSI",r);
        return rsiChartVOs;
    }

    public WRChartVO[] getWRChartVO(String start ,String end) {
        int startIndex = getStart(start);
        int endIndex = getEnd(end);
        int num = endIndex - startIndex + 1;
        WRChartVO[] wrChartVOs = new WRChartVO[num];
        double[] wr = new double[num];
        for(int i = startIndex ; i < endIndex ; i ++){
            double wr14 = (double) data[i].get("WR14");
            wr[i - startIndex] = wr14;
            wrChartVOs[i - startIndex] = new WRChartVO(wr14, (String) data[i].get("date"));
        }
        Indicator w = new WR(wr);
        indicators.put("WR",w);
        return wrChartVOs;
    }

    public BOLLChartVO[] getBOLLChartVO(String start ,String end) {
        int startIndex = getStart(start);
        int endIndex = getEnd(end);
        int num = endIndex - startIndex + 1;
        BOLLChartVO[] boll = new BOLLChartVO[num];
        double[] ups = new double[num];
        double[] mds = new double[num];
        double[] downs = new double[num];
        double[] closes = new double[num];
        for(int i = startIndex ; i <= endIndex; i ++){
            int index = i - startIndex;
            double up = (double) data[i].get("BOLL_U");
            double md = (double) data[i].get("BOLL_M");
            double down = (double) data[i].get("BOLL_D");
            double close = (double) data[i].get("close");
            ups[index] = up;
            mds[index] = md;
            downs[index] = down;
            closes[index] = close;
            boll[index] = new BOLLChartVO(up,md,down,(String)data[i].get("date"));
         }
        Indicator BOLL = new BOLL(ups,mds,downs,closes);
        indicators.put("BOLL",BOLL);
        return boll;
    }

    public KLineChartVO[] getKLineChartVO(String start ,String end) {
        int startIndex = getStart(start);
        if(startIndex == 0) startIndex += 1;
        int endIndex = getEnd(end);
        int num = endIndex - startIndex + 1;
        KLineChartVO[] k = new KLineChartVO[num];
        for (int i = startIndex ; i <= endIndex ;i ++){
            double high = (double) data[i].get("high");
            double low = (double) data[i].get("low");
            double open = (double) data[i].get("open");
            double close = (double) data[i].get("close");
            double increase = (close - (double)data[i - 1].get("close"))/(double)data[i - 1].get("close");
            double value = (double)data[i].get("volume") * close;
            k[i - startIndex] = new KLineChartVO((String)data[i].get("date"),open,close,high,low,increase,value);
        }
        return k;
    }

    private int getStart(String start){//二分法查找
        LocalDate s = LocalDate.parse(start);
        String earliest = (String) data[0].get("date");
        String latest = (String) data[data.length - 1].get("date");
        LocalDate e = LocalDate.parse(earliest);
        LocalDate l = LocalDate.parse(latest);
        if (s.compareTo(e) <= 0 ) return 0;
        else if (s.compareTo(l) > 0) return -1;
        else if (s.compareTo(l) == 0) return data.length - 1;
        else {
            int head = 0;
            int tail = data.length - 1;
            int mid = (head + tail) / 2;
            while (mid != tail && mid != head){
                String mids = (String) data[mid].get("date");
                LocalDate midD = LocalDate.parse(mids);
                if (s.compareTo(midD) > 0){
                    head = mid;
                    mid = (head + tail) / 2;
                }else if (s.compareTo(midD) < 0){
                    tail = mid;
                    mid = (head + tail) / 2;
                }else {
                    return mid;
                }
            }
            return mid + 1;
        }
    }

    private int getEnd (String end){//二分法查找
        LocalDate s = LocalDate.parse(end);
        String earliest = (String) data[0].get("date");
        String latest = (String) data[data.length - 1].get("date");
        LocalDate e = LocalDate.parse(earliest);
        LocalDate l = LocalDate.parse(latest);
        if (s.compareTo(e) < 0 ) return -1;
        else if (s.compareTo(e) == 0) return 0;
        else if (s.compareTo(l) >= 0) return data.length - 1;
        else {
            int head = 0 ;
            int tail = data.length - 1;
            int mid = (head + tail) / 2;
            while (mid != tail && mid != head){
                String mids = (String) data[mid].get("date");
                LocalDate midD = LocalDate.parse(mids);
                if (s.compareTo(midD) > 0){
                    head = mid;
                    mid = (head + tail) / 2;
                }else if (s.compareTo(midD) < 0){
                    tail = mid;
                    mid = (head + tail) / 2;
                }else {
                    return mid;
                }
            }
            return mid - 1;
        }

    }

    public IndicatorVO[] getStatisticsConclusion(String date) {
        //todo this interface is not good
        int end = getStart(date);
        int num = 30;
        double[] ups = new double[num];
        double[] mbs = new double[num];
        double[] downs = new double[num];
        double[] closes = new double[num];

        double[] MACD = new double[num];
        double[] MACDsignal = new double[num];
        double[] MACDhist = new double[num];
        double[] ema12 = new double[num];
        double[] ema26 = new double[num];

        double[] k = new double[num];
        double[] d = new double[num];
        double[] j = new double[num];

        double[] wr = new double[num];

        double[] rsi6 = new double[num];
        double[] rsi12 = new double[num];

        for (int i = end - num; i < end; i++){
            int index = i - (end - num);
            DBase dBase = data[i];

            ups[index] = (double) data[i].get("BOLL_U");
            mbs[index] = (double) data[i].get("BOLL_M");
            downs[index] = (double) data[i].get("BOLL_D");
            closes[index] = (double) data[i].get("close");

            k[index] = (double) data[i].get("K9");
            d[index] = (double) data[i].get("D9");
            j[index] = (double) data[i].get("J9");

            MACD[index] = (double) data[i].get("MACD");
            MACDsignal[index] = (double) data[i].get("MACDsignal");
            MACDhist[index] = (double) data[i].get("MACDhist");
            ema12[index] = (double) data[i].get("EMA12");
            ema26[index] = (double) data[i].get("EMA26");

            rsi6[index] = (double) data[i].get("RSI6");
            rsi12[index] = (double) data[i].get("RSI12");

            wr[index] = (double) data[i].get("WR14");
        }

        Indicator macd = new MACD(MACD,MACDsignal,MACDhist,ema12,ema26);
        Indicator kdj = new KDJ(k,d,j);
        Indicator boll = new BOLL(ups,mbs,downs,closes);
        Indicator rsi = new RSI(rsi6,rsi12);
        Indicator w = new WR(wr);
//        indicators.put("MACD",macd);
//        indicators.put("KDJ",kdj);
//        indicators.put("RSI",rsi);
//        indicators.put("BOLL",boll);
//        indicators.put("WR",w);
        return  null;


    }

    public DataList[] getSwingList(String startTime, String endTime) {
        int start = getStart(startTime);
        int end = getEnd(endTime);
        if (start == 0) start += 1;
        DataList[] list = new DataList[start - end + 1];
        for (int i = start; i <= end; i++){
            DBase d = data[i];
            double swing =((double)d.get("high") - (double)d.get("low"))/ (double)(data[i - 1].get("close"));
            list[i] = new DataList(swing);
        }
        return list;
    }

    public DataList[] getVolumeList(String startTime, String endTime) {
        int start = getStart(startTime);
        int end = getEnd(endTime);
        if (start == 0) start += 1;
        DataList[] list = new DataList[start - end + 1];
        for (int i = start; i <= end; i++){
            DBase d = data[i];
            double swing = (double) d.get("volume");
            list[i] = new DataList(swing);
        }
        return list;
    }
}
