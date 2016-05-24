package gofive.models.bl.indicator;

import gofive.models.bl.consts.IndexFeature;
import gofive.models.bl.consts.Industry;
import gofive.models.bl.consts.Reliability;
import org.omg.CORBA.DATA_CONVERSION;

import java.util.ArrayList;

/**
 * Created by xu on 2016/5/14.
 */
public class MACD extends Indicator {
    double[] macd;
    double[] macdSignal;
    double[] macdHist;
    double[] ema12;
    double[] ema26;
    public MACD(double[] MACD,double[] MACDsignal,double[] MACDhist,double[] ema12,double[] ema26){
        this.macd = MACD;
        this.macdSignal = MACDsignal;
        this.macdHist = MACDhist;
        this.ema12 = ema12;
        this.ema26 = ema26;
    }

    public void setData(double[] MACD,double[] MACDsignal,double[] MACDhist,double[] ema12,double[] ema26){
        this.macd = MACD;
        this.macdSignal = MACDsignal;
        this.macdHist = MACDhist;
        this.ema12 = ema12;
        this.ema26 = ema26;
    }


    @Override
    public IndexFeature[] analysis() {
        return null;
    }

    @Override
    public int forecast() {
        return 0;
    }

    @Override
    public Reliability getReliability() {
        return null;
    }
}
