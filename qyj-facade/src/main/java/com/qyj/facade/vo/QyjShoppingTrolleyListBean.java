package com.qyj.facade.vo;

import java.io.Serializable;
import java.util.List;

public class QyjShoppingTrolleyListBean implements Serializable {

	private static final long serialVersionUID = -4967475053293629627L;

	private List<QyjShoppingTrolleyBean> list;

	public List<QyjShoppingTrolleyBean> getList() {
		return list;
	}

	public void setList(List<QyjShoppingTrolleyBean> list) {
		this.list = list;
	}

}
