package com.activiti.test.service;

import com.activiti.test.entity.BsUserAct;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jun
 * @since 2018-05-22
 */
public interface IBsUserActService extends IService<BsUserAct> {

	void askForLeave(Long id,String name, Integer day, String reason) throws Exception;
	
}
