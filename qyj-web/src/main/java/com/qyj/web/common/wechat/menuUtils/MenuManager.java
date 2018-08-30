package com.qyj.web.common.wechat.menuUtils;

/**
 * 菜单管理器类
 */
public class MenuManager {
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wx3830cbb607084fc5";
		// 第三方用户唯一凭证密钥
		String appSecret = "f23108c1cc45fd4d9d23229be93de10a";

		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);
		System.out.println(at.getToken());
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				System.out.println("菜单创建成功！");
			else
				System.out.println("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 * @return
	 */
	private static Menu getMenu() {
		CommonButton btn11 = new CommonButton();
		btn11.setName("平台简介");
		btn11.setType("click");
		btn11.setKey("11");

		CommonButton btn12 = new CommonButton();
		btn12.setName("公司展示");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("人才招聘");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("实时新闻");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn32 = new CommonButton();
		btn32.setName("电影排行榜");
		btn32.setType("click");
		btn32.setKey("32");

		CommonButton btn33 = new CommonButton();
		btn33.setName("幽默笑话");
		btn33.setType("click");
		btn33.setKey("33");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("汇咨询");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		ViewButton viewBtn = new ViewButton();
		viewBtn.setName("哎哟");
		viewBtn.setType("view");
		viewBtn.setUrl("http://www.huilc.cn");

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("汇投资");
		mainBtn3.setSub_button(new CommonButton[] { btn32, btn33 });

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, viewBtn, mainBtn3 });

		return menu;
	}
}
