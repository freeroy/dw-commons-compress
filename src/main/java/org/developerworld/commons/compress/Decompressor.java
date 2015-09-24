package org.developerworld.commons.compress;

import java.io.IOException;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;

/**
 * 解压缩器
 * 
 * @author Roy Huang
 * @version 20121107
 * 
 */
public interface Decompressor {

	/**
	 * 添加压缩文件
	 * 
	 * @param decompressFilePath
	 * @return
	 */
	Decompressor addFilePath(String decompressFilePath);

	/**
	 * 添加压缩文件
	 * 
	 * @param decompressFilePath
	 * @param outputRelativePath
	 * @param includeRoot
	 * @return
	 */
	Decompressor addFilePath(String decompressFilePath,
			String outputRelativePath, boolean includeRoot);

	/**
	 * 添加压缩文件
	 * 
	 * @param decompressFilePath
	 * @param outputRelativePath
	 * @return
	 */
	Decompressor addFilePath(String decompressFilePath,
			String outputRelativePath);

	/**
	 * 添加压缩文件
	 * 
	 * @param decompressFilePath
	 * @param includeRoot
	 * @return
	 */
	Decompressor addFilePath(String decompressFilePath,
			boolean includeRoot);

	/**
	 * 设置解压缩位置
	 * 
	 * @param filePath
	 */
	void setOutputFilePath(String filePath);

	/**
	 * 解压缩
	 * 
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	void decompress() throws IOException, ArchiveException, CompressorException;

}
