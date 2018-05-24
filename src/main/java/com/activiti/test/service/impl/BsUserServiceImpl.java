package com.activiti.test.service.impl;

import com.activiti.test.entity.BsUser;
import com.activiti.test.entity.BsUserRole;
import com.activiti.test.mapper.BsUserMapper;
import com.activiti.test.mapper.BsUserRoleMapper;
import com.activiti.test.service.IBsUserService;
import com.activiti.test.service.support.BaseServiceImpl;
import com.activiti.test.utils.MD5;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jun
 * @since 2018-05-14
 */
@Service
public class BsUserServiceImpl extends BaseServiceImpl<BsUserMapper, BsUser> implements IBsUserService {
	
	@Autowired
	private BsUserMapper bsUserMapper;
	
	@Autowired
	private BsUserRoleMapper bsUserRoleMapper;

	@Override
	public BsUser getUserByNameAndPassword(String name, String pwd) {
		return bsUserMapper.selectUserByNameAndPassword(name,pwd);
	}

	@Override
	public Page<BsUser> queryAllUser(int pageNum, int numPerPage, String searchText) {
		Page<BsUser> users = new Page<>(pageNum, numPerPage);
		List<BsUser> records = bsUserMapper.queryAllUser(searchText,users);
		users.setRecords(records);
		return users;
	}

	@Transactional
	@Override
	public void addUser(String name, String loginName,String roleCode) throws RuntimeException{
		BsUser user = new BsUser();
		user.setLoginName(loginName);
		user.setName(name);
		user.setPassWord(MD5.toMD5("123456"));
		Integer num1 = bsUserMapper.insert(user);
		if(num1.equals(0)) {
			throw new RuntimeException();
		}
		BsUserRole userRole = new BsUserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleCode(roleCode);
		Integer num2 = bsUserRoleMapper.insert(userRole);
		if(num2.equals(0)) {
			throw new RuntimeException();
		}
	}

	@Override
	public List<BsUser> queryUsersByRoleCode(String code) {
		return bsUserMapper.queryUsersByRoleCode(code);
	}

	@Transactional
	@Override
	public void delUser(Long id) throws RuntimeException{
		Integer num2 = bsUserRoleMapper.delByUserId(id);
		if(num2.equals(0)) {
			throw new RuntimeException();
		}
		Integer num1 = bsUserMapper.deleteById(id);
		if(num1.equals(0)) {
			throw new RuntimeException();
		}
	}
	
}
