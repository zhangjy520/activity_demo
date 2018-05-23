package com.activiti.test.action;

import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.activiti.test.action.support.BaseController;
import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(TaskController.class);
	
	/**
	 * 管理流程定义
	 */
	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping("/list")
	public String list(Model model,@RequestParam(name="pageNum",defaultValue="1",required=true)int pageNum,@RequestParam(name="numPerPage",defaultValue="10",required=true)int numPerPage) {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()// 创建一个流程定义查询
				/* 指定查询条件,where条件 */
				// .deploymentId(deploymentId)//使用部署对象ID查询
				// .processDefinitionId(processDefinitionId)//使用流程定义ID查询
				// .processDefinitionKey(processDefinitionKey)//使用流程定义的KEY查询
				// .processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询

				/* 排序 */
				.orderByProcessDefinitionVersion().asc();// 按照版本的升序排列
				// .orderByProcessDefinitionName().desc()//按照流程定义的名称降序排列
				//.list();// 返回一个集合列表，封装流程定义
				// .singleResult();//返回唯一结果集
				// .count();//返回结果集数量
				// .listPage(firstResult, maxResults)//分页查询
		List<ProcessDefinition> list = processDefinitionQuery.listPage(pageNum - 1, numPerPage);
		int countTotal = Integer.parseInt(String.valueOf(processDefinitionQuery.count())); 
		Page<ProcessDefinition> res = new Page<>(pageNum,numPerPage);
		res.setRecords(list);
		res.setTotal(countTotal);
		model.addAttribute("processDefinitions", res);
		return "task-list";
	}
}
