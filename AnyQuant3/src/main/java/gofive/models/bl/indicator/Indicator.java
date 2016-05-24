package gofive.models.bl.indicator;

import gofive.models.bl.consts.IndexFeature;
import gofive.models.bl.consts.Reliability;

import java.util.ArrayList;

/**
 * 指标模型
 * Created by xu on 2016/5/14.
 */
public abstract class Indicator {




    /**
     * 文字类型分析结果
     * @return 描述
     */
    public abstract IndexFeature[] analysis();

    /**
     * 涨跌幅分析结果
     * 涨为正，越大表示涨幅越大
     * 跌为负，越小表示跌幅越大
     * @return
     */
    public abstract int forecast();

    /**
     * 获取可靠程度
     * @return
     */
    public abstract Reliability getReliability();
}
