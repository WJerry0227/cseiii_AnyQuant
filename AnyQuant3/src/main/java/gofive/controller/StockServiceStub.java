package gofive.controller;

import gofive.service.StockService;
import gofive.vo.IndicatorVO;
import gofive.vo.StockVO;
import gofive.vo.chart.BOLLChartVO;
import gofive.vo.chart.DataList;
import gofive.vo.chart.KDJChartVO;
import gofive.vo.chart.KLineChartVO;
import gofive.vo.chart.MACDChartVO;
import gofive.vo.chart.RSIChartVO;
import gofive.vo.chart.WRChartVO;

public class StockServiceStub implements StockService {

	@Override
	public StockVO getTodayStockVO(String id) {
		// TODO Auto-generated method stub
		StockVO grailVO  = new StockVO(id, id, id, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		return grailVO;
	}

	@Override
	public StockVO[] getStock(String id, String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MACDChartVO[] getMACDChart(String id, String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KDJChartVO[] getKDJChartVO(String id, String start, String end) {
		// TODO Auto-generated method stub
		KDJChartVO singleVO1 = new KDJChartVO(1,2,4,"");
		KDJChartVO singleVO2 = new KDJChartVO(1,2,4,"");
		KDJChartVO[] dataArray = new KDJChartVO[2];
		dataArray[0] = singleVO1;
		dataArray[1] = singleVO2;
		return dataArray;
	}

	@Override
	public RSIChartVO[] getRSIChartVO(String id, String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WRChartVO[] getWRChartVO(String id, String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BOLLChartVO[] getBOLLChartVO(String id, String start, String end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KLineChartVO[] getKLineChartVO(String id, String start, String end) {
		// TODO Auto-generated method stub
		KLineChartVO singleVO = new KLineChartVO(end, 0, 0, 0, 0, 0, 0);
		KLineChartVO singleVO2 = new KLineChartVO(start, 1,1,1,1,1,1);
		KLineChartVO[] dataArray = new KLineChartVO[2];
		dataArray[0] = singleVO;
		dataArray[1] = singleVO2;
		return dataArray;
	}

	@Override
	public IndicatorVO[] getStatisticsConclusion(String id, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataList[] getSwingList(String id, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataList[] getVolumeList(String id, String startTime, String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
