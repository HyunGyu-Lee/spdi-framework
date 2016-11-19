package com.leeframework.beans.metadata;

/**
 * requireType : 필요한 타입
 * requireName : 필요한 이름
 * 주입 방식별로 빈을 검색할때 사용할 의존정보
 * BeanFactoryMetaData내 특정 빈에 대해 일대다구조로 존재함(없을수도 있음)
 * */
public class BeanDependency {
	private Class<?> requireType;
	private String requireName;
	
	public BeanDependency() {}

	public BeanDependency(Class<?> requireType, String requireName) {
		this.requireType = requireType;
		this.requireName = requireName;
	}
	public Class<?> getRequireType() {
		return requireType;
	}
	public void setRequireType(Class<?> requireType) {
		this.requireType = requireType;
	}
	public String getRequireName() {
		return requireName;
	}
	public void setRequireName(String requireName) {
		this.requireName = requireName;
	}

	@Override
	public String toString() {
		return "BeanDependency [requireType=" + requireType + ", requireName=" + requireName + "]";
	}
	
}
