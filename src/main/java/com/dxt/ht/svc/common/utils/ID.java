package com.dxt.ht.svc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ID {

    private static long tmpID = 0;

    private static boolean tmpIDlocked = false;

    public static String getId()
    {
        Long ltime = (long)0;
        while (true)
        {

            if(tmpIDlocked == false)
            {
                tmpIDlocked = true;
                //当前：（年、月、日、时、分、秒、毫秒）*10000
                ltime = Long.valueOf(new SimpleDateFormat("yyMMddhhmmssSSS").format(new Date()).toString());
                if(tmpID < ltime)
                {
                    tmpID = ltime;
                }
                else
                {
                    tmpID = tmpID + 1;
                    ltime = tmpID;
                }
                tmpIDlocked = false;
                return ltime.toString();
            }else{
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
