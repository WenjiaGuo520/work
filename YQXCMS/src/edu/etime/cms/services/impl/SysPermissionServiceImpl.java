package edu.etime.cms.services.impl;

import java.util.UUID;

import edu.etime.cms.dao.SysPermissionDao;
import edu.etime.cms.dto.SysRoleDto;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.SysFunction;
import edu.etime.cms.pojo.SysPermission;
import edu.etime.cms.services.interfaces.SysPermissionService;

public class SysPermissionServiceImpl implements SysPermissionService {
	private SysPermissionDao dao = new SysPermissionDao();
	@Override
	public boolean editPermission(SysRoleDto pers) {
		//判断是否上传了角色id
		if(pers.getRid()==null) {
			return false;
		}
		
		//删除所有权限
		int i = dao.deleteByRid(pers.getRid());
		//System.out.println("从syspermission删除:"+i+"条记录:rid==="+pers.getRid());
		int count = 0;
		for (String fid : pers.getList()) {
			count += dao.insertPerssion(UUID.randomUUID().toString(),pers.getRid(), fid);
		}
		return count==0?false:true;		
	}

}
