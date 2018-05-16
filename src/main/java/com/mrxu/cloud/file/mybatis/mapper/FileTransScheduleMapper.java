package com.mrxu.cloud.file.mybatis.mapper;

import com.mrxu.cloud.file.mybatis.entity.FileTransSchedule;
import com.mrxu.cloud.file.mybatis.entity.FileTransScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FileTransScheduleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int countByExample(FileTransScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int deleteByExample(FileTransScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int insert(FileTransSchedule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int insertSelective(FileTransSchedule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    List<FileTransSchedule> selectByExampleWithRowbounds(FileTransScheduleExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    List<FileTransSchedule> selectByExample(FileTransScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    FileTransSchedule selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int updateByExampleSelective(@Param("record") FileTransSchedule record, @Param("example") FileTransScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int updateByExample(@Param("record") FileTransSchedule record, @Param("example") FileTransScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int updateByPrimaryKeySelective(FileTransSchedule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    int updateByPrimaryKey(FileTransSchedule record);
}