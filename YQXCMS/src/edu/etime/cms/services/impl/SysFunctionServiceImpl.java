package edu.etime.cms.services.impl;

import java.util.List;
import java.util.UUID;

import edu.etime.cms.dao.SysFunctionDao;
import edu.etime.cms.dto.SysFunctionDto;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.SysFunction;
import edu.etime.cms.services.interfaces.SysFunctionService;

public class SysFunctionServiceImpl implements SysFunctionService {
	private SysFunctionDao dao = new SysFunctionDao();
	
	@Override
	public List<SysFunctionDto> getList() {
		return dao.selectForList();
	}

	@Override
	public ReturnBean add(SysFunction fun) {
		
		if(fun.getPfid().equals("-1")) {
			//根据根节点数设置根节点的fid,如数据库中有2个根节点,那么增加的根节点id为3
			//这样方便设置菜单.
			int count = dao.selectForRootFunction();
			fun.setFid(count+1+"");
		}else {
			fun.setFid(UUID.randomUUID().toString());
		}
		
		int count = dao.selectCountColumnByFun(fun);
		//返回信息封装类
		ReturnBean rb = new ReturnBean();
		if(count != 0) {
			rb.setFlag(false);
			rb.setMsg("添加失败,您输入的功能信息字段有重复,请重新输入!");
			return rb;
		}else {
			//等于0,说明没有重复字段
			int insert = dao.insertSysFunction(fun);
			if(insert==0) {
				rb.setFlag(false);
				rb.setMsg("添加失败,插入数据异常!!!");
				return rb;
			}
		}
		//还没有返回,说明添加成功了
		rb.setFlag(true);
		rb.setMsg("添加成功");
		return rb;
	}

	@Override
	public List<SysFunctionDto> getListByRid(String roleid) {
		return dao.selectForListByRid(roleid);
	}

	@Override
	public List<SysFunction> getRootList() {
		return dao.selectRoot();
	}

	@Override
	public boolean editFunction(SysFunction fun) {
		int count = dao.updateFunctionBuId(fun);
		return count==0?false:true;
	}

	@Override
	public ReturnBean save(SysFunction fun) {
		if(fun.getFid()==null) {
			//说明是增加功能
			fun.setFid(UUID.randomUUID().toString());
			return add(fun);
		}
		//查询数据库有无记录
		int count = dao.selectCountById(fun.getFid());
		ReturnBean rb = new ReturnBean();
		if(count>0) {
			//数据库中有这条记录,表明这是在修改function
			boolean b = editFunction(fun);
			rb.setFlag(b);
			rb.setMsg("修改功能"+ (b?"成功":"失败"));
			return rb;
		}else {
			//这是在新增一个功能,注意一般走到这里说明数据库表有异常了.!
			//返回增加的状态
			return add(fun);		
		}
	}

}
