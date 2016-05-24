function setStatistics(data) {
	//var data = [3020.18, -5.0, -71, 3022.2, 3045.2, 3079.2, 3011.3, 150000, 5600020];

	if (data[1] > 0) {
		document.getElementById("now").innerHTML = "<span style='color:red'>" + data[0] + "</span>";
		document.getElementById("increaseValue").innerHTML = "<span style='color:red'>" + data[1] + "%</span>";
		document.getElementById("increase").innerHTML = "<span style='color:red'>" + data[2] + "</span>";
		document.getElementById("graphIcon").innerHTML = "<span class='glyphicon glyphicon-arrow-up' style='color:darkred' aria-hidden='true'> </span>";
	} else if (data[1] < 0) {
		document.getElementById("now").innerHTML = "<span style='color:green'>" + data[0] + "</span>";
		document.getElementById("increaseValue").innerHTML = "<span style='color:green'>" + data[1] + "%</span>";
		document.getElementById("increase").innerHTML = "<span style='color:green'>" + data[2] + "</span>";
		document.getElementById("graphIcon").innerHTML = "<span class='glyphicon glyphicon-arrow-down' style='color:darkgreen' aria-hidden='true'> </span>";
	} else {
		document.getElementById("now").innerHTML = "<span>" + data[0] + "</span>";;
		document.getElementById("increaseValue").innerHTML = "--";
		document.getElementById("increase").innerHTML = "--";
		document.getElementById("graphIcon").innerHTML = "<span>--</span>";
	}

	if (data[3] > data[4]) {
		document.getElementById("open").innerHTML = "<span style='color:red'>" + data[3] + "</span>";
		document.getElementById("close").innerHTML = data[4];
	} else {
		document.getElementById("open").innerHTML = "<span style='color:green'>" + data[3] + "</span>";
		document.getElementById("close").innerHTML = data[4];
	}
	document.getElementById("high").innerHTML = "<span style='color:red'>" + data[5] + "</span>";
	document.getElementById("low").innerHTML = "<span style='color:green'>" + data[3] + "</span>";
	document.getElementById("volume").innerHTML = "<span style='color:blue'>" + data[3] + "</span>";
	document.getElementById("volumeValue").innerHTML = "<span style='color:blue'>" + data[3] + "</span>";

}

var axisData = [
		"2013/1/24", "2013/1/25", "2013/1/28", "2013/1/29", "2013/1/30",
		"2013/1/31", "2013/2/1", "2013/2/4", "2013/2/5", "2013/2/6",
		"2013/2/7", "2013/2/8", "2013/2/18", "2013/2/19", "2013/2/20",
		"2013/2/21", "2013/2/22", "2013/2/25", "2013/2/26", "2013/2/27",
		"2013/2/28", "2013/3/1", "2013/3/4", "2013/3/5", "2013/3/6",
		"2013/3/7", "2013/3/8", "2013/3/11", "2013/3/12", "2013/3/13",
		"2013/3/14", "2013/3/15", "2013/3/18", "2013/3/19", "2013/3/20",
		"2013/3/21", "2013/3/22", "2013/3/25", "2013/3/26", "2013/3/27",
		"2013/3/28", "2013/3/29", "2013/4/1", "2013/4/2", "2013/4/3",
		"2013/4/8", "2013/4/9", "2013/4/10", "2013/4/11", "2013/4/12",
		"2013/4/15", "2013/4/16", "2013/4/17", "2013/4/18", "2013/4/19",
		"2013/4/22", "2013/4/23", "2013/4/24", "2013/4/25", "2013/4/26",
		"2013/5/2", "2013/5/3", "2013/5/6", "2013/5/7", "2013/5/8",
		"2013/5/9", "2013/5/10", "2013/5/13", "2013/5/14", "2013/5/15",
		"2013/5/16", "2013/5/17", "2013/5/20", "2013/5/21", "2013/5/22",
		"2013/5/23", "2013/5/24", "2013/5/27", "2013/5/28", "2013/5/29",
		"2013/5/30", "2013/5/31", "2013/6/3", "2013/6/4", "2013/6/5",
		"2013/6/6", "2013/6/7", "2013/6/13"
	];
	
function setKline() {


	var KLineData = [ // 开盘，收盘，最低，最高  
		[2320.26, 2302.6, 2287.3, 2362.94],
		[2300, 2291.3, 2288.26, 2308.38],
		[2295.35, 2346.5, 2295.35, 2346.92],
		[2347.22, 2358.98, 2337.35, 2363.8],
		[2360.75, 2382.48, 2347.89, 2383.76],
		[2383.43, 2385.42, 2371.23, 2391.82],
		[2377.41, 2419.02, 2369.57, 2421.15],
		[2425.92, 2428.15, 2417.58, 2440.38],
		[2411, 2433.13, 2403.3, 2437.42],
		[2432.68, 2434.48, 2427.7, 2441.73],
		[2430.69, 2418.53, 2394.22, 2433.89],
		[2416.62, 2432.4, 2414.4, 2443.03],
		[2441.91, 2421.56, 2415.43, 2444.8],
		[2420.26, 2382.91, 2373.53, 2427.07],
		[2383.49, 2397.18, 2370.61, 2397.94],
		[2378.82, 2325.95, 2309.17, 2378.82],
		[2322.94, 2314.16, 2308.76, 2330.88],
		[2320.62, 2325.82, 2315.01, 2338.78],
		[2313.74, 2293.34, 2289.89, 2340.71],
		[2297.77, 2313.22, 2292.03, 2324.63],
		[2322.32, 2365.59, 2308.92, 2366.16],
		[2364.54, 2359.51, 2330.86, 2369.65],
		[2332.08, 2273.4, 2259.25, 2333.54],
		[2274.81, 2326.31, 2270.1, 2328.14],
		[2333.61, 2347.18, 2321.6, 2351.44],
		[2340.44, 2324.29, 2304.27, 2352.02],
		[2326.42, 2318.61, 2314.59, 2333.67],
		[2314.68, 2310.59, 2296.58, 2320.96],
		[2309.16, 2286.6, 2264.83, 2333.29],
		[2282.17, 2263.97, 2253.25, 2286.33],
		[2255.77, 2270.28, 2253.31, 2276.22],
		[2269.31, 2278.4, 2250, 2312.08],
		[2267.29, 2240.02, 2239.21, 2276.05],
		[2244.26, 2257.43, 2232.02, 2261.31],
		[2257.74, 2317.37, 2257.42, 2317.86],
		[2318.21, 2324.24, 2311.6, 2330.81],
		[2321.4, 2328.28, 2314.97, 2332],
		[2334.74, 2326.72, 2319.91, 2344.89],
		[2318.58, 2297.67, 2281.12, 2319.99],
		[2299.38, 2301.26, 2289, 2323.48],
		[2273.55, 2236.3, 2232.91, 2273.55],
		[2238.49, 2236.62, 2228.81, 2246.87],
		[2229.46, 2234.4, 2227.31, 2243.95],
		[2234.9, 2227.74, 2220.44, 2253.42],
		[2232.69, 2225.29, 2217.25, 2241.34],
		[2196.24, 2211.59, 2180.67, 2212.59],
		[2215.47, 2225.77, 2215.47, 2234.73],
		[2224.93, 2226.13, 2212.56, 2233.04],
		[2236.98, 2219.55, 2217.26, 2242.48],
		[2218.09, 2206.78, 2204.44, 2226.26],
		[2199.91, 2181.94, 2177.39, 2204.99],
		[2169.63, 2194.85, 2165.78, 2196.43],
		[2195.03, 2193.8, 2178.47, 2197.51],
		[2181.82, 2197.6, 2175.44, 2206.03],
		[2201.12, 2244.64, 2200.58, 2250.11],
		[2236.4, 2242.17, 2232.26, 2245.12],
		[2242.62, 2184.54, 2182.81, 2242.62],
		[2187.35, 2218.32, 2184.11, 2226.12],
		[2213.19, 2199.31, 2191.85, 2224.63],
		[2203.89, 2177.91, 2173.86, 2210.58],
		[2170.78, 2174.12, 2161.14, 2179.65],
		[2179.05, 2205.5, 2179.05, 2222.81],
		[2212.5, 2231.17, 2212.5, 2236.07],
		[2227.86, 2235.57, 2219.44, 2240.26],
		[2242.39, 2246.3, 2235.42, 2255.21],
		[2246.96, 2232.97, 2221.38, 2247.86],
		[2228.82, 2246.83, 2225.81, 2247.67],
		[2247.68, 2241.92, 2231.36, 2250.85],
		[2238.9, 2217.01, 2205.87, 2239.93],
		[2217.09, 2224.8, 2213.58, 2225.19],
		[2221.34, 2251.81, 2210.77, 2252.87],
		[2249.81, 2282.87, 2248.41, 2288.09],
		[2286.33, 2299.99, 2281.9, 2309.39],
		[2297.11, 2305.11, 2290.12, 2305.3],
		[2303.75, 2302.4, 2292.43, 2314.18],
		[2293.81, 2275.67, 2274.1, 2304.95],
		[2281.45, 2288.53, 2270.25, 2292.59],
		[2286.66, 2293.08, 2283.94, 2301.7],
		[2293.4, 2321.32, 2281.47, 2322.1],
		[2323.54, 2324.02, 2321.17, 2334.33],
		[2316.25, 2317.75, 2310.49, 2325.72],
		[2320.74, 2300.59, 2299.37, 2325.53],
		[2300.21, 2299.25, 2294.11, 2313.43],
		[2297.1, 2272.42, 2264.76, 2297.1],
		[2270.71, 2270.93, 2260.87, 2276.86],
		[2264.43, 2242.11, 2240.07, 2266.69],
		[2242.26, 2210.9, 2205.07, 2250.63],
		[2190.1, 2148.35, 2126.22, 2190.1]
	];

	var VolumeData = [13560434, 8026738.5, 11691637, 12491697, 12485603,
		11620504, 12555496, 15253370, 12709611, 10458354,
		10933507, 9896523, 10365702, 10633095, 9722230,
		12662783, 8757982, 7764234, 10591719, 8826293,
		11591827, 11153111, 14304651, 11672120, 12536480,
		12608589, 8843860, 7391994.5, 10063709, 7768895.5,
		6921859, 10157810, 8148617.5, 7551207, 11397426,
		10478607, 8595132, 8541862, 9181132, 8570842,
		10759351, 7335819, 6699753.5, 7759666.5, 6880135.5,
		7366616.5, 7313504, 7109021.5, 6213270, 5619688,
		5816217.5, 6695584.5, 5998655.5, 6188812.5, 9538301,
		8224500, 8221751.5, 7897721, 8448324, 6525151,
		5987761, 7831570, 8162560.5, 7904092, 8139084.5,
		9116529, 8128014, 7919148, 7566047, 6665826.5,
		10225527, 11124881, 12884353, 11302521, 11529046,
		11105205, 9202153, 9992016, 12035250, 11431155,
		10354677, 10070399, 9164861, 9237718, 7114268,
		7526158.5, 8105835, 7971452.5
	];

	var swingData = [
		560434, 226738, 696370, 249697, 248563,
		620504, 555496, 525337, 270968, 458354,
		933507, 896523, 365702, 633095, 722230,
		662783, 875798, 776423, 105979, 882629,
		598278, 231253, 430465, 672208, 253648,
		608589, 884386, 739994, 263709, 776889,
		692859, 105780, 848675, 755207, 397426,
		478607, 859532, 854862, 983288, 857084,
		759358, 733589, 669975, 775965, 688035,
		736666, 733504, 709025, 623270, 569688,
		586275, 669558, 599865, 688825, 953830,
		822450, 822755, 789772, 844832, 652558,
		598776, 783570, 862560, 794092, 839084,
		965298, 828048, 799480, 756647, 665826,
		102257, 248870, 288435, 302528, 529046,
		105205, 920253, 999206, 203525, 435588,
		103546, 703990, 964868, 923778, 742688,
		752658, 805835, 797452
	];

	//KLine
	option = {
		title: {
			text: '沪深300指数'
		},
		tooltip: {
			trigger: 'axis',
			showDelay: 0, // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
			//			formatter: function(params) {
			//				var res = params[0].name;
			//				res += '<br/>' + params[0].seriesName;
			//				res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
			//				res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
			//				return res;
			//			}
		},
		legend: {
			data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
		},
		toolbox: {
			show: true,
			feature: {
				mark: {
					show: true
				},
				dataZoom: {
					show: true
				},
				magicType: {
					show: true,
					type: ['line', 'bar']
				},
				restore: {
					show: true
				},
				saveAsImage: {
					show: true
				}
			}
		},
		dataZoom: {
			y: 250,
			show: false,
			realtime: true,
			start: 50,
			end: 100
		},
		grid: {
			x: 80,
			y: 40,
			x2: 20,
			y2: 25
		},
		xAxis: [{
			type: 'category',
			boundaryGap: true,
			axisTick: {
				onGap: false
			},
			splitLine: {
				show: false
			},
			data: axisData
		}],
		yAxis: [{
			type: 'value',
			scale: true,
			boundaryGap: [0.05, 0.05],
			splitArea: {
				show: true
			}
		}],
		series: [{
				name: '沪深300',
				type: 'k',
				data: KLineData

			}, {
				name: 'MA5',
				type: 'line',
				data: calculateMA(5)
			}, {
				name: 'MA10',
				type: 'line',
				data: calculateMA(10)
			}, {
				name: 'MA20',
				type: 'line',
				data: calculateMA(20)
			}, {
				name: 'MA30',
				type: 'line',
				data: calculateMA(30)
			}

		]
	};

	//成交额
	option2 = {
		tooltip: {
			trigger: 'axis',
			showDelay: 0 // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
		},
		legend: {
			y: -30,
			data: ['沪深300', '成交金额(万)', '振幅']
		},
		toolbox: {
			y: -30,
			show: true,
			feature: {
				mark: {
					show: true
				},
				dataZoom: {
					show: true
				},
				dataView: {
					show: true,
					readOnly: false
				},
				magicType: {
					show: true,
					type: ['line', 'bar']
				},
				restore: {
					show: true
				},
				saveAsImage: {
					show: true
				}
			}
		},
		dataZoom: {
			show: true,
			realtime: true,
			start: 50,
			end: 100
		},
		grid: {
			x: 80,
			y: 5,
			x2: 20,
			y2: 40
		},
		xAxis: [{
			type: 'category',
			position: 'top',
			boundaryGap: true,
			axisLabel: {
				show: false
			},
			axisTick: {
				onGap: false
			},
			splitLine: {
				show: false
			},
			data: axisData
		}],
		yAxis: [{
			type: 'value',
			scale: true,
			splitNumber: 3,
			boundaryGap: [0.05, 0.05],
			axisLabel: {
				formatter: function(v) {
					return Math.round(v / 10000) + ' 万'
				}
			},
			splitArea: {
				show: true
			}
		}],
		series: [{
			name: '成交金额(万)',
			type: 'line',
			symbol: 'none',
			data: VolumeData,
			markLine: {
				symbol: 'none',
				itemStyle: {
					normal: {
						color: '#1e90ff',
						label: {
							show: false
						}
					}
				},
				data: [{
					type: 'average',
					name: '平均值'
				}]
			}
		}]
	};

	//振幅
	option3 = {
		tooltip: {
			trigger: 'axis',
			showDelay: 0 // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
		},
		legend: {
			y: -30,
			data: ['沪深300', '成交金额(万)', '振幅']
		},
		toolbox: {
			y: -30,
			show: true,
			feature: {
				mark: {
					show: true
				},
				dataZoom: {
					show: true
				},
				dataView: {
					show: true,
					readOnly: false
				},
				magicType: {
					show: true,
					type: ['line', 'bar']
				},
				restore: {
					show: true
				},
				saveAsImage: {
					show: true
				}
			}
		},
		dataZoom: {
			y: 200,
			show: true,
			realtime: true,
			start: 50,
			end: 100
		},
		grid: {
			x: 80,
			y: 5,
			x2: 20,
			y2: 30
		},
		xAxis: [{
			type: 'category',
			position: 'bottom',
			boundaryGap: true,
			axisTick: {
				onGap: false
			},
			splitLine: {
				show: false
			},
			data: axisData
		}],
		yAxis: [{
			type: 'value',
			scale: true,
			splitNumber: 3,
			boundaryGap: [0.05, 0.05],
			axisLabel: {
				formatter: function(v) {
					return Math.round(v / 10000) + ' 万'
				}
			},
			splitArea: {
				show: true
			}
		}],
		series: [{
			name: '振幅',
			type: 'bar',
			symbol: 'none',
			data: swingData,
			markLine: {
				symbol: 'none',
				itemStyle: {
					normal: {
						color: '#1e90ff',
						label: {
							show: false
						}
					}
				},
				data: [{
					type: 'average',
					name: '平均值'
				}]
			}
		}]
	};
	myChart.setOption(option);
	myChart2.setOption(option2);
	myChart3.setOption(option3);
	//联动图表
	echarts.connect([myChart, myChart3, myChart2]);
	
	function calculateMA(dayCount) {
	var result = [];
	for (var i = 0, len = axisData.length; i < len; i++) {
		if (i < dayCount) {
			result.push('-');
			continue;
		}
		var sum = 0;
		for (var j = 0; j < dayCount; j++) {
			sum += KLineData[i - j][1];
		}
		result.push((sum / dayCount).toFixed(3));
	}
	return result;
}
}

function setMACDChart() {
	
	macdData = [120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101 ];
	ema12Data = [220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234];
	ema26Data =[150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410]
	diffData = [320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334,320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334,320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334,320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334,320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334,320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334,320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334,320, 332, 301, 334, 390, 330, 320,320, 332, 301, 334]
	barData =[820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934,820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934,820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934,820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934,820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934,820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934,820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934,820, 932, 901, 934, 1290, 1330, 1320,820, 932, 901, 934]
	macdoption = {
    title: {
        text: 'MACD数据'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['MACD','EMA12','EMA26','DIFF','BAR']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: axisData
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'MACD',
            type:'line',
            stack: '总量',
            data:macdData
        },
        {
            name:'EMA12',
            type:'line',
            stack: '总量',
            data:ema12Data
        },
        {
            name:'EMA26',
            type:'line',
            stack: '总量',
            data:ema26Data
        },
        {
            name:'DIFF',
            type:'line',
            stack: '总量',
            data:diffData
        },
        {
            name:'BAR',
            type:'line',
            stack: '总量',
            data:barData
        }
    ]
    
};

	macdChart.setOption(macdoption);
	
}

function setKDJChart(){
	kData = [120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101 ];
	dData = [220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234];
	jData =[150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410]
	
	kdjoption = {
    title: {
        text: 'KDJ数据'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['K','D','J']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: axisData
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'K',
            type:'line',
            stack: '总量',
            data:kData
        },
        {
            name:'D',
            type:'line',
            stack: '总量',
            data:dData
        },
        {
            name:'J',
            type:'line',
            stack: '总量',
            data:jData
        }
    ]
    
};

	kdjChart.setOption(kdjoption);
}

function setRSIChart(){
	rsi6Data = [120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101 ];
	rsi12Data = [220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234];
	rsi14Data =[150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410]
	
	rsioption = {
    title: {
        text: 'RSI数据'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['RSI6','RSI12','RSI14']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: axisData
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'RSI6',
            type:'line',
            stack: '总量',
            data:rsi6Data
        },
        {
            name:'RSI12',
            type:'line',
            stack: '总量',
            data:rsi12Data
        },
        {
            name:'RSI14',
            type:'line',
            stack: '总量',
            data:rsi14Data
        }
    ]
    
};

	rsiChart.setOption(rsioption);
}

function setBOLLChart(){
	upData = [120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101 ];
	mbData = [220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234];
	downData =[150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410]
	
	bolloption = {
    title: {
        text: 'BOLL数据'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['UP','MB','DOWN']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: axisData
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'UP',
            type:'line',
            stack: '总量',
            data:upData
        },
        {
            name:'MB',
            type:'line',
            stack: '总量',
            data:mbData
        },
        {
            name:'DOWN',
            type:'line',
            stack: '总量',
            data:downData
        }
    ]
    
};

	bollChart.setOption(bolloption);
}
function setWRChart(){
		n1Data = [120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101,120, 132, 101, 134, 90, 230, 210, 100,120, 132, 101 ];
	n2Data = [220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234,220, 182, 191, 234, 290, 330, 310,220, 182, 191, 234];
	n3Data =[150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410,150, 232, 201, 154, 190, 330, 410,154, 190, 330, 410]
	
	wroption = {
    title: {
        text: 'WR数据'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['N1','N2','N3']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: axisData
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'N1',
            type:'line',
            stack: '总量',
            data:upData
        },
        {
            name:'N2',
            type:'line',
            stack: '总量',
            data:mbData
        },
        {
            name:'N3',
            type:'line',
            stack: '总量',
            data:downData
        }
    ]
    
};

	wrChart.setOption(wroption);
}

function setStatisticConclusion() {
	var statisticsConclusion = "根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。";
	document.getElementById("statisticsPanel").innerHTML = "<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + statisticsConclusion + "</p>";
}

function setFinalConclusion() {
	var statisticsConclusion = "根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。根据图中数据显示，该股票处于黄金交叉线，KDJ三线走势良好。。。。。";
	document.getElementById("finalConclusion").innerHTML = "<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp" + statisticsConclusion + "</p>";
}

function setAnalysisChart() {
	var xData = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];
	var yData = [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3];
	var analysisChartoption = {
		title: {
			text: '沪深300大盘',
			subtext: '涨跌分析'
		},
		tooltip: {
			trigger: 'axis'
		},
		legend: {
			data: ['涨幅']
		},
		toolbox: {
			show: true,
			feature: {
				mark: {
					show: true
				},
				dataView: {
					show: true,
					readOnly: false
				},
				magicType: {
					show: true,
					type: ['line', 'bar']
				},
				restore: {
					show: true
				},
				saveAsImage: {
					show: true
				}
			}
		},
		calculable: true,
		xAxis: [{
			type: 'category',
			data: xData
		}],
		yAxis: [{
			type: 'value'
		}],
		series: [{
			name: '涨幅',
			type: 'bar',
			data: yData,
			itemStyle: {
				normal: {
					color: function(params) {
						if (params.dataIndex < xData.length / 2)
							return 'green';
						else
							return '#BA2828';
					}
				}
			},
			markPoint: {
				data: [{
					type: 'max',
					name: '最大值'
				}, {
					type: 'min',
					name: '最小值'
				}]
			},
			markLine: {
				data: [{
					type: 'average',
					name: '平均值'
				}]
			}
		}]
	}
	myChart4.setOption(analysisChartoption);

}

function setInOutChart(){
	//大单流入、中单流入、小单流入、大单流出、中单流出、小单流出
	var datas = [1335,2310,2340,1350,1548,1548];
	
	var InoutChartData = {
    title : {
        text: '成交分布表',
         x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['大单流入','中单流入','小单流入','大单流出','中单流出','小单流出']
    },
    series : [
        {
            name: '成交分布表',
            type: 'pie',
            radius : '55%',
            center: ['55%', '60%'],
            data:[
                {value:datas[0], name:'大单流入'},
                {value:datas[1], name:'中单流入'},
                {value:datas[2], name:'小单流入'},
                {value:datas[3], name:'大单流出'},
                {value:datas[4], name:'中单流出'},
                {value:datas[5], name:'小单流出'},
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
}

var myChart = echarts.init(document.getElementById('KLine'));
var myChart2 = echarts.init(document.getElementById('chart2'));
var myChart3 = echarts.init(document.getElementById('chart3'));
var myChart4 = echarts.init(document.getElementById('BarLine'));
var macdChart = echarts.init(document.getElementById('macd'));
var kdjChart = echarts.init(document.getElementById('kdj'));
var rsiChart = echarts.init(document.getElementById('rsi'));
var bollChart = echarts.init(document.getElementById('boll'));
var wrChart = echarts.init(document.getElementById('wr'));

var inOutChart = echarts.init(document.getElementById('InOutChart'));

window.onresize = function() {
	myChart.resize();
	myChart2.resize();
	myChart3.resize();
	myChart4.resize();
	macdChart.resize();
	kdjChart.resize();
	rsiChart.resize();
	bollChart.resize();
	wrChart.resize();
	inOutChart.resize();
	//	statistics.resize();
	//	macdchart.resize();
	//	myChart4.resize();
	//	myChart5.resize();
	//	myChart6.resize();
	//	myChart7.resize();
	//	myChart8.resize();
	//	myChart9.resize();
	//	myChart10.resize();
}

function setAll(){
	//setStatistics();
	setMACDChart();
	setKline();
	setStatisticConclusion();
	setAnalysisChart();
	setKDJChart();
	setRSIChart();
	setBOLLChart();
	setWRChart();
}
