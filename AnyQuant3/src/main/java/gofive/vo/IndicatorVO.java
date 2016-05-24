package gofive.vo;

/**
 * Created by xu on 2016/5/22.
 */
public class IndicatorVO {
    public String indicator;        //指标名称
    public String name;             //简短描述
    public String desc;             //详细描述
    public IndicatorVO(String indicator,String name,String desc){
        this.indicator = indicator;
        this.name = name;
        this.desc = desc;
    }
}
