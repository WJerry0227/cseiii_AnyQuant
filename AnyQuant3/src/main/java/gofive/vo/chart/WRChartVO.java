package gofive.vo.chart;

/**
 * Created by xu on 2016/5/14.
 */
public class WRChartVO {
    public double n1,n2,n3;
    public String date;

    /**
     * 改进版 W&R
     * @param n1 13 DAY
     * @param n2 34 DAY
     * @param n3 89 DAY
     * @param date
     */
    public WRChartVO(double n1,double n2 , double n3,String date){
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.date = date;
    }

    /**
     * 默认版 W&R
     * @param n 14 DAY
     * @param date
     */
    public WRChartVO(double n,String date){
        this.n1 = n;
        this.date = date;
    }
}
