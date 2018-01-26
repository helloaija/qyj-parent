package com.qyj.web.common.wechat.menuUtils;

/**
 * 复杂按钮（父按钮）
 * 包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
 * @author lirui
 *
 * @date 2014年7月21日 下午3:06:58
 */
public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
}