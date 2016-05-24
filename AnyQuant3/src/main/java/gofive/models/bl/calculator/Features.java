//package gofive.models.bl.calculator;
//
//import gofive.models.bl.consts.IndexFeature;
//import gofive.models.db.DBase;
//import gofive.models.db.StockInfo;
//
//import java.util.ArrayList;
//
///**
// * 提取指标特征。
// *
// * Created by coral on 16-5-16.
// */
//public class Features {
//
//    DBase[] data = null;
//
//    public Features(String stockID) {
//        loadData(stockID);
//    }
//
//    public Features(DBase[] data) {
//        this.data = data;
//    }
//
//    public void loadData(String stockID) {
//        data = StockInfo.query(stockID).where("volume > 0");
//    }
//
//    public void loadData(DBase[] data) {
//        this.data = data;
//    }
//
//    public IndexFeature[] pickFeatures() {
//        ArrayList<IndexFeature> result = new ArrayList<>();
//        if (isBollUpCrossLow()) result.add(IndexFeature.BOLL_UP_CROSS_LOW);
//        if (isBollDownCrossMdAfterFluc()) result.add(IndexFeature.BOLL_DOWN_CROSS_MIDDLE_AFTER_FLUC);
//        if (isBollDownCrossUp()) result.add(IndexFeature.BOLL_DOWN_CROSS_UP);
//        if (isBollUpCrossMiddle()) result.add(IndexFeature.BOLL_UP_CROSS_MD);
//        if (isBollFlucBetweenMdUp()) result.add(IndexFeature.BOLL_FLUC_BETWEEN_MD_UP);
//        return result.toArray(new IndexFeature[result.size()]);
//    }
//
//    public boolean isBollUpCrossLow() {
//        boolean result = false;
//        for (int i = data.length - 5; i < data.length; i++) {
//            double yclose = Double.valueOf(data[i - 1].get("close").toString());
//            double yboll_d = Double.valueOf(data[i - 1].get("BOLL_D").toString());
//            double close = Double.valueOf(data[i].get("close").toString());
//            double boll_d = Double.valueOf(data[i].get("BOLL_D").toString());
//            if ((yclose - yboll_d) * (close - boll_d) < 0 && close > boll_d) result = true;
//            else if (close < boll_d) result = false;
//        }
//        return result;
//    }
//
//    public boolean isBollUpCrossMiddle() {
//        boolean result = false;
//        for (int i = data.length - 5; i < data.length; i++) {
//            double yclose = Double.valueOf(data[i - 1].get("close").toString());
//            double yboll_m = Double.valueOf(data[i - 1].get("BOLL_M").toString());
//            double close = Double.valueOf(data[i].get("close").toString());
//            double boll_m = Double.valueOf(data[i].get("BOLL_M").toString());
//            if ((yclose - yboll_m) * (close - boll_m) < 0 && close > boll_m) result = true;
//            else if (close < boll_m) result = false;
//        }
//        return result;
//    }
//
//    public boolean isBollFlucBetweenMdUp() {
//        boolean result = true;
//        for (int i = data.length - 10; i < data.length; ++i) {
//            double close = Double.valueOf(data[i].get("close").toString());
//            double boll_u = Double.valueOf(data[i].get("BOLL_U").toString());
//            double boll_m = Double.valueOf(data[i].get("BOLL_M").toString());
//            if (close < boll_m || close > boll_u) result = false;
//        }
//        return result;
//    }
//
//    public boolean isBollDownCrossMdAfterFluc() {
//        boolean result = true;
//        boolean isBollFluc = true;
//        for (int i = data.length - 10 - 3; i < data.length - 3; ++i) {
//            double close = Double.valueOf(data[i].get("close").toString());
//            double boll_u = Double.valueOf(data[i].get("BOLL_U").toString());
//            double boll_m = Double.valueOf(data[i].get("BOLL_M").toString());
//            if (close < boll_m || close > boll_u) isBollFluc = false;
//        }
//        if (!isBollFluc) return false;
//        for (int i = data.length - 3; i < data.length; ++i) {
//            double yclose = Double.valueOf(data[i - 1].get("close").toString());
//            double yboll_m = Double.valueOf(data[i - 1].get("BOLL_M").toString());
//            double close = Double.valueOf(data[i].get("close").toString());
//            double boll_m = Double.valueOf(data[i].get("BOLL_M").toString());
//            if ((yclose - yboll_m) * (close - boll_m) < 0 && close < boll_m) result = true;
//            else if (close > boll_m) result = false;
//        }
//        return result;
//    }
//
//    public boolean isBollDownCrossUp() {
//        boolean isUpALot = false;
//        boolean result = false;
//        for (int i = data.length - 3 - 5; i < data.length - 3; ++i) {
//            double close = Double.valueOf(data[i].get("close").toString());
//            double boll_u = Double.valueOf(data[i].get("BOLL_U").toString());
//            if (close - boll_u > boll_u * 0.05) isUpALot = true;
//        }
//        if (!isUpALot) return false;
//        for (int i = data.length - 3; i < data.length; ++i) {
//            double yclose = Double.valueOf(data[i - 1].get("close").toString());
//            double yboll_u = Double.valueOf(data[i - 1].get("BOLL_U").toString());
//            double close = Double.valueOf(data[i].get("close").toString());
//            double boll_u = Double.valueOf(data[i].get("BOLL_U").toString());
//            if ((yclose - yboll_u) * (close - boll_u) < 0 && close < boll_u) result = true;
//            else if (close > boll_u) result = false;
//        }
//        return result;
//    }
//
//    public boolean isKdjSuperBuy() {
//        DBase dat = data[data.length - 1];
//        double k9 = Double.valueOf(dat.get("K9").toString());
//        double d9 = Double.valueOf(dat.get("D9").toString());
//        double j9 = Double.valueOf(dat.get("J9").toString());
//        return (k9 > 80 && d9 > 80 && j9 > 80);
//    }
//
//    public boolean isKdjSuperSell() {
//        DBase dat = data[data.length - 1];
//        double k9 = Double.valueOf(dat.get("K9").toString());
//        double d9 = Double.valueOf(dat.get("D9").toString());
//        double j9 = Double.valueOf(dat.get("J9").toString());
//        return (k9 < 20 && d9 < 20 && j9 < 20);
//    }
//}
