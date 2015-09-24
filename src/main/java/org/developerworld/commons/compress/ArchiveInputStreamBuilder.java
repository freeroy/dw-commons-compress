package org.developerworld.commons.compress;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;

/**
 * 压缩文件输入流创建器
 * 
 * @author Roy Huang
 * @version 20121109
 * 
 */
public interface ArchiveInputStreamBuilder {

	/**
	 * 创建压缩文件输入流
	 * 
	 * @param inputStream
	 * @return
	 * @throws FileNotFoundException
	 * @throws ArchiveException
	 */
	ArchiveInputStream buildArchiveInputStream(InputStream inputStream)
			throws FileNotFoundException, ArchiveException;

}
