package gofive.models.bl.calculator.index;


/**
 * Created by coral on 16-4-22.
 */

import gofive.models.db.DBase;

/**
 * 计算指标并直接将指标设置为PO的属性。
 * 约定dataPOs的顺序是从早到晚，即时间
 * 最早的在前，时间最晚的在后。
 */
public interface Calculator {

    void defaultCal(DBase[] data);
    void calculate(DBase[] dataPOs, int... params);
}