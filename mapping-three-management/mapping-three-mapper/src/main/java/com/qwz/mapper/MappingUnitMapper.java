package com.qwz.mapper;

import com.qwz.model.MappingUnit;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.boot.web.server.MimeMappings;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface MappingUnitMapper extends Mapper<MappingUnit> {

    /**
     * @author  qlh
     * @date   2020/7/16
     * @desc
     * 根据资质等级分组查询单位
     **/
    @Select("select count(qualification_level) levelCount,qualification_level level from t_mapping_unit GROUP BY qualification_level")
    List<Map> selectQualification();
    /**
     * @author  qlh
     * @date   2020/7/16
     * @desc
     * 查询 技术人员
     * 特殊人员
     * 项目数量
     **/
    @Select("select count(major_type),major_type from  t_technicist where user_id=#{userId} GROUP BY major_type")
    List<Map> selectUnitTech(@Param("userId") Integer userId);
    @Select("select count(*) from t_special_post where user_id=#{userId}")
    Integer selectSpecialCount(@Param("userId") Integer userId);
    @Select("select count(*) from t_mapping_project where user_id=#{userId}")
    Integer selectProjectCount(@Param("userId") Integer userId);



    /**
     * @author  qlh
     * @date   2020/7/16
     * @desc
     * 查询 资质等级查询单位数量
     **/

    List<Map> selectUnitByType(String unitArea);
    /**
     * @author  qlh
     * @date   2020/7/16
     * @desc
     * 查询 根据资质等级查询技术人员数量
     **/
    List<Map> selectTechPersonByType(String unitArea);

    /**
     * @author  qlh
     * @date   2020/7/16
     * @desc
     * 查询 根据资质等级查询设备数量
     **/
    List<Map> selectEquipmentByType(String unitArea);

    /**
     * @Description: 查询白名单
     * @Author: Bing
     * @Date: 2020/7/16 11:35
     **/
    List<MappingUnit> selectwhiteUnit();

    /**
     * @Description: 查询黑名单
     * @Author: Bing
     * @Date: 2020/7/16 11:38
     **/
    List<MappingUnit> selectBlackUnit();

    /**
     * @author  qlh
     * @date   2020/7/17
     * @desc
     * 分页 模糊查询所有单位
     **/

    List<MappingUnit> selectAllUnitVague(String unitName);


    /**
     * @author  qlh
     * @date   2020/7/17
     * @desc
     * 分页模糊查询 未注册通过的单位
     **/
    List<MappingUnit> selectUnitNoRegister(String unitName);
    /**
     * @author  qlh
     * @date   2020/7/17
     * @desc
     * 分页模糊查询 待修改审核
     *
     **/
    List<MappingUnit> selectUnitNoUpdateAudit(String unitName);


    /**
     * @author  qlh
     * @date   2020/7/17
     * @desc
     * 查询关于此单位的 附件
     **/
    List<Map> selectResourceByUserId(Integer userId);
    /**
     * @author  qlh
     * @date   2020/7/17
     * @desc
     * 修改单位的分数
     **/
    @Update("update t_mapping_unit set score=#{score} where id=#{unitId}")
    int updateUnitScore(@Param("unitId") Long unitId,@Param("score") Integer score);
    /**
     * @author  qlh
     * @date   2020/7/17
     * @desc
     * 修改单位审核状态
     **/
    @Update("update t_mapping_unit set audit_status=#{auditStatus} where id=#{unitId}")
    int updateUnitAuditstatus(@Param("auditStatus") Integer auditStatus,@Param("unitId") Long unitId);


    /**
     * @author  qlh
     * @date   2020/7/18
     * @desc
     * 查询单位表的总行数
     **/
    @Select("select count(1) from t_mapping_unit")
    int selectUnitCount();

    /**
     * @author  qlh
     * @date   2020/7/18
     * @desc
     * 双随机抽查单位
     **/
    List<MappingUnit> selectUnitRandom(@Param("randomCount") Integer randomCount,@Param("ownedDistrict") String ownedDistrict);


    /**
     * @author  qlh
     * @date   2020/7/18
     * @desc
     * 根据单位名称 资质等级 单位地址查询单位
     **/

    List<MappingUnit> selectUnitBynameAndLevelAndArea(@Param("unitName") String unitName,@Param("unitArea") String unitArea,
                                                      @Param("unitLevel") String unitLevel);


    
}