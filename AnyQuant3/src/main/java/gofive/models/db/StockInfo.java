package gofive.models.db;

import gofive.core.DBAdmin;
import gofive.util.AnyQuantHttpRequest;
import gofive.util.ThreadPool;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/**
 * StockInfo 表
 *
 * Created by coral on 16-5-5.
 */
public class StockInfo extends DBase {

    public String stockID = "000000";
    public String date = "";
    public double open = 0.0, close = 0.0, high = 0.0, low = 0.0, volume = 0.0, pe_ttm = 0.0, adj_price = 0.0;
    public double MACD = 0.0, MACDsignal = 0.0, MACDhist = 0.0;
    public double EMA12 = 0.0, EMA26 = 0.0;
    public double K9 = 0.0, D9 = 0.0, J9 = 0.0;
    public double BOLL_M = 0.0, BOLL_U = 0.0, BOLL_D = 0.0;
    public double RSI14 = 0.0;
    public double WR14 = 0.0;

    @Override
    public String getTableName() {
        return "stockinfo" + stockID;
    }

    public static void fetchData() {
        ThreadPool tp = new ThreadPool(30);

        DBase[] stocks = StockEntry.query().where("stockID='603998'");
        for (DBase dBase: stocks) {
            tp.insertTask(() -> {
                StockEntry stock = (StockEntry) dBase;
                DBAdmin.dropTable("StockInfo" + stock.stockID);
                DBAdmin.createTable(StockInfo.query(stock.stockID).getTableName(), StockInfo.class);
                String link = stock.get("link").toString() + "?start=2009-01-01";
                String str = AnyQuantHttpRequest.get(link);
                if (str == null) {
                    System.err.println(link);
                    return;
                }
                JSONTokener jt = new JSONTokener(str);
                JSONObject joStockInfo;
                JSONArray jaInfo = null;
                JSONObject joStockRecord = null;
                try {
                    joStockInfo = (JSONObject) jt.nextValue();
                    jaInfo = joStockInfo.getJSONObject("data").getJSONArray("trading_info");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jaInfo != null) {

                    for (int i = 0; i < jaInfo.length(); i++) {
                        try {
                            joStockRecord = jaInfo.getJSONObject(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            continue;
                        }
                        DBAdmin.execute("insert into stockinfo" + stock.stockID + " set " +
                                "stockID = '" + stock.stockID + "', " +
                                "date = '" + joStockRecord.get("date") + "', " +
                                "open = " + joStockRecord.get("open") + ", " +
                                "close = " + joStockRecord.get("close") + ", " +
                                "high = " + joStockRecord.get("high") + ", " +
                                "low = " + joStockRecord.get("low") + ", " +
                                "volume = " + joStockRecord.get("volume") +  ", " +
                                "pe_ttm = " + joStockRecord.get("pe_ttm") + ", " +
                                "adj_price = " + joStockRecord.get("adj_price") + ";"
                        );
                        joStockRecord = null;
                    }
                    System.out.println("written " + jaInfo.length() + " rows");
                }
            });
        }
    }

    /**
     * 创建可供查询的数据对象。
     *
     * @param stockID 需要执行查询的股票编号
     * @return 能够执行查询功能的数据对象。
     */
    public static StockInfo query(String stockID) {
        StockInfo si = new StockInfo();
        si.stockID = stockID;
        return si;
    }

    public static void main(String[] args) {
        DBase[] result = StockInfo.query("600000").where("date>'2015-01-01'");
        return;
    }
}
