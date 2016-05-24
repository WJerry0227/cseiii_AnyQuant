package gofive.vo.chart;

/**
 * @author 这菜咸了
 * 大盘K线图VO
 */
public class KLineChartVO {

	public double increase;		//涨幅

	public double volumeValue;	//成交额

	public String date;			//日期

	public double open;			//开盘

	public double close;		//收盘

	public double high;			//最高

	public double low;			//最低


	public KLineChartVO(String date, double open, double close, double high, double low, double increase, double volumeValue) {
		super();
		this.date = date;
		this.open = open;
		this.close = close;
		this.high = high;
		this.low = low;
		this.increase = increase;
		this.volumeValue = volumeValue;
	}
}
