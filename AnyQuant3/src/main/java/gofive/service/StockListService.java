package gofive.service;

import gofive.vo.IndustryVO;
import gofive.vo.RankingChartVO;
import gofive.vo.StockVO;
import gofive.vo.chart.IndustryChartVO;

/**
 *
 * 股票列表模型
 * Created by xu on 2016/5/14.
 */
public interface StockListService {
    /**
     * 获取当天全部股票列表
     * @return
     */
    public StockVO[] getStockList();

    /**
     * 通过关键词查询出符合条件的当天stockList
     * @param keyword 关键词
     * @return
     */
    public StockVO[] searchStockList(String keyword);

    /**
     * 获取当天股票排行列表
     * @return 每个排行只需要十只股票
     */
    public RankingChartVO getRankingChart();

    /**
     * 获取行业图表
     * @return
     */
    public IndustryChartVO[] getIndustryChart();

    /**
     * 获取行业列表信息
     * @return
     */
    public IndustryVO[] getIndusty();
}
