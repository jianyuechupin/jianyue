package com.hyst.service.impl.safe;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hyst.service.safe.SafeAdminService;
import com.hyst.service.safe.impl.SafeAdminServiceImpl;
import com.hyst.vo.UserGroupPowerDetail;


/**
 * @author DongYi
 * @version 创建时间：2016年5月3日 上午11:26:28
 * 类说明
 */
public class SafeAdminServiceImplTest {
	private SafeAdminService safeAdminService;
	@Before
	public void set(){
		ApplicationContext context=new ClassPathXmlApplicationContext("com/hyst/config/applicationContext.xml");
		safeAdminService=(SafeAdminServiceImpl) context.getBean("safeAdminService");
	}
	@Test
	public void testInsertDetails(){
		List<UserGroupPowerDetail> list=new ArrayList<UserGroupPowerDetail>();
		int k=1;
		for (int i = 0; i < 57; i+=7) {
			UserGroupPowerDetail usDetail=new UserGroupPowerDetail();
			usDetail.setId(k);
			usDetail.setTableOperID(i);
			usDetail.setUserGroupId(1);
			list.add(usDetail);
			k++;
		}
		safeAdminService.addDetails(list,1);
	}
}
