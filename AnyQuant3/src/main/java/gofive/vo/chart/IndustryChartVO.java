package gofive.vo.chart;

public class IndustryChartVO {

	public String name;			//行业名称

	public String increase_day;	//行业一天涨幅

	public String increase_week;//行业一周涨幅平均值


	public IndustryChartVO(String name, String increase_day, String increase_week) {
		super();
		this.name = name;
		this.increase_day = increase_day;
		this.increase_week = increase_week;
	}


}
