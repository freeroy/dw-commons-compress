package org.developerworld.commons.compress;

/**
 * 支持类型枚举
 * 
 * @author Roy Huang
 * @version 20121112
 * 
 */
public enum SupportType {

	AR("ar"), CPIO("cpio"), DUMP("dump"), JAR("jar"), TAR("tar"), ZIP("zip"), TAR_GZIP(
			"tar.gzip"), TAR_BZIP2("tar.bzip2"), TAR_XZ("tar.xz"), JAR_PACK200(
			"jar.pack200");

	private String name;

	// 构造方法
	private SupportType(String name) {
		this.name = name;
	}

	// 覆盖方法
	@Override
	public String toString() {
		return this.name;
	}

}
