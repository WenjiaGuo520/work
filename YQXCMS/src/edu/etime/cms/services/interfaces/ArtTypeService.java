package edu.etime.cms.services.interfaces;

import java.util.List;
import java.util.Map;

import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.PageBean;

/**
 * ArtType业务逻辑接口
 * @author 1
 *
 */
public interface ArtTypeService {
	/**
	 * 	增加文章类型
	 * @param artType 要增加的文章类型
	 * @return
	 */
	boolean addArtType(ArtType artType);
	/**
	 * 得到分页数据
	 * @param map 得到的参数
	 * @return
	 */
	PageBean<ArtType> getArtTypeList(Map<String, String[]> map);
	/**
	 * 根据id属性删除相应的arttype
	 * @param tids
	 * @return
	 */
	boolean delTypeById(String[] tids);
	/**
	 * 得到所有的文章类型
	 * @return
	 */
	List<ArtType> getAllArtType();
	/**
	 * 修改文章类型
	 * @param type
	 * @return
	 */
	boolean edit(ArtType type);
	/**
	 * 获得导航栏列表
	 * @return 一个有顺序的集合
	 */
	List<ArtType> getNav();
}
