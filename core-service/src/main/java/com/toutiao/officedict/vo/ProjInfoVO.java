package com.toutiao.officedict.vo;

import com.toutiao.officedict.dao.entity.officedict.ProjectInfo;
import lombok.Data;

/**
 * @author WuShoulei on 2017/12/8
 */
@Data
public class ProjInfoVO extends ProjectInfo {

    /**
     * 销售状态描述
     */
    private String salingDesc;

    /**
     * 销售阶段描述
     */
    private String sailScheduleDesc;

    /**
     * 住宅建筑形式描述
     */
    private String buildFormDesc;

    /**
     * 住宅类别描述
     */
    private String buildCategoryDesc;

    /**
     * 住宅类别描述
     */
    private String residentialCategoryDesc;

    /**
     * 别墅建筑风格描述
     */
    private String villaStyleDesc;

    /**
     * 环线描述
     */
    private String ringRoadDesc;

}
