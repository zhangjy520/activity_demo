package com.activiti.test.service.impl;

import com.activiti.test.entity.BsRole;
import com.activiti.test.mapper.BsRoleMapper;
import com.activiti.test.service.IBsRoleService;
import com.activiti.test.service.support.BaseServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jun
 * @since 2018-05-14
 */
@Service
public class BsRoleServiceImpl extends BaseServiceImpl<BsRoleMapper, BsRole> implements IBsRoleService {
	
	@Autowired
	private BsRoleMapper bsRoleMapper;

	@Override
	public List<BsRole> queryAllRoles() {
		Wrapper<BsRole> wrapper = new EntityWrapper<>();
		wrapper.orderBy("ROLE_CODE", false);
		return bsRoleMapper.selectList(wrapper);
	}
	
}
