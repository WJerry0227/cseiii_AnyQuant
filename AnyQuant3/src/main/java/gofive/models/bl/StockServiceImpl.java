package gofive.models.bl;

import gofive.service.StockService;
import gofive.vo.IndicatorVO;
import gofive.vo.StockVO;
import gofive.vo.chart.*;

import java.util.HashMap;

/**
 * 个股相关接口实现类
 * Created by xu on 2016/5/14.
 */
public class StockServiceImpl implements StockService{
    Stock stock ;       //持有一支股票的详细数据

//    public StockServiceImpl(){
//        stock = new Stock();
//    }
    @Override
    public StockVO getTodayStockVO(String id) {
        if((stock == null) || (!stock.getId().equals(id))){
            stock = new Stock(id);
        }
        return stock.getTodayStockVO();
    }

    @Override
    public StockVO[] getStock(String id,String start ,String end) {
        if((stock == null) || (!stock.getId().equals(id))){
            stock = new Stock(id);
        }
        return stock.getStock(start,end);
    }

    @Override
    public MACDChartVO[] getMACDChart(String id,String start ,String end) {
        if((stock == null) || (!stock.getId().equals(id))){
            stock = new Stock(id);
        }
        return stock.getMACDChart(start,end);
    }

    @Override
    public KDJChartVO[] getKDJChartVO(String id,String start ,String end) {
        if((stock == null) || (!stock.getId().equals(id))){
            stock = new Stock(id);
        }
        return stock.getKDJChartVO(start,end);
    }

    @Override
    public RSIChartVO[] getRSIChartVO(String id,String start ,String end) {
        if((stock == null) || (!stock.getId().equals(id))){
            stock = new Stock(id);
        }
        return stock.getRSIChartVO(start,end);
    }

    @Override
    public WRChartVO[] getWRChartVO(String id,String start ,String end) {
        if((stock == null) || (!stock.getId().equals(id))){
            stock = new Stock(id);
        }
        return stock.getWRChartVO(start,end);
    }

    @Override
    public BOLLChartVO[] getBOLLChartVO(String id,String start ,String end) {
        if((stock == null) || (!stock.getId().equals(id))){
            stock = new Stock(id);
        }
        return stock.getBOLLChartVO(start,end);
    }

    @Override
    public KLineChartVO[] getKLineChartVO(String id,String start ,String end) {
        if((stock == null) || (!stock.getId().equals(id))){
            stock = new Stock(id);
        }
        return stock.getKLineChartVO(start,end);
    }

    @Override
    public IndicatorVO[] getStatisticsConclusion(String id, String date) {
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
