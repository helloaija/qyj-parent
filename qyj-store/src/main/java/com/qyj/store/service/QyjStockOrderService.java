package com.qyj.store.service;

import com.qyj.common.page.PageBean;
import com.qyj.common.page.PageParam;
import com.qyj.common.page.ResultBean;
import com.qyj.store.entity.QyjStockOrderEntity;
import com.qyj.store.vo.QyjOrderBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口-进货单
 * @author CTF_stone
 */
public interface QyjStockOrderService {
	
	/**
	 * 获取订单分页数据
	 * @param pageParam 分页信息
	 * @param paramMap 查询参数
	 * @return
	 * @throws Exception
	 */
	PageBean listStockOrderAndProductPage(PageParam pageParam, Map<String, Object> paramMap) throws Exception;

	/**
	 * 添加进货订单
	 * @param stockOrder
	 * @return
	 * @throws Exception
	 */
	ResultBean addStockOrder(QyjStockOrderEntity stockOrder) throws Exception;

	/**
	 * 获取进货单数据
	 * @param stockId
	 * @return
	 */
	ResultBean loadStockOrderAndProductByStockId(Long stockId);

	/**
	 * 更新进货订单
	 * @param stockOrder
	 * @return
	 * @throws Exception
	 */
	ResultBean editStockOrder(QyjStockOrderEntity stockOrder) throws Exception;

	/**
	 * 根据stockId删除进货单
	 * @param stockId
	 * @return
	 * @throws Exception
	 */
	ResultBean deleteStockOrder(Long stockId) throws Exception;
	
}
