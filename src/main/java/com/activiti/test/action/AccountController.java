package com.activiti.test.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activiti.test.action.support.BaseController;
import com.activiti.test.beans.DWZResponse;
import com.activiti.test.service.IBsUserActService;
import com.activiti.test.utils.DWZResponseUtil;
import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController{

	private static Logger log = LoggerFactory.getLogger(AccountController.class);
	
	
	/**
	 * 任务管理，是activiti的任务服务类。可以从这个类中获取任务的信息。
	 */
	@Autowired
	private TaskService taskService;
	
	/**
	 * 历史管理(执行完的数据的管理)，是activiti的查询历史信息的类。在一个流程执行完成后，这个对象为我们提供查询历史信息。
	 */
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private IBsUserActService bsUserActService;
	
	@RequestMapping("/list")
	public String list() {
		return "account";
	}
	
	@RequestMapping("/leave")
	public String leave(Model model) {
		return "leave";
	}
	
	@RequestMapping("/myLeave")
	public String myLeave(Model model,@RequestParam(name="pageNum",defaultValue="1",required=true)int pageNum,@RequestParam(name="numPerPage",defaultValue="10",required=true)int numPerPage) {
		Long id = getCurrentUserId();
		
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery().includeProcessVariables().startedBy(id.toString());
		
		List<HistoricProcessInstance> list = historicProcessInstanceQuery.listPage(pageNum - 1, numPerPage);
		int countTotal = Integer.parseInt(String.valueOf(historicProcessInstanceQuery.count())); 
		Page<HistoricProcessInstance> res = new Page<>(pageNum,numPerPage);
		res.setRecords(list);
		res.setTotal(countTotal);
		model.addAttribute("historicProcessInstanceList", res);
		return "myLeave";
	}
	
	@RequestMapping("/askForLeave")
	@ResponseBody
	public DWZResponse askForLeave(Model model,Integer day,String reason) {
		Long id = getCurrentUserId();
		String name = getCurrentUserName();
		try {
			bsUserActService.askForLeave(id,name,day,reason);
		} catch (Exception e) {
			log.error("请假失败！");
			e.printStackTrace();
			return DWZResponseUtil.callbackFail("500","请假失败");
		}
		return DWZResponseUtil.callbackSuccess("请假成功");
	}
	
	@RequestMapping("/groupTask")
	public String groupTask(Model model,@RequestParam(name="pageNum",defaultValue="1",required=true)int pageNum,@RequestParam(name="numPerPage",defaultValue="10",required=true)int numPerPage) {
		Long id = getCurrentUserId();
		TaskQuery taskQuery = taskService.createTaskQuery().includeProcessVariables()
				.taskCandidateUser(id.toString())//创建任务查询对象  
                //查询条件（where部分
				//.taskAssignee(assignee)//指定个人任务查询，指定办理人  
                //排序
                .orderByTaskCreateTime().asc();//使用创建时间的升序排列  
                //返回结果集
                //.listPage(page, size);
		List<Task> list = taskQuery.listPage(pageNum - 1, numPerPage);
		int countTotal = Integer.parseInt(String.valueOf(taskQuery.count())); 
		Page<Task> res = new Page<>(pageNum,numPerPage);
		res.setRecords(list);
		res.setTotal(countTotal);
		model.addAttribute("tasks", res);
		return "groupTask";
	}
	
	@RequestMapping("/myTask")
	public String myTask(Model model,@RequestParam(name="pageNum",defaultValue="1",required=true)int pageNum,@RequestParam(name="numPerPage",defaultValue="10",required=true)int numPerPage) {
		Long id = getCurrentUserId();
		TaskQuery taskQuery = taskService.createTaskQuery().includeProcessVariables()
				//.taskCandidateUser(id.toString())//创建任务查询对象  
                //查询条件（where部分
				.taskAssignee(id.toString())//指定个人任务查询，指定办理人  
                //排序
                .orderByTaskCreateTime().asc();//使用创建时间的升序排列  
                //返回结果集
                //.listPage(page, size);
		List<Task> list = taskQuery.listPage(pageNum - 1, numPerPage);
		int countTotal = Integer.parseInt(String.valueOf(taskQuery.count())); 
		Page<Task> res = new Page<>(pageNum,numPerPage);
		res.setRecords(list);
		res.setTotal(countTotal);
		model.addAttribute("tasks", res);
		return "myTask";
	}
	
	/**
	 * 
	 * 认领任务
	 * */
	@RequestMapping("/claim")
	@ResponseBody
	public DWZResponse claim(Model model,Long taskId) {
		Long id = getCurrentUserId();
		try {
			taskService.claim(taskId.toString() ,id.toString()); //认领任务，让用户成为任务的执行者  
		} catch (Exception e) {
			log.error("认领失败！");
			e.printStackTrace();
			return DWZResponseUtil.callbackFail("500","认领失败");
		}
		return DWZResponseUtil.callbackSuccess("认领成功");
	}
	
	/**
	 * 
	 * 完成任务
	 * */
	@RequestMapping("/complete")
	@ResponseBody
	public DWZResponse complete(Model model,Long taskId) {
		try {
			taskService.complete(taskId.toString());  //
		} catch (Exception e) {
			log.error("操作失败！");
			e.printStackTrace();
			return DWZResponseUtil.callbackFail("500","操作失败");
		}
		return DWZResponseUtil.callbackSuccess("操作成功");
	}
}
