package gofive.controller;

import gofive.service.StockListService;
import gofive.vo.IndustryVO;
import gofive.vo.RankingChartVO;
import gofive.vo.StockVO;
import gofive.vo.chart.IndustryChartVO;

public class StockListServiceStub implements StockListService {

	@Override
	public StockVO[] getStockList() {
		// TODO Auto-generated method stub
		StockVO[] volist = new StockVO[3];
		StockVO vo = new StockVO("123123", "1123123", "123123", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		volist[1] = vo;
		volist[2] = vo;
		volist[0] = vo;
		return volist;
	}

	@Override
	public StockVO[] searchStockList(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RankingChartVO getRankingChart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndustryChartVO[] getIndustryChart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IndustryVO[] getIndusty() {
		// TODO Auto-generated method stub
		return null;
	}

}
