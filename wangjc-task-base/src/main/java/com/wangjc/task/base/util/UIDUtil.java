package com.wangjc.task.base.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author com.wangjc
 * 生成时间流水号加随机数作为唯一ID的工具类
 * 比字母串更便于排序查询，直观简单，不重复
 */
public class UIDUtil {

    private static int sn=0;

    /**
     * 生成一个新的唯一ID，21位，17位时间+4位流水号
     * @return：返回生成的流水号
     */
    public synchronized static String getUIDS(){
        if(sn>=9999){
            sn=0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //年月日时分秒毫秒序列4共21位
        return sdf.format(new Date())+new DecimalFormat("0000").format(sn++);
    }

    /**
     * 生成一个唯一的ID，使用指定的ID作为前缀，前缀加17位时间+4位流水号
     * @param prefix:指定前缀
     * @return
     */
    public synchronized static String getUIDS(String prefix){
        if(sn>=9999){
            sn=0;
        }
        return prefix.concat(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+
            new DecimalFormat("0000").format(sn++));
    }
}
