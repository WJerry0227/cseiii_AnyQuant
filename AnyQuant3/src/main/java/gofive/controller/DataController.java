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
import gofive.vo.chart.KLineChartVO;

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
	  
	  
}
