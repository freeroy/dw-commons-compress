package org.developerworld.commons.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/**
 * 多文件解压缩器
 * 
 * @author Roy Huang
 * @version 20121107
 * 
 */
public class SimpleDecompressor implements Decompressor {

	protected String outputFilePath;
	protected List<String> decompressFilePaths = new ArrayList<String>();
	protected List<String> relativePaths = new ArrayList<String>();
	protected List<Boolean> includeRoots = new ArrayList<Boolean>();
	protected ArchiveInputStreamBuilder archiveInputStreamBuilder;

	public SimpleDecompressor(ArchiveInputStreamBuilder builder) {
		archiveInputStreamBuilder = builder;
	}

	public SimpleDecompressor addFilePath(String decompressFilePath) {
		return addFilePath(decompressFilePath, "");
	}

	public SimpleDecompressor addFilePath(String decompressFilePath,
			boolean includeRoot) {
		return addFilePath(decompressFilePath, "", includeRoot);
	}

	public SimpleDecompressor addFilePath(String decompressFilePath,
			String outputRelativePath) {
		return addFilePath(decompressFilePath, outputRelativePath, false);
	}

	public SimpleDecompressor addFilePath(String decompressFilePath,
			String outputRelativePath, boolean includeRoot) {
		decompressFilePaths.add(decompressFilePath);
		relativePaths.add(outputRelativePath);
		includeRoots.add(includeRoot);
		return this;
	}

	public void setOutputFilePath(String filePath) {
		this.outputFilePath = filePath;
	}

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
			// 执行文件解压
			ArchiveInputStream archiveInputStream = null;
			try {
				// 创建压缩文件输入流
				archiveInputStream = archiveInputStreamBuilder
						.buildArchiveInputStream(new BufferedInputStream(
								new FileInputStream(archiveFile)));
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
							os = new BufferedOutputStream(new FileOutputStream(
									file));
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
}
