package edu.etime.yqxdc.services.interfaces;
/**
 * 等候队列业务逻辑层
 * @author 孤心
 *
 */

import edu.etime.yqxdc.dto.QueueDto;

public interface QueueService {

	/**
	 * 查询等候队列
	 * @param openid
	 * @return
	 */
	public QueueDto selectQueue(String openid);
}
