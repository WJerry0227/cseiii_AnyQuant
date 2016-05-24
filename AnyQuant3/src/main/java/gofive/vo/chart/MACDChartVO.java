package gofive.vo.chart;

public class MACDChartVO {
	double macd, ema12, ema26, diff, bar;
    String date;
    public MACDChartVO(){

    }

    public MACDChartVO( String date,double macd, double ema12, double ema26, double diff, double bar) {
		super();
		this.macd = macd;
		this.ema12 = ema12;
		this.ema26 = ema26;
		this.diff = diff;
		this.bar = bar;
		this.date = date;
	}


}
