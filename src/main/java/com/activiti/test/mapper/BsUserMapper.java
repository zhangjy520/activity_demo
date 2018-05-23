package com.activiti.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.activiti.test.entity.BsUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jun
 * @since 2018-05-14
 */
public interface BsUserMapper extends BaseMapper<BsUser> {

	BsUser selectUserByNameAndPassword(@Param("name")String name, @Param("pwd")String pwd);

	List<BsUser> queryAllUser(@Param("searchText")String searchText, RowBounds page);

	List<BsUser> queryUsersByRoleCode(@Param("code")String code);

}