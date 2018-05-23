package com.activiti.test.listener;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.activiti.test.entity.BsUser;
import com.activiti.test.service.IBsUserService;

public class HRListener extends SpringBeanAutowiringSupport implements TaskListener{
	
	@Autowired
	private IBsUserService bsUserService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		List<BsUser> users = bsUserService.queryUsersByRoleCode("R0002");
		List<String> ids = new ArrayList<>();
		for (BsUser bsUser : users) {
			ids.add(bsUser.getId().toString());
		}
		delegateTask.addCandidateUsers(ids);
	}

}
