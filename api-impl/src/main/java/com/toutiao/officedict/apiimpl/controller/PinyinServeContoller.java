package com.toutiao.officedict.apiimpl.controller;

import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.domain.pinyin.Pinyin;
import com.toutiao.officedict.service.pinyin.PinyinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/pinyin")
public class PinyinServeContoller {

    @Autowired
    private PinyinService pinyinService;


    /**
     * 根据名字转换拼音及首字大写
     * @param name
     * @return
     */
    @RequestMapping("/changepinyin")
    @ResponseBody
    public NashResult getChangepinyin(@RequestParam("name") String name){

        Pinyin pinyin = pinyinService.getPinyin(name);
        return NashResult.build(pinyin);

    }
}
