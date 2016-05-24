package gofive.models.db;

import gofive.models.bl.Stock;
import gofive.util.AnyQuantHttpRequest;
import gofive.util.StockName;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * StockEntry 表
 *
 * Created by coral on 16-5-2.
 */
public class StockEntry extends DBase {

    public String stockID = "000000";
    public String stockName = "";
    public String link = "";


    public static void fetchData(int year, String exchange) {
        String getRes = AnyQuantHttpRequest.get("http://121.41.106.89:8010/api/stocks/?year=" + 2015 + "&exchange=" + exchange);
        if (getRes == null) return;
        JSONTokener jt = new JSONTokener(getRes);
        JSONObject joStockList;
        try {
            joStockList = (JSONObject) jt.nextValue();
            JSONArray ja = joStockList.getJSONArray("data");
            StockName name = StockName.getInstance();
            int jaSize = ja.length();
            for (int i = 0; i < jaSize; ++i) {
                String id = ((String) ja.getJSONObject(i).get("name")).substring(2);
                StockEntry stockEntry = new StockEntry();
                stockEntry.setValue("stockID", id);
                stockEntry.setValue("stockName", name.getName(id));
                stockEntry.setValue("link", ja.getJSONObject(i).get("link"));
                stockEntry.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }
    }

    public static DBase query() {
        return new StockEntry();
    }
}
