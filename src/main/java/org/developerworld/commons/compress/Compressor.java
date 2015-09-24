package org.developerworld.commons.compress;

import java.io.FileFilter;
import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;

/**
 * 压缩器
 * 
 * @author Roy Huang
 * @version 20121107
 * 
 */
public interface Compressor {

	/**
	 * 添加压缩路径
	 * 
	 * @param filePath
	 * @return
	 */
	Compressor addFilePath(String filePath);

	/**
	 * 添加压缩路径
	 * 
	 * @param filePath
	 * @param includeRoot
	 * @return
	 */
	Compressor addFilePath(String filePath, boolean includeRoot);

	/**
	 * 添加压缩路径
	 * 
	 * @param filePath
	 * @param compressPath
	 * @return
	 */
	Compressor addFilePath(String filePath, String compressPath);

	/**
	 * 添加压缩路径
	 * 
	 * @param filePath
	 * @param compressPath
	 * @param includeRoot
	 * @return
	 */
	Compressor addFilePath(String filePath, String compressPath,
			boolean includeRoot);

	/**
	 * 设置文件过滤器
	 * 
	 * @param fileFilter
	 */
	void setFileFilter(FileFilter fileFilter);

	/**
	 * 设置压缩文件输出位置
	 * 
	 * @param filePath
	 */
	void setOutputFilePath(String filePath);

	/**
	 * 执行文件压缩
	 * 
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	void compress() throws IOException, ArchiveException, CompressorException;

}
