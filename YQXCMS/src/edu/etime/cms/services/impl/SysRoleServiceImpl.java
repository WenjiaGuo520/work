package edu.etime.cms.services.impl;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import edu.etime.cms.dao.SysRoleDao;
import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.SysRole;
import edu.etime.cms.services.interfaces.SysRoleService;

public class SysRoleServiceImpl implements SysRoleService {
	private SysRoleDao dao = new SysRoleDao();
	@Override
	public PageBean<SysRole> getPage(Map<String, String[]> map) {
		PageBean<SysRole> pb = new PageBean<SysRole>();
		int currentPage = 1;//默认为1
		int rows = 5;//默认为5
		if (map.containsKey("currentPage")) {
			currentPage = Integer.parseInt(map.get("currentPage")[0]);
		}
		if(map.containsKey("rows")) {
			rows = Integer.parseInt(map.get("rows")[0]);
		}
		pb.setCurrentPage(currentPage);
		pb.setRows(rows);
		int totalCount = dao.selectCount(map);
		pb.setTotalCount(totalCount);
		int totalPage = totalCount % rows == 0?totalCount/rows:totalCount/rows+1;
		pb.setTotalPage(totalPage);
		
		int start = (currentPage-1)*rows;
	
		List<SysRole> list = dao.selectAllByPage(start, rows, map);
		pb.setList(list);
		return pb;
	}
	@Override
	public boolean deleteRoleByIds(String[] ids) {
		int count = 0;
		for (String rid : ids) {
			count += dao.updateStateById(0, rid);
		}
		return count==0?false:true;
	}
	@Override
	public ReturnBean add(SysRole role) {
		ReturnBean rb = new ReturnBean();
		if(role !=null && role.getRname()!=null && role.getRstate()!=null && !"".equals(role.getRname()) && !"".equals(role.getRdesc())) {
			int count = dao.insertRole(role);
			if (count==0) {
				rb.setFlag(false);
				rb.setMsg("数据库异常,添加失败!");
			}else {
				rb.setFlag(true);
				rb.setMsg("角色添加成功!");
			}
		}else {
			rb.setFlag(false);
			rb.setMsg("添加失败,不能传递空参数!");
		}
		return rb;
	}
	
	@Override
	public boolean edit(SysRole role) {
		int count = dao.updateRoleById(role);
		return count==0?false:true;
	}
	@Override
	public List<SysRole> getAll() {
		return dao.selectForList();
	}

}
