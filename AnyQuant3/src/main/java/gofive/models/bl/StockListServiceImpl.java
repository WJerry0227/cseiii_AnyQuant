package gofive.models.bl;

import gofive.models.bl.consts.Industry;
import gofive.service.StockListService;
import gofive.vo.IndustryVO;
import gofive.vo.RankingChartVO;
import gofive.vo.StockVO;
import gofive.vo.chart.IndustryChartVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by xu on 2016/5/18.
 */
public class StockListServiceImpl implements StockListService {

    public static void main(String[] args) {
        StockListService stockListService = new StockListServiceImpl();
    }
    HashMap<Industry , StockClass> stockClasses;
    Industry[] industries = Industry.values();
    public StockListServiceImpl(){
        stockClasses = new HashMap<>();
        for (int i = 0 ; i < industries.length; i++){
            StockClass stockClass = new StockClass(industries[i]);
//            System.out.println("industry:" + stockClass.getIndustryName() + " done");
        }
    }
    @Override
    public StockVO[] getStockList() {
        ArrayList<StockVO> stockVOs = new ArrayList<>();
        Iterator i = stockClasses.entrySet().iterator();
        while (i.hasNext()){
            Map.Entry entry = (Map.Entry) i.next();
            StockClass c = (StockClass) entry;
            StockVO[] s = c.getStockList();
            for(int j = 0 ; j < s.length; j ++){
                stockVOs.add(s[j]);
            }
        }
        return stockVOs.toArray(new StockVO[stockVOs.size()]);
    }

    @Override
    public StockVO[] searchStockList(String keyword) {
        return new StockVO[0];
    }

    @Override
    public RankingChartVO getRankingChart() {
        return null;
    }

    @Override
    public IndustryChartVO[] getIndustryChart() {
        return new IndustryChartVO[0];
    }

    @Override
    public IndustryVO[] getIndusty() {
        return new IndustryVO[0];
    }
}
