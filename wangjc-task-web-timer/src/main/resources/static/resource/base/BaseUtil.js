/*************************************基础Ajax请求 @wangjc***********************************/
var BaseAjax = {
	getDataAsync:function(ajax_url,param,success,contentType){//异步请求，回调函数一定要有
		var disParam,index;
		if(BaseUtil.isEmpty(param)){
			disParam = null;
		}else{
			disParam = param;
		}
		var ajaxOption={
			cache:false,//关闭缓存
			async:true,//默认为true
			url:ajax_url,
			data:disParam,
			method:"post",
			dataType:"json",

            beforeSend:function(){
                index = Feng.loading("数据请求中。。");
            },

			success:function(resultData){
            	Feng.close(index);
				success(resultData);
			},
			error: function (err) {
				Feng.close(index);
				Feng.close("请求超时")
			}
		};
		//之前封装遗漏的，导致很多地方编辑保存，传参不合法
		if(contentType){
			ajaxOption.contentType="application/json; charset=UTF-8";
		}
		$.ajax(ajaxOption);
	},
	getDataAsync_Map:function(ajax_url,param,success){
		return BaseAjax.getDataAsync(ajax_url,JSON.stringify(param),success,true);
	},
	getData_Map:function(ajax_url,param,loadFlag){
		return BaseAjax.getData(ajax_url,JSON.stringify(param),true,loadFlag);
	},
	getData:function(ajax_url,param,contentType,loadFlag){//同步加载,是否带loading
		var disParam,result,index;
		if(BaseUtil.isEmpty(param)){
			disParam = null;
		}else{
			disParam = param;
		}
		var ajaxOption={
			cache:false,//关闭缓存
			async:false,
			url:ajax_url,
			data:disParam,
			method:"post",
			dataType:"json",
			success:function(resultData){
				result = resultData;
			},
			error: function (err) {
				Feng.close(index);
				Feng.close("请求超时")
			}
		};
		//之前封装遗漏的，导致很多地方编辑保存，传参不合法
		if(contentType){
			ajaxOption.contentType="application/json; charset=UTF-8";
		}
        if(loadFlag == true){//默认不带loading加载
            ajaxOption.beforeSend=function(){
                index = Feng.loading();
            };
            ajaxOption.success=function(resultData){
            	Feng.close(index);
                result = resultData;
            };
        }
		$.ajax(ajaxOption);
		return result;
	},
	getPageHtml:function(ajax_url,param,success){
		var disParam,index;
		if(BaseUtil.isEmpty(param)){
			disParam = null
		}else{
			disParam = param;
		}
		var ajaxOption = {
			cache:false,
			url:ajax_url,
			data:disParam,
			method:"post",
			dataType:"html",
			beforeSend:function(){
				index = Feng.load(2);
			},
			success:function(resultData){
				Feng.close(index);
				success(resultData);
			},
			error: function (err) {
				Feng.close(index);
				Feng.close("请求超时")
			}
		};
		$.ajax(ajaxOption);
	}
};
/*************************************公共util函数 @wangjc*********************************/
var BaseUtil = {
	/**判断非空*/
	isEmpty:function(obj){//判断非空
	    if(typeof obj == "undefined" || obj == null || obj == "" || obj == "null"){
	        return true;
	    }else{
	        return false;
	    }
	},
	
	/**替换字符串*/
	replaceAll:function(info,regax,replace){
		var t = eval("/"+regax+"/g");
		return info.replace(t,replace);
	},
	
	/**获取页面请求链接中包含的参数*/
	getPageParam:function(param){
		var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if (r != null){
	        return unescape(r[2]);
	    }else{
		    return "";	    	
	    }
	},

	/** 获取form表单的输入的参数 */
	getParamByFormId:function(formId){
        var a, o, h, i, e;
        a = $("#"+formId).serializeArray();
        o = {};
        h = o.hasOwnProperty;
        for (i = 0; i < a.length; i++) {
            e = a[i];
            if (!h.call(o, e.name)) {
                o[e.name] = BaseUtil.trim(e.value);
            }
        }
        return o;
	},

	/** 去左右空格 */
	trim:function(s){
        return s.replace(/(^\s*)|(\s*$)/g, "");
	},

	/** 跳链接 */
    redirect:function(url){
        location.href = url;
    },

	/** 延时执行 */
    setTimeout:function(method,times){//延时函数
        setTimeout(method,times);
    },

	/** 清空对象属性 */
	clearParam:function(param){
		for(var key in param){
			param[key] = "";
		}
	},

	/** 点击复制 */
    clickCopy:function(copyInfo,successInfo){
        var oInput = document.createElement('input');//虚拟一个对象
        oInput.value = copyInfo;
        document.body.appendChild(oInput);
        oInput.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
        oInput.className = 'oInput';
        oInput.style.display='none';
        Feng.success(successInfo);
    },

	/** 刷新父页面 */
	reloadParent:function(){
		parent.location.reload();
	}
};

/*************************************日期工具**************************************/
var BaseDate = {

    /**
	 * 时间戳转字符串，精确到秒
     * @param timestamp:时间戳
     * @returns {*}
     * @constructor
     */
    timeStampToDate:function(timestamp){
        if(BaseUtil.isEmpty(timestamp)){
            return null;
        }else{
            var d = new Date(timestamp * 1000);
            return (d.getFullYear())+"-"+(d.getMonth()+1)+"-"+(d.getDate())+" "+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds());
        }
    },

    /**
	 * 字符串转为时间戳
     * @param DateStr
     * @returns {*}
     * @constructor
     */
    dateStrToTimeStamp:function(DateStr){
        if(BaseUtil.isEmpty(DateStr)){
            return null;
        }else{
            return new Date(DateStr).getTime()/1000;
        }
    },

    /**
     * 当前时间往前推几天，
     * @param before：负数之前，正数之后
     */
    rangeDate:function(before){
        var today = new Date();
        today.setTime(today.getTime()+(24*before)*60*60*1000);
        var y = today.getFullYear();
        var m = (today.getMonth() + 1) < 10 ? "0" + (today.getMonth() + 1) : (today.getMonth() + 1);// 获取当前月份的日期
        var d = today.getDate() < 10 ? "0" + today.getDate() : today.getDate();
        var pre = y + "-" + m + "-" + d;
        return pre;
    },

    /**
     * 指定时间往前推几天，
     * @param before：负数之前，正数之后
     */
    rangeDateByOper:function(operDate,before){
        var today = new Date(operDate);
        today.setTime(today.getTime()+(24*before)*60*60*1000);
        var y = today.getFullYear();
        var m = (today.getMonth() + 1) < 10 ? "0" + (today.getMonth() + 1) : (today.getMonth() + 1);// 获取当前月份的日期
        var d = today.getDate() < 10 ? "0" + today.getDate() : today.getDate();
        var pre = y + "-" + m + "-" + d;
        return pre;
    },

    /**
	 * 为了迎合数据库yyyyMMdd的格式，此处主要服务于日期联动框，格式化
     * @param date
     */
	getDateByRangeLay:function(date){
		var arr = date.split(" - ");
		var res = {};
		res.startDate = BaseUtil.replaceAll(arr[0],"-","");
		res.endDate = BaseUtil.replaceAll(arr[1],"-","");
		return res;
	},

	/**
	 * 获取日期之间的天数
	 * @param startDate：计划开始时间
	 * @param endDate：计划结束时间
	 * @param dayLength：每隔几天，0-代表每天，1-代表日期间隔一天
	 */
	getRangeDatelist:function(startDate, endDate, dayLength) {
		var res = [];
		res.push(startDate);
		for (var i = 0 ;; i++) {
			var getDate = BaseDate.getTargetDate(startDate, dayLength);
			startDate = getDate;
			if (getDate <= endDate) {
				res.push(getDate);
			} else {
				break;
			}
		}
		return res;
	},

	/**
	 *
	 * @param date 开始时间
	 * @param dayLength 每隔几天，0-代表获取每天，1-代表日期间隔一天
	 * @returns {string}
	 */
	getTargetDate:function(date,dayLength) {
		dayLength = dayLength + 1;
		var tempDate = new Date(date);
		tempDate.setDate(tempDate.getDate() + dayLength);
		var year = tempDate.getFullYear();
		var month = tempDate.getMonth() + 1 < 10 ? "0" + (tempDate.getMonth() + 1) : tempDate.getMonth() + 1;
		var day = tempDate.getDate() < 10 ? "0" + tempDate.getDate() : tempDate.getDate();
		return year + "-" + month + "-" + day;
	},
	/**
	 * 获取两个日期之间天数差
	 * @param start
	 * @param end
	 * @returns {number}
	 */
	getDiff:function(start,end){
		var days = new Date(end).getTime() - new Date(start).getTime();
		var time = parseInt(days / (1000 * 60 * 60 * 24));
		return time;
	},
	/**
	 * 20181220 => 2018-12-20
	 * @param date
	 * @returns {string}
	 */
	d8ToD11(date){
		return [date.substring(0, 4), date.substring(4, 6), date.substring(6, 8)].join('-')
	},
	/**
	 * 获取当月的第一天
	 * @returns {string}
	 */
	getStartByCurrMonth(){
		return this.rangeDate(0).substr(0,8)+"01"
	}

};