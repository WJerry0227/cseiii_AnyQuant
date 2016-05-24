package gofive.vo.chart;

/**
 * Created by xu on 2016/5/14.
 */
public class KDJChartVO {
    public double k,d,j;
    public String date;
    public KDJChartVO(double k , double d , double j, String date){
        this.k = k;
        this.d = d;
        this.j = j;
        this.date = date;
    }
}
