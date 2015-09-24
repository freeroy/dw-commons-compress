package org.developerworld.commons.compress;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;

/**
 * 压缩文件输入流创建器
 * 
 * @author Roy Huang
 * @version 20121109
 * 
 */
public interface CompressorInputStreamBuilder {

	/**
	 * 创建压缩文件输入流
	 * 
	 * @param inputStream
	 * @return
	 * @throws CompressorException
	 * @throws FileNotFoundException
	 */
	CompressorInputStream buildCompressorInputStream(InputStream inputStream)
			throws FileNotFoundException, CompressorException;

}
