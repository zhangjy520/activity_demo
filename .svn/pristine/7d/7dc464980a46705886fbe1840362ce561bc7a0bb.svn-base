package com.activiti.test.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.image.ProcessDiagramGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bpmn")
public class BpmnController {

	private static Logger log = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;

	/**
	 * 管理流程定义
	 */
	@Autowired
	private RepositoryService repositoryService;


	/**
	 * 历史管理(执行完的数据的管理)，是activiti的查询历史信息的类。在一个流程执行完成后，这个对象为我们提供查询历史信息。
	 */
	@Autowired
	private HistoryService historyService;
	
	
	/**
	 * 得到流程定义图
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return
	 */
	@RequestMapping("/showProcessDefinitionImgView")
	public String showProcessDefinitionImgView(Model model, String processDefinitionId) {
		model.addAttribute("processDefinitionId", processDefinitionId);
		return "processDefinitionImg";
	}
	
	
	/**
	 * 得到流程定义图
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return
	 */
	@RequestMapping("/showProcessDefinitionImg")
	public void showProcessDefinitionImg(Model model,HttpServletResponse response, String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).latestVersion().singleResult();  
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());  
        // 不使用spring请使用下面的代码  
        //Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
  
        // 使用spring注入引擎请使用下面的这行代码  
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);  
        ProcessDiagramGenerator a = processEngineConfiguration.getProcessDiagramGenerator();  
        InputStream is = a.generateDiagram(bpmnModel, "png", "宋体","宋体", "宋体", null, 1.0);  
        try {
			int size = is.available();
			byte data[] = new byte[size];
			is.read(data);
			response.setContentType("image/png"); // 设置返回的文件类型
			OutputStream os = response.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
		} catch (IOException e) {
			log.error("读写流程图时出现异常！");
		}
	}
	 

	/**
	 * 得到带有高亮节点的流程图
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return
	 */
	@RequestMapping("/showBpmnView")
	public String showBpmnView(Model model, String processId) {
		// 获取历史流程实例
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).includeProcessVariables().singleResult();
		model.addAttribute("processInstance", processInstance);
		return "bpmnImg";
	}

	@RequestMapping("/showBpmnImg")
	public void showBpmnImg(HttpServletResponse response, String processId) {
		InputStream is = traceProcessImage(processId);
		try {
			int size = is.available();
			byte data[] = new byte[size];
			is.read(data);
			response.setContentType("image/png"); // 设置返回的文件类型
			OutputStream os = response.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
		} catch (IOException e) {
			log.error("读写流程图时出现异常！");
		}
	}

	private InputStream traceProcessImage(String processId) {
		// 获取历史流程实例
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();
		// 获取流程图
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
		Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
		
		ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
		ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

		List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processId).list();
		// 高亮环节id集合
		List<String> highLightedActivitis = new ArrayList<String>();
		// 高亮线路id集合
		List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);

		for (HistoricActivityInstance tempActivity : highLightedActivitList) {
			String activityId = tempActivity.getActivityId();
			highLightedActivitis.add(activityId);
		}
		try {
			// 中文显示的是口口口，设置字体就好了
			InputStream is = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows,"宋体", "宋体", "宋体", null, 1.0);
			return is;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("生成流程图异常");
		}
		return null;
	}

	/**
	 * 获取需要高亮的线
	 * 
	 * @param processDefinitionEntity
	 * @param historicActivityInstances
	 * @return
	 */
	private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,List<HistoricActivityInstance> historicActivityInstances) {
		List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
		for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstances.get(i).getActivityId());// 得到节点定义的详细信息
			List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
			ActivityImpl sameActivityImpl1 = processDefinitionEntity.findActivity(historicActivityInstances.get(i + 1).getActivityId());
			// 将后面第一个节点放在时间相同节点的集合里
			sameStartTimeNodes.add(sameActivityImpl1);
			for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
				HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
				HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点
				if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {
					// 如果第一个节点和第二个节点开始时间相同保存
					ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
					sameStartTimeNodes.add(sameActivityImpl2);
				} else {
					// 有不相同跳出循环
					break;
				}
			}
			List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();// 取出节点的所有出去的线
			for (PvmTransition pvmTransition : pvmTransitions) {
				// 对所有的线进行遍历
				ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
				// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
				if (sameStartTimeNodes.contains(pvmActivityImpl)) {
					highFlows.add(pvmTransition.getId());
				}
			}
		}
		return highFlows;
	}
}
