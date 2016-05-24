package gofive.vo;

/**
 * @author 这菜咸了
 * 排名界面所用VO，只需要前十名的股票即可
 */
public class RankingChartVO {
	public String[] increaseRankName;
	public double[] increaseValue;

	public String[] decreaseRankName;
	public double[] decreaseValue;

	/**
	 * 成交量（万）
	 */
	public String[] volumeRankName;
	public double[] volumeValue;

	/**
	 * 资金净流入量（）单位万
	 */
	public String[] flow_InRankName;
	public double[] flow_InValue;
}
