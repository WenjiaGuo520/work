package edu.etime.yqxdc.services.interfaces;
/**
 * ��Ʒ����
 * @author zyh
 *
 */

import java.util.List;

import edu.etime.yqxdc.dto.GoodsDto;
import edu.etime.yqxdc.pojo.Goods;
import edu.etime.yqxdc.pojo.SysRole;
import edu.etime.yqxdc.tools.Pagers;

public interface GoodsService {

	/**
	 * ������Ʒ����
	 * @param goods
	 * @return
	 */
	public int insertGoods(Goods goods);
	/**
	 * ��ѯ��Ʒ�б�
	 * @param goods
	 * @return
	 */
	public List<GoodsDto> selectGoods(Goods goods);
	/**
	 * ��ҳ
	 * @param goods
	 * @param pagenum
	 * @param pagesize
	 * @return
	 */
	Pagers<GoodsDto> selectSysRolepage(Goods goods,int pagenum,int pagesize);
	
	/**
	 * ��ʼ��
	 * @param id
	 * @return
	 */
	Goods selectGoodsById(String id);
	/**
	 * �༭
	 * @param goods
	 * @return
	 */
	int updateGoods(Goods goods);
}
