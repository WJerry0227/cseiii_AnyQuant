package gofive.vo;

public class ConclusionVO {
	public String date;				//日期

	public String indexConclusion;	//统计指标所展示的结论

	public String completeConclusion;//总体分析的结论

	public ConclusionVO(String date, String indexConclusion, String completeConclusion) {
		super();
		this.date = date;
		this.indexConclusion = indexConclusion;
		this.completeConclusion = completeConclusion;
	}



}
