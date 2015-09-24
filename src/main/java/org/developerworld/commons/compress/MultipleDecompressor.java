package org.developerworld.commons.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 * gzip解压缩的tar打包
 * 
 * @author Roy Huang
 * @version 20121113
 * 
 */
public class MultipleDecompressor extends SimpleDecompressor {

	private CompressorInputStreamBuilder compressorInputStreamBuilder;

	public MultipleDecompressor(
			ArchiveInputStreamBuilder archiveInputStreamBuilder,
			CompressorInputStreamBuilder compressorInputStreamBuilder) {
		super(archiveInputStreamBuilder);
		this.compressorInputStreamBuilder = compressorInputStreamBuilder;
	}

	/**
	 * 重构原来的解压方法，先执行gzip解压，再执行tar解包
	 * 
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	@Override
	public void decompress() throws IOException, ArchiveException,
			CompressorException {
		if (outputFilePath == null || outputFilePath.trim().length() <= 0)
			throw new IOException("the outputFilePath can't be empty");
		// 遍历所有需要压缩的文件
		int size = decompressFilePaths.size();
		for (int i = 0; i < size; i++) {
			String decompressFilePath = decompressFilePaths.get(i);
			String relativePath = relativePaths.get(i);
			boolean includeRoot = includeRoots.get(i);
			File archiveFile = new File(decompressFilePath);
			// 文件不存在，跳过
			if (!archiveFile.exists())
				continue;
			// 实际输出路径
			String outputFilePath = this.outputFilePath;
			if (!outputFilePath.endsWith(File.separator))
				outputFilePath += File.separator;
			while (relativePath.startsWith(File.separator))
				relativePath = relativePath.substring(1);
			while (relativePath.endsWith("/"))
				relativePath = relativePath.substring(0,
						relativePath.length() - 1);
			outputFilePath += relativePath;
			// 检查是否需要包含一个压缩文件的文件夹
			if (includeRoot) {
				String directoryName = FilenameUtils.getBaseName(archiveFile
						.getName());
				outputFilePath += File.separator + directoryName;
			}
			// 获取tar数据流
			byte[] tarDatas = getDecompressDatas(archiveFile);
			// 执行文件解压
			ArchiveInputStream archiveInputStream = null;
			try {
				// 创建压缩文件输入流
				archiveInputStream = archiveInputStreamBuilder
						.buildArchiveInputStream(new BufferedInputStream(new ByteArrayInputStream(
								tarDatas)));
				ArchiveEntry entry = null;
				// 遍历压缩文件中实体对象
				while ((entry = archiveInputStream.getNextEntry()) != null) {
					// 若文件为目录
					if (entry.isDirectory()) {
						// 创建目录
						File directory = new File(outputFilePath,
								entry.getName());
						directory.mkdirs();
					} else {
						File file = new File(outputFilePath, entry.getName());
						// 创建所在目录文件夹
						if (!file.getParentFile().exists())
							file.getParentFile().mkdirs();
						// 文件，复制流
						OutputStream os = null;
						try {
							os = new BufferedOutputStream(new FileOutputStream(file));
							IOUtils.copy(archiveInputStream, os);
						} finally {
							if (os != null)
								os.close();
						}
					}
				}
			} finally {
				if (archiveInputStream != null)
					archiveInputStream.close();
			}
		}
	}

	/**
	 * 获取去除gzip后的tar字节流
	 * 
	 * @param archiveFile
	 * @return
	 * @throws CompressorException
	 * @throws IOException
	 */
	private byte[] getDecompressDatas(File archiveFile)
			throws CompressorException, IOException {
		// 创建临时byte对象
		ByteArrayOutputStream byteArrayOutputStream = null;
		CompressorInputStream compressorInputStream = null;
		try {
			compressorInputStream = compressorInputStreamBuilder
					.buildCompressorInputStream(new BufferedInputStream(new FileInputStream(archiveFile)));
			byteArrayOutputStream = new ByteArrayOutputStream();
			IOUtils.copy(compressorInputStream, byteArrayOutputStream);
		} finally {
			try {
				if (byteArrayOutputStream != null)
					byteArrayOutputStream.close();
			} finally {
				if (compressorInputStream != null)
					compressorInputStream.close();
			}
		}
		return byteArrayOutputStream.toByteArray();
	}

}
