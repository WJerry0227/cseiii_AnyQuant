package gofive.models.bl.consts;

import java.util.HashMap;

/**
 * 股价的指数特征。
 *
 * Created by coral on 16-5-16.
 */
public enum IndexFeature {

    BOLL_UP_CROSS_LOW,          // 股价向上穿越下轨线，买入
    BOLL_UP_CROSS_MD,           // 股价向上穿越中轨，增持
    BOLL_FLUC_BETWEEN_MD_UP,    // 股价在中轨和上轨之间波动，持股观望
    BOLL_DOWN_CROSS_MIDDLE_AFTER_FLUC,  // 股价在中上轨中长时间运行后向下突破中轨，卖出
    BOLL_DOWN_CROSS_UP,         // 股价突破上轨后，再次跌破上轨，卖出
    KDJ_EXTRA_SELL,             // KDJ超卖
    KDJ_EXTRA_BUY,              // KDJ超买
    KDJ_GOLDEN_CROSS,           // KDJ金叉
    KDJ_DEATH_CROSS,            // KDJ死叉
    KDJ_SWING,                  // KDJ徘徊
    WR_UP,                      // W&R上升
    WR_DOWN,                    // W&R下降
    WR_SUPER_BUY,               // W&R超买
    WR_SUPER_SELL,              // W&R超卖
    WR_STAY,                    // W&R稳定
    RSI_SUPER_SELL,             // RSI超卖
    RSI_SUPER_BUY,              // RSI超买
    RSI_ADD,                    // RSI强势
    RSI_STAY,                   // RSI稳定
    RSI_GOLDEN_CROSS,           // RSI低位金叉
    RSI_DEAD_CROSS;             // RSI高位死叉

    private static HashMap<IndexFeature, String> desc = new HashMap<>();
    private static HashMap<IndexFeature, String> name = new HashMap<>();

    static {
        desc.put(BOLL_UP_CROSS_MD, "股价由下向上穿过中轨线，建议加仓买进。");
        desc.put(BOLL_UP_CROSS_LOW, "股价由下向上穿过下轨线，建议加仓买进。");
        desc.put(BOLL_FLUC_BETWEEN_MD_UP, "股价在中轨和上轨之间波动，为多头市场，应该持股观望。");
        desc.put(BOLL_DOWN_CROSS_MIDDLE_AFTER_FLUC, "股价在中轨与上轨之间长期运行，然后向下突破中轨，此时应该卖出。");
        desc.put(BOLL_DOWN_CROSS_UP, "股价向上强烈突破上轨线，由上轨外跌落上轨，注意短线高抛了结，应该卖出。");

        desc.put(KDJ_EXTRA_BUY, "k d j值均大于80, 此时处于超买区，建议卖出股票。");
        desc.put(KDJ_EXTRA_SELL, "k d j值均小于20，此时处于超卖区，建议买入股票");
        desc.put(KDJ_GOLDEN_CROSS, "一段时间内k d j处于50以下或在50附近且股价稳定，j线，k线同时向上击穿d线，适合买入");
        desc.put(KDJ_SWING,"短期内k d j均在20至80范围内徘徊，建议持仓观望");
        desc.put(KDJ_DEATH_CROSS, "股价短期内上升，k d j均在80以上，kj同时向下击穿d线,适合卖出");

        desc.put(WR_UP,"短期内W&R指数呈上升趋势，建议谨慎参与短线操作");
        desc.put(WR_DOWN,"短期内股价成下跌趋势，建议减少短线操作");
        desc.put(WR_STAY,"W&R指数较平稳，建议持仓观望");
        desc.put(WR_SUPER_BUY,"W&R指数低于20，可能超买见顶，建议卖出");
        desc.put(WR_SUPER_SELL,"W&R指数高于80，可能超卖见底，建议买入");

        desc.put(RSI_SUPER_BUY,"6日RSI小于20，超卖见底，建议买入");
        desc.put(RSI_SUPER_SELL,"6日RSI大于80，超卖见顶，建议抛出");
        desc.put(RSI_ADD,"12日RSI保持在50-80之间，形势较好，建议加仓买入");
        desc.put(RSI_STAY,"12日RSI在20—50之间波动，形势较弱，建议持仓观望");
        desc.put(RSI_GOLDEN_CROSS,"6日RSI在低位由下向上穿越12日RSI，形成低位金叉，这是买入信号");
        desc.put(RSI_DEAD_CROSS,"6日RSI在高位由上向下穿越12日RSI，形成高位死叉，这是卖出信号");
    }

    static {
        name.put(BOLL_UP_CROSS_LOW, "上穿boll下轨");
        name.put(BOLL_UP_CROSS_MD, "上穿boll中轨");
        name.put(BOLL_FLUC_BETWEEN_MD_UP, "浮动于boll中上轨");
        name.put(BOLL_DOWN_CROSS_MIDDLE_AFTER_FLUC, "长期浮动后跌破boll中轨");
        name.put(BOLL_DOWN_CROSS_UP, "激涨后跌破boll下轨");

        name.put(KDJ_EXTRA_BUY, "kdj超买");
        name.put(KDJ_EXTRA_SELL, "kdj超卖");
        name.put(KDJ_GOLDEN_CROSS, "kdj金叉");
        name.put(KDJ_DEATH_CROSS, "kdj死叉");
        name.put(KDJ_SWING, "kdj徘徊");

        name.put(WR_UP,"W&R上涨");
        name.put(WR_DOWN,"W&R下降");
        name.put(WR_SUPER_BUY,"W&R超买");
        name.put(WR_SUPER_SELL,"W&R超卖");
        name.put(WR_STAY,"W&R稳定");

        name.put(RSI_SUPER_BUY,"RSI超买");
        name.put(RSI_SUPER_SELL,"RSI超卖");
        name.put(RSI_ADD,"RSI强势");
        name.put(RSI_STAY,"RSI稳定");
        name.put(RSI_GOLDEN_CROSS,"RSI低位金叉");
        name.put(RSI_DEAD_CROSS,"RSI高位死叉");
    }

    public String getDescription() {
        return desc.get(this);
    }
    public String getName() {
        return name.get(this);
    }
}
