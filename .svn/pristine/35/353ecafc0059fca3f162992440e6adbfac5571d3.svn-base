package com.activiti.test.entity;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jun
 * @since 2018-05-22
 */
@TableName("BS_USER_ACT")
public class BsUserAct extends Model<BsUserAct> {

    private static final long serialVersionUID = 1L;

	@TableId(value="ID", type= IdType.AUTO)
	private Long id;
	@TableField("ACT_ID")
	private String actId;
	@TableField("USER_ID")
	private Long userId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActId() {
		return actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "BsUserAct{" +
			"id=" + id +
			", actId=" + actId +
			", userId=" + userId +
			"}";
	}
}
