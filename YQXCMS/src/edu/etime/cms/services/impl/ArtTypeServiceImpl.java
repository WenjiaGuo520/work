package edu.etime.cms.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import edu.etime.cms.dao.ArtTypeDao;
import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.services.interfaces.ArtTypeService;

public class ArtTypeServiceImpl implements ArtTypeService {
	//注入持久层对象
	private ArtTypeDao typeDao = new ArtTypeDao();
	
	@Override
	public boolean addArtType(ArtType artType) {
		int count = typeDao.insertByArtType(artType);
		return count==0?false:true;
	}

	@Override
	public PageBean<ArtType> getArtTypeList(Map<String, String[]> map) {
		//默认当前页为1
		int currentPage = 1;
		//默认每页显示5条记录
		int rows = 5;
		//创建PageBean对象
        PageBean<ArtType> pb = new PageBean<>();
        //如果map中没有currentPage,rows
        if(map.containsKey("currentPage")){
            currentPage = Integer.parseInt(map.get("currentPage")[0]);
        }
        if(map.containsKey("rows")){
            rows = Integer.parseInt(map.get("rows")[0]);
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //limit 查询开始位置
        int start = (currentPage-1) * rows;
        //结束位置
        int totalCount = typeDao.selectCount(map);
        List<ArtType> list = typeDao.selectAllByPage(start,rows,map);
        //得到总页数
        int totalPage =totalCount % rows == 0?totalCount/rows:totalCount/rows+1;
        pb.setTotalPage(totalPage);
        pb.setTotalCount(totalCount);
        pb.setList(list);

        return pb;
	}

	@Override
	public boolean delTypeById(String[] tids) {
		int count = typeDao.deleteById(tids);
		return count==0?false:true;
	}

	@Override
	public List<ArtType> getAllArtType() {
		
		return typeDao.selectAll();
	}

	@Override
	public boolean edit(ArtType type) {
		int count = typeDao.updateById(type);
		return count==0?false:true;
	}

	@Override
	public List<ArtType> getNav() {	
		return typeDao.selectAllOrderByTsort();
	}

}
