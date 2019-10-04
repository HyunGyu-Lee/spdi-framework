package com.spdiframework.beans.metadata;

/***
 * @author dlgusrb0808@gmail.com
 */
public class BeanDependency {
	private Class<?> requireType;
	private String requireName;

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
