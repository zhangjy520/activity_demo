package com.activiti.test.service.impl;

import com.activiti.test.entity.BsUserAct;
import com.activiti.test.mapper.BsUserActMapper;
import com.activiti.test.service.IBsUserActService;
import com.activiti.test.service.support.BaseServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jun
 * @since 2018-05-22
 */
@Service
public class BsUserActServiceImpl extends BaseServiceImpl<BsUserActMapper, BsUserAct> implements IBsUserActService {

	@Autowired
	private BsUserActMapper bsUserActMapper;
	
	/**
	 * 执行管理，包括启动、推进、删除流程实例等操作，是activiti的流程执行服务类。可以从这个服务类中获取很多关于流程执行相关的信息。
	 */
	@Autowired
	private RuntimeService runtimeService;
	
	/**
	 * 组织机构管理
	 */
	@Autowired
	private IdentityService identityService;
	
	@Override
	public void askForLeave(Long id,String name, Integer day, String reason) throws Exception{
		Map<String, Object> variables = new HashMap<String, Object>(); 
		variables.put("name", name);//设置请假人名字
        variables.put("day", day);
        variables.put("reason", reason);
        variables.put("askTime", new Date());//设置全局变量，申请日期
        identityService.setAuthenticatedUserId(id.toString());
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess",variables);
		BsUserAct bsUserAct = new BsUserAct();
		bsUserAct.setUserId(id);
		bsUserAct.setActId(processInstance.getId());
		Integer num = bsUserActMapper.insert(bsUserAct);
		if(num.equals(0)) {
			throw new RuntimeException();
		}
	}
	
}
