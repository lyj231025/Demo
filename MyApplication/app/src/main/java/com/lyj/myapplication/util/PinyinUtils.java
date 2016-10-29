package com.lyj.myapplication.util;

import opensource.jpinyin.PinyinFormat;
import opensource.jpinyin.PinyinHelper;

/**
 * Created by lyj on 2016/10/29.
 */

public class PinyinUtils {
    public static String getPinyin(String str){
       return  PinyinHelper.convertToPinyinString(str,"", PinyinFormat.WITHOUT_TONE);
    }
}
