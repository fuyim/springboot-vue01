package com.obtk.util.updataPhoto;

import java.util.Calendar;

/**
 * 根据时间更换头像名称，防止头像名称一致
 *
 */
public class UpdataPhotoNameUtils {

    public static String getPhotoName(String name ,String imageFileName){
        String fileName = name;
        Calendar calendar = Calendar.getInstance();
        //创建年目录
        int year = calendar.get(Calendar.YEAR);
        fileName = fileName + "_"+year;
        //创建月目录
        int month = calendar.get(Calendar.MONTH)+1;
        fileName=fileName+"_";
        if(month<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+month;
        //创建如目录
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        fileName=fileName+"_";
        if(day<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+day;
        //生成小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+hour;
        //生成分目录
        int minute = calendar.get(Calendar.MINUTE);
        if(minute<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+minute;
        int second=calendar.get(Calendar.SECOND);
        if(second<10)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+second;
        //生成文件名的毫秒部分
        int millisecond=calendar.get(Calendar.MILLISECOND);
        if(millisecond<10)
        {
            fileName=fileName+"0";
        }
        if(millisecond<100)
        {
            fileName=fileName+"0";
        }
        fileName=fileName+millisecond;
        //生成文件的扩展名部分,只截取最后单位
        String endName = imageFileName.substring(imageFileName.lastIndexOf("."));//截取之后的值
        fileName=fileName+ endName;
        System.out.println(fileName);
        return fileName;
    }


}
