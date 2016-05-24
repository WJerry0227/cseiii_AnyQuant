package gofive.models.bl.calculator.index;

import gofive.models.db.DBase;
import gofive.models.db.StockEntry;
import gofive.models.db.StockInfo;

/**
 * Created by coral on 16-5-12.
 */
public class MACDCalculator implements Calculator {
    @Override
    public void defaultCal(DBase[] data) {
        calculate(data, 12, 26, 9);
    }

    @Override
    public void calculate(DBase[] dataPOs, int... params) {
        if (params.length < 3) {
            throw new IllegalArgumentException("MACD calculator takes exactly 3 arguments");
        }
        int fastperiod = params[0], slowperiod = params[1], signalperiod = params[2];

        EmaCalculator emaCal = new EmaCalculator();
        emaCal.calculate(dataPOs, fastperiod);
        emaCal.calculate(dataPOs, slowperiod);
        for (int i = 1; i < dataPOs.length; i++) {
            double emas = Double.valueOf(dataPOs[i].get("EMA" + slowperiod).toString());
            double emaf = Double.valueOf(dataPOs[i].get("EMA" + fastperiod).toString());
            double macd = emaf - emas;
            dataPOs[i].setValue("MACD", macd);
        }
        emaCal.calculate(dataPOs, "MACD", "MACDsignal", signalperiod);
        for (int i = 1; i < dataPOs.length; i++) {
            double macd = Double.valueOf(dataPOs[i].get("MACD").toString());
            double macdsignal = Double.valueOf(dataPOs[i].get("MACDsignal").toString());
            double macdhist = macd - macdsignal;
            dataPOs[i].setValue("MACDhist", macdhist);
        }
    }

    public static void main(String[] args) {
        /*DBase[] stocks = StockEntry.query().where("id>1021");
        for (DBase stock:stocks) {
            System.out.println(((StockEntry) stock).stockID);
            DBAdmin.addColumn("stockinfo" + ((StockEntry) stock).stockID, "K9", "double");
            DBAdmin.addColumn("stockinfo" + ((StockEntry) stock).stockID, "D9", "double");
            DBAdmin.addColumn("stockinfo" + ((StockEntry) stock).stockID, "J9", "double");
            DBAdmin.addColumn("stockinfo" + ((StockEntry) stock).stockID, "BOLL_M", "double");
            DBAdmin.addColumn("stockinfo" + ((StockEntry) stock).stockID, "BOLL_U", "double");
            DBAdmin.addColumn("stockinfo" + ((StockEntry) stock).stockID, "BOLL_D", "double");
            DBAdmin.addColumn("stockinfo" + ((StockEntry) stock).stockID, "RSI14", "double");
            DBAdmin.addColumn("stockinfo" + ((StockEntry) stock).stockID, "WR14", "double");
        }*/

        DBase[] stocks = StockEntry.query().where("id>1119");

        for (DBase stock:stocks) {
            System.out.println(((StockEntry) stock).stockID);
            DBase[] result = StockInfo.query(((StockEntry) stock).stockID).where("volume > 0");
            new MACDCalculator().defaultCal(result);
            System.out.print("macd ");
            new BollCalculator().defaultCal(result);
            System.out.print("boll ");
            new KDJCalculator().defaultCal(result);
            System.out.print("kdj ");
            new RSICalculator().defaultCal(result);
            System.out.print("rsi ");
            new WRCalculator().defaultCal(result);
            System.out.print("w&r ");
            for (DBase db : result) {
                db.save();
            }
        }
    }
}
