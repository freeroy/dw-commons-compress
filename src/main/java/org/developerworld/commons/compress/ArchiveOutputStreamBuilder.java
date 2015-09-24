package org.developerworld.commons.compress;

import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;

/**
 * 压缩文件输出流创建器
 * @author Roy Huang
 * @version 20121109
 *
 */
public interface ArchiveOutputStreamBuilder {

	/**
	 * 创建输出流
	 * @param outputStream
	 * @return
	 * @throws ArchiveException
	 */
	ArchiveOutputStream buildArchiveOutputStream(
			OutputStream outputStream) throws ArchiveException;
}
