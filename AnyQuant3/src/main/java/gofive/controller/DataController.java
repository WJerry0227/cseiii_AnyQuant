package gofive.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gofive.service.StockListService;
import gofive.service.StockService;
import gofive.vo.StockVO;
import gofive.vo.chart.KDJChartVO;
import gofive.vo.chart.KLineChartVO;
import gofive.vo.chart.MACDChartVO;

@Controller
@RequestMapping("data.do")
public class DataController {
	  private StockListService stockListService = new StockListServiceStub();
	  private StockService stockService = new StockServiceStub();
	
	  @RequestMapping(params="method=stockList",method=RequestMethod.GET)
	  public @ResponseBody StockVO[] stockList() throws Exception{ 
	  List<StockVO> list = new ArrayList<StockVO>();
	  StockVO[] array = stockListService.getStockList();
	  for(int i=0;i<array.length;i++){
		  list.add(array[i]);
	  }	  	   
      return array;      
	    //return list;
	  }
	  
	  @RequestMapping(params="method=graiList",method=RequestMethod.GET)
	  public @ResponseBody StockVO grailToday() throws Exception{ 
	     String id;
	     id = "grail";
	     StockVO grailVO = stockService.getTodayStockVO(id);
	     return grailVO;
	    //return list;
	  }
	  
	  @RequestMapping(params="method=KLineData",method=RequestMethod.GET)
	  public @ResponseBody List<KLineChartVO> KLineData() throws Exception{ 
	     String id = "grail";
	     String start ="2016-03-01";
	     String end = "2016-04-01";
	     
	     KLineChartVO[] dataArray = stockService.getKLineChartVO(id, start, end);
	     List<KLineChartVO> dataList  = new ArrayList<KLineChartVO>();
	     for(int i=0;i<dataArray.length;i++){
	    	 dataList.add(dataArray[i]);
	     }
	     
	     return dataList;
	    //return list;
	  }
	  
	  @RequestMapping(params="method=VolumeData",method=RequestMethod.GET)
	  public @ResponseBody double[] VolumeData() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    
	    StockVO[] dataArray = stockService.getStock(id, start, end);
	    double[] volumeArray  = new double[dataArray.length];
	    for(int i=0;i<dataArray.length;i++){
	    	volumeArray[i] = dataArray[i].volume;
	    }
	     
	     return volumeArray;
	    //return list;
	  }
	  
	  @RequestMapping(params="method=swing",method=RequestMethod.GET)
	  public @ResponseBody double[] swing() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    
	    StockVO[] dataArray = stockService.getStock(id, start, end);
	    double[] swingArray  = new double[dataArray.length];
	    for(int i=0;i<dataArray.length;i++){
	    	swingArray[i] = dataArray[i].swing;
	    }
	     
	     return swingArray;
	    //return list;
	  }
	  
	  //macdData 
	  @RequestMapping(params="method=macdData",method=RequestMethod.GET)
	  public @ResponseBody double[] macdData() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    MACDChartVO[] macdData = stockService.getMACDChart(id, start, end);
	    double[] macdDataArray  = new double[macdData.length];
	    for(int i=0;i<macdDataArray.length;i++){
	    	macdDataArray[i] = macdData[i].macd;
	    }
	     
	     return macdDataArray;
	    //return list;
	  }
	  
	  //ema12Data
	  @RequestMapping(params="method=ema12Data",method=RequestMethod.GET)
	  public @ResponseBody double[] ema12Data() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    MACDChartVO[] macdData = stockService.getMACDChart(id, start, end);
	    double[] ema12DataArray  = new double[macdData.length];
	    for(int i=0;i<ema12DataArray.length;i++){
	    	ema12DataArray[i] = macdData[i].ema12;
	    }
	     
	     return ema12DataArray;
	    //return list;
	  }
	  //ema26Data
	  @RequestMapping(params="method=ema26Data",method=RequestMethod.GET)
	  public @ResponseBody double[] ema26Data() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    MACDChartVO[] macdData = stockService.getMACDChart(id, start, end);
	    double[] ema26DataArray  = new double[macdData.length];
	    for(int i=0;i<ema26DataArray.length;i++){
	    	ema26DataArray[i] = macdData[i].ema26;
	    }
	     
	     return ema26DataArray;
	    //return list;
	  }
	  
	  //diffData
	  @RequestMapping(params="method=diffData",method=RequestMethod.GET)
	  public @ResponseBody double[] diffData() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    MACDChartVO[] macdData = stockService.getMACDChart(id, start, end);
	    double[] diffDataArray  = new double[macdData.length];
	    for(int i=0;i<diffDataArray.length;i++){
	    	diffDataArray[i] = macdData[i].diff;
	    }
	     
	     return diffDataArray;
	    //return list;
	  }
	  
	  //barData
	  @RequestMapping(params="method=barData",method=RequestMethod.GET)
	  public @ResponseBody double[] barData() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    MACDChartVO[] macdData = stockService.getMACDChart(id, start, end);
	    double[] barDataArray  = new double[macdData.length];
	    for(int i=0;i<barDataArray.length;i++){
	    	barDataArray[i] = macdData[i].bar;
	    }
	     
	     return barDataArray;
	    //return list;
	  }
	  
	  //kData
	  @RequestMapping(params="method=kData",method=RequestMethod.GET)
	  public @ResponseBody double[] kData(String para,String date,String startTime) throws Exception{ 
		System.out.println(para);
		System.out.println(date);
		System.out.println(startTime);
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    //MACDChartVO[] macdData = stockService.getMACDChart(id, start, end);
	    KDJChartVO[] kdjChartData = stockService.getKDJChartVO(id, start, end);
	    
	    double[] kDataArray  = new double[kdjChartData.length];
	    for(int i=0;i<kDataArray.length;i++){
	    	kDataArray[i] = kdjChartData[i].k;
	    }
	     
	     return kDataArray;
	    //return list;
	  }
	  //d,j
	  @RequestMapping(params="method=dData",method=RequestMethod.GET)
	  public @ResponseBody double[] dData() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    //MACDChartVO[] macdData = stockService.getMACDChart(id, start, end);
	    KDJChartVO[] kdjChartData = stockService.getKDJChartVO(id, start, end);
	    
	    double[] dDataArray  = new double[kdjChartData.length];
	    for(int i=0;i<dDataArray.length;i++){
	    	dDataArray[i] = kdjChartData[i].d;
	    }
	     
	     return dDataArray;
	    //return list;
	  }
	  //
	  @RequestMapping(params="method=jData",method=RequestMethod.GET)
	  public @ResponseBody double[] jData() throws Exception{ 
	    String id = "grail";
	    String start = "2016-03-01";
	    String end = "2016-04-01";
	    //MACDChartVO[] macdData = stockService.getMACDChart(id, start, end);
	    KDJChartVO[] kdjChartData = stockService.getKDJChartVO(id, start, end);
	    
	    double[] jDataArray  = new double[kdjChartData.length];
	    for(int i=0;i<jDataArray.length;i++){
	    	jDataArray[i] = kdjChartData[i].k;
	    }
	     
	     return jDataArray;
	    //return list;
	  }
}
