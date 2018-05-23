package com.activiti.test.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.activiti.test.entity.BsRole;
import com.activiti.test.entity.BsUser;
import com.activiti.test.service.IBsRoleService;
import com.activiti.test.service.IBsUserService;
import com.activiti.test.utils.DWZResponseUtil;
import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IBsUserService bsUserService;
	
	@Autowired
	private IBsRoleService bsRoleService;

	@RequestMapping("/list")
	public String list(Model model,@RequestParam(name="pageNum",defaultValue="1",required=true)int pageNum,@RequestParam(name="numPerPage",defaultValue="10",required=true)int numPerPage,String searchText) {
		Page<BsUser> users = bsUserService.queryAllUser(pageNum,numPerPage,searchText);
		model.addAttribute("users", users);
		return "user";
	}
	
	@RequestMapping("/add.html")
	public String addHtml(Model model) {
		List<BsRole> roles = bsRoleService.queryAllRoles();
		model.addAttribute("roles", roles);
		return "add-user-dialog";
	}
	
	@RequestMapping("/add.json")
	@ResponseBody
	public DWZResponse addJson(Model model,String name,String loginName,String roleCode) {
		try {
			bsUserService.addUser(name,loginName,roleCode);
		} catch (Exception e) {
			log.error("添加职员失败！");
			e.printStackTrace();
			return DWZResponseUtil.callbackFail("500","添加用户失败");
		}
		return DWZResponseUtil.callbackSuccess("添加用户成功");
	}
	
	@RequestMapping("/del.json")
	@ResponseBody
	public DWZResponse del(Model model,Long id) {
		try {
			bsUserService.delUser(id);
		} catch (Exception e) {
			log.error("删除用户失败！");
			e.printStackTrace();
			return DWZResponseUtil.callbackFail("500","删除用户失败");
		}
		return DWZResponseUtil.callbackSuccess("删除用户成功");
	}
}
