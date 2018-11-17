package com.toutiao.officedict.service.pinyin.impl;

import com.toutiao.officedict.common.util.Pinyin4jUtil;
import com.toutiao.officedict.domain.pinyin.Pinyin;
import com.toutiao.officedict.service.pinyin.PinyinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PinyinServiceImpl implements PinyinService{

    /**
     * 根据汉字转化拼音及拼音首字母
     * @param pinyin
     * @return
     */
    @Override
    public Pinyin getPinyin(String pinyin) {

        Pinyin py = new Pinyin();
        String pingYin = Pinyin4jUtil.getPingYin(pinyin);
        String pinYinHeadChar = Pinyin4jUtil.getPinYinHeadChar(pinyin);
        py.setPingYin(pingYin);
        py.setPinYinHeadChar(pinYinHeadChar);
        return py;
    }
}
