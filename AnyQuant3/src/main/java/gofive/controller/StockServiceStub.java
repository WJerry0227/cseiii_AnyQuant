package gofive.controller;

import gofive.service.StockService;
import gofive.vo.IndicatorVO;
import gofive.vo.StockVO;
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
		return null;
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
