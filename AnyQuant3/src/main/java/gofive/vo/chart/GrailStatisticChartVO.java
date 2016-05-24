package gofive.vo.chart;

/**
 * @author 这菜咸了
 * 大盘总体数据分析图VO（浩爷画的那个）
 */
public class GrailStatisticChartVO {
	public String[] xAxis;

	public double[] number;

	public GrailStatisticChartVO(String[] xAxis, double[] number) {
		super();
		this.xAxis = xAxis;
		this.number = number;
	}

}
