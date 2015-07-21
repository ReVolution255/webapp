package main.java;

import java.util.List;
import main.java.UserGroups;
import main.java.UserGroupsExample;
import org.apache.ibatis.annotations.Param;

public interface UserGroupsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int countByExample(UserGroupsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int deleteByExample(UserGroupsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int insert(UserGroups record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int insertSelective(UserGroups record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    List<UserGroups> selectByExample(UserGroupsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    UserGroups selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int updateByExampleSelective(@Param("record") UserGroups record, @Param("example") UserGroupsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int updateByExample(@Param("record") UserGroups record, @Param("example") UserGroupsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int updateByPrimaryKeySelective(UserGroups record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_groups
     *
     * @mbggenerated Wed Jul 22 01:57:37 MSK 2015
     */
    int updateByPrimaryKey(UserGroups record);
}