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
import gofive.vo.chart.*;


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
		StockVO singleVO1  = new StockVO(end, end, end, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		StockVO singleVO2  = new StockVO(end, end, end, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		//StockVO singleVO2  = new StockVO(end, end, end, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		//StockVO singleVO2  = new StockVO(end, end, end, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		StockVO[] dataArray = new StockVO[2];
		dataArray[0] = singleVO1;
		dataArray[1] = singleVO2;
		return dataArray;
	}

	@Override
	public MACDChartVO[] getMACDChart(String id, String start, String end) {
		// TODO Auto-generated method stub
		//String date,double macd, double ema12, double ema26, double diff, double bar
		MACDChartVO singleVO = new MACDChartVO("2016-05-25",23.23,23.2,2,2,2);
		MACDChartVO singleVO2 = new MACDChartVO("2016-05-26",23.26,26.6,6,6,6);
		MACDChartVO[] dataArray = new MACDChartVO[2];
		dataArray[0] = singleVO;
		dataArray[1] = singleVO2;
		return dataArray;
	}

	@Override
	public KDJChartVO[] getKDJChartVO(String id, String start, String end) {
		// TODO Auto-generated method stub
		return null;
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
	public IndicatorVO[] getConclusion(String id, String date, String indicator) {
		return new IndicatorVO[0];
	}

	@Override
	public DataList[] getSwingList(String id, String startTime, String endTime) {
		return new DataList[0];
	}

	@Override
	public DataList[] getVolumeList(String id, String startTime, String endTime) {
		return new DataList[0];
	}

}
