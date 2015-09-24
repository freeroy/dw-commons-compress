package org.developerworld.commons.compress;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

/**
 * 抽象压缩的tar打包
 * 
 * @author Roy Huang
 * @version 20121112
 * 
 */
public class MultipleCompressor extends SimpleCompressor {

	private CompressorOutputStreamBuilder compressorOutputStreamBuilder;

	public MultipleCompressor(
			ArchiveOutputStreamBuilder archiveOutputStreamBuilder,
			CompressorOutputStreamBuilder compressorOutputStreamBuilder) {
		super(archiveOutputStreamBuilder);
		this.compressorOutputStreamBuilder = compressorOutputStreamBuilder;
	}

	/**
	 * 重写压缩方法
	 * 
	 * @throws ArchiveException
	 * @throws IOException
	 * @throws CompressorException
	 */
	@Override
	public void compress() throws IOException, ArchiveException,
			CompressorException {
		// 执行父类的打包方法
		super.compress();
		// 获取已经打包的文件对象
		File outputFile = new File(outputFilePath);
		// 用于记录临时的字节数据
		ByteArrayOutputStream outputStream = null;
		CompressorOutputStream compressorOutputStream = null;
		InputStream is = null;
		try {
			// 创建输入流
			is = new FileInputStream(outputFile);
			// 创建输出流
			outputStream = new ByteArrayOutputStream();
			compressorOutputStream = compressorOutputStreamBuilder
					.buildCompressorOutputStream(new BufferedOutputStream(
							new BufferedOutputStream(outputStream)));
			IOUtils.copy(is, compressorOutputStream);
		} finally {
			try {
				if (is != null)
					is.close();
			} finally {
				try {
					if (compressorOutputStream != null)
						compressorOutputStream.close();
				} finally {
					if (outputStream != null)
						outputStream.close();
				}
			}
		}
		// 把字节数组重新写进原来的文件
		FileUtils.writeByteArrayToFile(outputFile, outputStream.toByteArray());
	}
}
