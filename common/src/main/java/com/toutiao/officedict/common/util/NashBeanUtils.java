package com.toutiao.officedict.common.util;

import com.toutiao.officedict.common.commonmodel.Money;
import com.toutiao.officedict.common.util.BeanUtilsConverts.MoneyConvert;
import com.toutiao.officedict.common.util.BeanUtilsConverts.NashLongConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

/**
 * zhangjinglei 2017/9/9 下午2:00
 */
public class NashBeanUtils {
    private static boolean hasInit=false;

    public static void copyProperties(Object dest, Object orig){
        if(!hasInit){
            hasInit=true;
            ConvertUtils.deregister(Long.class);
            ConvertUtils.register(new NashLongConverter(),Long.class);
            ConvertUtils.register(new MoneyConvert(),Money.class);
        }
        try {
            BeanUtils.copyProperties(dest, orig);
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
