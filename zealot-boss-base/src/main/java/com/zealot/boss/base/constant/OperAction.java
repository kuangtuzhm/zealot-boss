package com.zealot.boss.base.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum OperAction {

	OPERATOR_LOGIN(1,"登录"),
	OPERATOR_ADD(2,"新增"),
	OPERATOR_MODIFY(3,"修改"),
	OPERATOR_DELETE(4,"删除"),
	OPERATOR_EXPORT(5,"导出"),
	OPERATOR_GRANT(6,"授权");
	
	// 成员变量
	private String name;
	private Integer index;
	
	private OperAction(Integer index,String name)
	{
		this.name = name;
		this.index = index;
	}
	
	private static final  Map<Integer, String> map = new HashMap<Integer, String>();  
	
	static 
	{
		for(OperAction oa : OperAction.values())
		{
			map.put(oa.index, oa.name);
		}
	}
	
	public static String getName(Integer index)
	{
		return map.get(index);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
	public static Map<Integer,String> map(){
		return map;
	}
	
	public static void main(String [] args)
	{
		Map<Integer,String> m = OperAction.map();
		Set<Integer> keys = m.keySet();
		for(Integer key : keys)
		{
			System.out.println(m.get(key));
		}
	}
}
