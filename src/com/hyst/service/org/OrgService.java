package com.hyst.service.org;

import java.util.List;




import com.hyst.vo.Orgnization;

public interface OrgService {
    public  List<Orgnization> getList();
	public String addList(Orgnization organizationTbl );
	public  List<Orgnization> getAllList();
}
