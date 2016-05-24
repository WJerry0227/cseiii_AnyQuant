package gofive.vo;

public class IndustryVO {

	public String industryName;

	public double increase;

	public String best;			//领涨股（涨幅最高）

	public String worst;		//领跌股

	public double flow_in;		//净流入

	public double flow_out;		//净流出

	public IndustryVO(String industryName, double increase, String best, String worst, double flow_in,
			double flow_out) {
		super();
		this.industryName = industryName;
		this.increase = increase;
		this.best = best;
		this.worst = worst;
		this.flow_in = flow_in;
		this.flow_out = flow_out;
	}


}
