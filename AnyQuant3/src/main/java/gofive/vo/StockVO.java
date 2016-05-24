package gofive.vo;

/**
 * @author 这菜咸了
 * 股票列表VO 获取某天部分或全体股票列表
 */
public class StockVO {

	/**
	 * 日期
	 */
	public String date;

	/**
	 * 股票代码
	 */
	 public String id;

	 /**
	 * 股票名称
	 */
	 public String name;

	 /**
	 * 涨跌额
	 */
	public double increaseValue;

	/**
	 * 涨跌幅
	 */
	public double increase;

	/**
	 * 振幅
	 */
	public double swing;

	/**
	 * 昨日收盘价
	 */
	public double close;

	/**
	 * 今日开盘价
	 */
	public double open;

	/**
	 * 最高价
	 */
	public double high;

	/**
	 * 最低价
	 */
	public double low;

	/**
	 * 资金流入量/万
	 */
	public double flowin;

	/**
	 * 成交量/手
	 */
	public double volume;

	/**
	 * 成交额/万
	 */
	public double volumeValue;

	/**
	 * 换手率
	 */
	public double handchange;

	public StockVO(String date, String id, String name, double increaseValue, double increase, double swing,
			double close, double open, double high, double low, double flowin, double volume, double volumeValue,
			double handchange) {
		super();
		this.date = date;
		this.id = id;
		this.name = name;
		this.increaseValue = increaseValue;
		this.increase = increase;
		this.swing = swing;
		this.close = close;
		this.open = open;
		this.high = high;
		this.low = low;
		this.flowin = flowin;
		this.volume = volume;
		this.volumeValue = volumeValue;
		this.handchange = handchange;
	}




}
