package gofive.vo.chart;

/**
 * Created by xu on 2016/5/14.
 */
public class BOLLChartVO {
    public double up , mb , down;
    public String date;
    public BOLLChartVO(double up, double mb , double down, String date){
        this.up = up;
        this.mb = mb;
        this.down = down;
        this.date = date;
    }
}
