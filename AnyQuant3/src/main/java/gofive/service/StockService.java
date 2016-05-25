package gofive.service;

import gofive.vo.*;
import gofive.vo.chart.*;

public interface StockService {

	/**
	 * 得到当天stockVO
	 * @return
	 */
	public StockVO getTodayStockVO(String id);

	/**
	 * 通过stockID得到stock列表
	 * @param id,start,end	 编号，起始时间，终止时间
	 * @return  该stock的历史数据
	 */
	public StockVO[] getStock(String id,String start,String end);

	/**
	 * 获取MACD绘图用数据
	 * @param id 股票编号
	 * @param start 起始日期
	 * @param end 终止日期
	 * @return 数据
	 */
	public MACDChartVO[] getMACDChart(String id,String start,String end);

	/**
	 * 获取KDJ绘图用数据
	 * @param id 股票编号
	 * @param start 起始日期
	 * @param end 终止日期
	 * @return 数据
	 */
	public KDJChartVO[] getKDJChartVO(String id,String start,String end);

	/**
	 * 获取RSI绘图用数据
	 * @param id 股票编号
	 * @param start 起始日期
	 * @param end 终止日期
	 * @return 数据
	 */
	public RSIChartVO[] getRSIChartVO(String id,String start, String end);

	/**
	 * 获取WR绘图用数据
	 * @param id 股票编号
	 * @param start 起始日期
	 * @param end 终止日期
	 * @return 数据
	 */
	public WRChartVO[] getWRChartVO(String id,String start , String end);

	/**
	 * 获取BOLL绘图用数据
	 * @param id 股票编号
	 * @param start 起始日期
	 * @param end 终止日期
	 * @return 数据
	 */
	public BOLLChartVO[] getBOLLChartVO(String id,String start,String end);

	/**
	 * 获取K线图用数据
	 * @param id 股票编号
	 * @param start 起始日期
	 * @param end 终止日期
	 * @return 数据
	 */
	public KLineChartVO[] getKLineChartVO(String id,String start,String end);

	/**
	 * 获取统计指标得出的结论
	 * @param id 股票编号
	 * @param date 日期
	 * @return 结论描述
	 */
	public IndicatorVO[] getConclusion(String id,String date,String indicator);

	/**
	 * 获取绘制振幅图所用数据
	 * @param id 股票编号
	 * @param startTime 起始日期
	 * @param endTime 终止日期
	 * @return 数据
	 */
	public DataList[] getSwingList(String id,String startTime,String endTime);

	/**
	 * 获取绘制成交量所用数据
	 * @param id 股票编号
	 * @param startTime 起始时间
	 * @param endTime 终止时间
	 * @return 数据
	 */
	public DataList[] getVolumeList(String id,String startTime,String endTime);

}
