package com.toutiao.officedict.dao.entity.officedict;

import com.alibaba.fastjson.annotation.JSONField;
import com.toutiao.officedict.domain.newHouse.SalesDynamicInfo;
import com.toutiao.officedict.domain.newHouse.SalesLicenseInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectInfo {
    /**
     * 序号
     */
    private Integer newcode;

    /**
     * 新房/二手房(0-新房，1-二手房)
     */
    private Short nOrE;

    /**
     * 楼盘名称
     */
    private String projname;

    /**
     * 别名
     */
    private String nickname;

    /**
     * 楼盘分期
     */
    private String installment;

    /**
     * 地址
     */
    private String address;

    /**
     * 地址描述
     */
    private String addressInfo;

    /**
     * 房源特色
     */
    private String housefeature;

    /**
     * 项目特色/标签(数组)
     */
    private String projFeature;

    /**
     * 省
     */
    private String province;

    /**
     * 城市
     */
    private Integer cityId;

    /**
     * 区县
     */
    private Integer districtId;

    /**
     * 商圈
     */
    private Integer areaId;

    /**
     * 环线
     */
    private String round;

    /**
     * 环线方位
     */
    private String rounddirection;

    /**
     * 所属社区
     */
    private Integer communityId;

    /**
     * 项目描述
     */
    private String projdesc;

    /**
     * 产权年限
     */
    private Short rightYear;

    /**
     * 占地面积(平方米)
     */
    private Double groundarea;

    /**
     * 建筑面积(平方米)
     */
    private Double purposearea;

    /**
     * 容积率(%)
     */
    private Double dimension;

    /**
     * 绿化率(%)
     */
    private Double virescencerate;

    /**
     * 楼层状况
     */
    private String buildingdes;

    /**
     * 总户数
     */
    private Integer totaldoor;

    /**
     * 停车位描述
     */
    private String parkdesc;

    /**
     * 停车位数量
     */
    private Integer parkspace;

    /**
     * 停车位租价
     */
    private Double carRentPrice;

    /**
     * 停车位售价
     */
    private Double carSellPrice;

    /**
     * 物业管理费
     */
    private Double propertyfee;

    /**
     * 物业管理费价格单位
     */
    private String propfeetype;

    /**
     * 物业管理费附加信息
     */
    private String propertyaddition;

    /**
     * 建材设备
     */
    private String equipment;

    /**
     * 小区内部配套
     */
    private String layout;

    /**
     * 工程进度
     */
    private String workSchedule;

    /**
     * 开工时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date startdate;

    /**
     * 竣工时间
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date finishdate;

    /**
     * 开盘时间
     */
    private String saledate;

    /**
     * 开盘时间描述
     */
    private String saledateS;

    /**
     * 入住时间/交房时间
     */
    private String livindate;

    /**
     * 入住时间描述
     */
    private String livindateS;

    /**
     * 售楼热线
     */
    private String saletelphone;

    /**
     * 售楼地址
     */
    private String saleaddress;

    /**
     * 销售证
     */
    private String salecard;

    /**
     * 按揭银行
     */
    private String mortgageBank;

    /**
     * 销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    private Short saling;

    /**
     * 销售阶段(0:期房.1:现房.2:尾房.3:二手房)
     */
    private Short sailSchedule;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 投资商
     */
    private String investor;

    /**
     * 物业管理公司
     */
    private String propertymanage;

    /**
     * 物业顾问公司
     */
    private String propertyadviser;

    /**
     * 建筑及园林设计单位
     */
    private String landscape;

    /**
     * 承建商
     */
    private String construct;

    /**
     * 代理商
     */
    private String agent;

    /**
     * 景观设计单位
     */
    private String sightdesign;

    /**
     * 整合推广单位
     */
    private String conextend;

    /**
     * 平面媒体广告单位
     */
    private String media;

    /**
     * 审批状态（0-不通过，1-通过，2-错误）（0-未发布，1-已发布）
     */
    private Short isApprove;

    /**
     * 网站地址
     */
    private String webaddress;

    /**
     * 网上售楼中心地址
     */
    private String websaleaddress;

    /**
     * 完成度
     */
    private String completeFraction;

    /**
     * x坐标
     */
    private Double coordX;

    /**
     * y坐标
     */
    private Double coordY;

    /**
     * 楼盘置为售完的时间
     */
    private Date salingOptime;

    /**
     * 移交二手房或者从二手房转回的时间
     */
    private Date adminstautsTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 删除时间
     */
    private Date deldate;

    /**
     * 删除说明
     */
    private String delreason;

    /**
     * 400电话
     */
    private String tel400;

    /**
     * 价格增长率
     */
    private Double pricerate;

    /**
     * 最高价格
     */
    private Double pricemax;

    /**
     * 最低价格
     */
    private Double pricemin;

    /**
     * 二手房区县
     */
    private String esfDistrict;

    /**
     * 二手房商圈
     */
    private String esfComarea;

    /**
     * 名称全拼
     */
    private String pinyinName;

    /**
     * 名称拼音首字母
     */
    private String pinyinInitials;

    /**
     * 二手房地址
     */
    private String esfAddress;

    /**
     * 二手房业态
     */
    private String esfOperastion;

    /**
     * 二手房是否展示(0-否，1-是)
     */
    private Short esfShow;

    /**
     * 二手房审核状态（Y/N）
     */
    private String esfIsApprove;

    /**
     * 开盘更新时间
     */
    private Date updateSaledateTime;

    /**
     * 入住更新时间
     */
    private Date updateLivindateTime;

    /**
     * 产权描述
     */
    private String rightDesc;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 物业电话
     */
    private String propertyTele;

    /**
     * 物业地址
     */
    private String propertyAddress;

    /**
     * 创建人ID
     */
    private Integer creatorId;

    /**
     * 更新人ID
     */
    private Integer updaterId;

    /**
     * 删除标志位(0-否，1-是)
     */
    private Short isDel;

    /**
     * 平均单价-二手房
     */
    private Double esfPrice;

    /**
     * 平均总价-新房
     */
    private Double totalPrice;

    /**
     * 平均单价-新房
     */
    private Double price;

    /**
     * 平均总价-二手房
     */
    private Double esfTotalPrice;


    /**
     * 起价-新房
     */
    private Double newhouseLowestPrice;

    /**
     * 总起价-新房
     */
    private Double newhouseLowestTotalPrice;

    /**
     * 供暖方式(0-未知，1-集中供暖，2-自供暖）
     */
    private Integer heatingMode;

    /**
     * 梯户比
     */
    private String liftDoorRadio;

    /**
     * 车位配比
     */
    private String parkRadio;

    /**
     * 物业类别/业态：
     * （1-住宅,2-别墅,3-写字楼，4-商铺，5-底商，6-住宅底商，7=办公别墅，8-标准写字楼，
     * 9-酒店写字楼，10-写字楼商铺，11-市场类商铺，12-商务型公寓，13-购物中心，14-企业独栋，15-商业，16-总部园区）
     */
    private Integer propertyType;

    /**
     * 住宅建筑形式(数组)：
     * 1-低层，2-多层，3-小高层，4-高层，5-超高层，6-联排、7-独栋、8-双拼、
     * 9-叠拼、10-空中花园、11-空中别墅、12-开间、13-平层、14-复式、15-跃层、16-其他、17-合院
     */
    private Integer[] buildForm;

    /**
     * 建筑类别(数组)：
     * 1：板楼，2：塔楼，3：板塔结合，4：砖楼5：其他
     */
    private Integer[] buildCategory;

    /**
     * 别墅建筑风格(数组)：1-中式、2-欧式、3-日式、4-美式、5-英式、6-澳式、7-法式、8-西班牙式、9-东南亚式、10-地中海式、11-意大利式、12-现代
     */
    private Integer[] villaStyle;

    /**
     * 新房平均单价单位
     */
    private String priceUnit;

    /**
     * 新房平均总价单位
     */
    private String totalPriceUnit;

    /**
     * 二手房平均单价单位
     */
    private String esfPriceUnit;

    /**
     * 二手房平均总价单位
     */
    private String esfTotalPriceUnit;

    /**
     * 住宅类别(数组)：
     * （1：普通住宅，2：公寓，3：酒店式公寓，4：花园洋房，5：商住楼，6：独栋别墅，7：联排别墅，8：经济适用房， 9：廉租房，
     * 10：公共租赁房，11：定向安置房，12：两限商品房，13：自住型商品房，14：其他，15-商铺，16-写字楼，17-平房，18-车位，
     * 19-办公，20-四合院，21-住宅商铺，22-写字楼商铺，23-商务型商铺，24-市场类商铺，25-标准写字楼，
     * 26-酒店写字楼，27-别墅，28-办公别墅，29-购物中心，30-商业，31-企业独栋，32-住宅底商，33-自持租赁，34-总部园区）
     */
    private Integer[] residentialCategory;

    /**
     * 房天下楼盘Id
     */
    private String soufunNewcode;

    /**
     * 空气质量
     */
    private String airQuality;

    /**
     * 电梯配备(1-有，2-无)
     */
    private Short hasLift;

    /**
     * 活动信息
     */
    private Object activityInfo;

    /**
     * 销售许可证信息
     */
    private List<SalesLicenseInfo> salesLicenseInfo;

    /**
     * 楼盘动态
     */
    private List<SalesDynamicInfo> dynamicInfo;

    /**
     * 楼盘优惠
     */
    private String discountInfo;

    /**
     * 楼盘评级(1-广告/付费，2-优，3-良，4-一般)
     */
    private Short level;

    /**
     * 楼盘评分
     */
    private Integer score;

    /**
     * 楼栋数
     */
    private Integer buildCount;

    /**
     * 环路(1-二环以内,2-二至三环,3-三至四环,4-四至五环,5-五至六环,6-六环以外)
     */
    private Short ringRoad;

    /**
     * 供水
     */
    private String waterSupply;

    /**
     * 供电
     */
    private String electricSupply;

    /**
     * 交通信息
     */
    private String traffic;

    /**
     * 装修(数组)(1:毛坯 2:普通装修 3:精装修 4:豪华装修 5:其他 6:非毛坯 7:公共部分简单装修)
     */
    private Integer[] fitment;


    private String areaName;

    private String districtName;

    private Integer isActivity;

    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;

        if (null != pinyinName) {

            this.setPinyinInitials(pinyinName.substring(0, 1).toUpperCase());
        }
    }
}