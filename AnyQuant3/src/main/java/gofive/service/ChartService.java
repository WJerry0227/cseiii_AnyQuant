package gofive.service;

import gofive.vo.ConclusionVO;
import gofive.vo.chart.DataList;

public interface ChartService {

	
	/**
	 * 获取某只股票的总结信息，包括指标的信息和最终总结信息
	 * @param id	股票ID
	 * @param date	需要获得的天数
	 * @return
	 */
	public ConclusionVO getStatisticsConclusion(String id,String date);
	
	/**
	 * 获取振幅的图表信息
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public DataList[] getSwingList(String id,String startTime,String endTime);
	
	/**
	 * 获取成交量的图表信息
	 * @param id
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public DataList[] getVolumeList(String id,String startTime,String endTime);
	
}
