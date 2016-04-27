package com.hyst.vo;
/**
 * 用户部门视图
 */
public class UserDeptView{
	/**属性描述：用户ID，外键 */
	private int uid;
	/**属性描述：用户名 */
	private String uname;
	/**属性描述：性别 */
	private String sex;
	/**属性描述：登录名 */
	private String post;
	/**属性描述：部门ID */
	private int did;
	/**属性描述：部门名称 */
	private String dname;
	
	
	
	@Override
	public String toString() {
		return "UserDeptView [uid=" + uid + ", 用户名=" + uname + ", 性别=" + sex
				+ ", 登录名=" + post + ", did=" + did + ", 部门名称=" + dname + "]";
	}
	public int getUid(){
		return this.uid;
	}
	public String getUname(){
		return this.uname;
	}
	public String getSex(){
		return this.sex;
	}
	public String getPost(){
		return this.post;
	}
	public int getDid(){
		return this.did;
	}
	public String getDname(){
		return this.dname;
	}
	public void setUid(int uid){
		this.uid = uid;
	}
	public void setUname(String uname){
		this.uname = uname;
	}
	public void setSex(String sex){
		this.sex = sex;
	}
	public void setPost(String post){
		this.post = post;
	}
	public void setDid(int did){
		this.did = did;
	}
	public void setDname(String dname){
		this.dname = dname;
	}
	
}