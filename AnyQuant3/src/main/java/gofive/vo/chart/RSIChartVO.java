package gofive.vo.chart;

/**
 * Created by xu on 2016/5/14.
 */
public class RSIChartVO {
    public double rsi6,rsi12;
    public String date;
    public RSIChartVO(double rsi6, double rsi12,String date){
        this.rsi6 = rsi6;
        this.rsi12 = rsi12;
        this.date = date;
    }

//    public RSIChartVO(double rsi14, String date){
//        this.rsi14 = rsi14;
//        this.date = date;
//    }

}
