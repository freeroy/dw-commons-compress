package org.developerworld.commons.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.io.IOUtils;

/**
 * 多文件压缩器
 * 
 * @author Roy Huang
 * @version 20121107
 * 
 */
public class SimpleCompressor implements Compressor {

	protected String outputFilePath;
	protected FileFilter fileFilter;

	protected List<String> filePaths = new ArrayList<String>();
	protected List<String> compressPaths = new ArrayList<String>();
	protected List<Boolean> includeRoots = new ArrayList<Boolean>();

	protected ArchiveOutputStreamBuilder archiveOutputStreamBuilder;

	public SimpleCompressor(ArchiveOutputStreamBuilder builder) {
		archiveOutputStreamBuilder = builder;
	}

	public void setFileFilter(FileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}

	public Compressor addFilePath(String filePath) {
		return addFilePath(filePath, "");
	}

	public Compressor addFilePath(String filePath, boolean includeRoot) {
		return addFilePath(filePath, "", includeRoot);
	}

	public Compressor addFilePath(String filePath, String compressPath) {
		return addFilePath(filePath, compressPath, false);
	}

	public Compressor addFilePath(String filePath, String compressPath,
			boolean includeRoot) {
		filePaths.add(filePath);
		compressPaths.add(compressPath);
		includeRoots.add(includeRoot);
		return this;
	}

	public void setOutputFilePath(String filePath) {
		this.outputFilePath = filePath;
	}

	public void compress() throws IOException, ArchiveException,
			CompressorException {
		if (outputFilePath == null || outputFilePath.trim().length() <= 0)
			throw new IOException("the outputFilePath can't be empty");
		// 创建输出文件流对象
		ArchiveOutputStream archiveOutputStream = null;
		try {
			// 创建压缩文件输出对象
			archiveOutputStream = archiveOutputStreamBuilder
					.buildArchiveOutputStream(new BufferedOutputStream(new FileOutputStream(
							outputFilePath)));
			int size = filePaths.size();
			for (int i = 0; i < size; i++) {
				// 获取文件路径以及压缩路径
				String filePath = filePaths.get(i);
				String compressPath = compressPaths.get(i);
				boolean includeRoot = includeRoots.get(i);
				// 创建文件对象
				File file = new File(filePath);
				// 若文件不存在，跳过
				if (!file.exists())
					continue;
				// 修正compressPath
				if (compressPath == null)
					compressPath = "";
				else {
					// 转换斜杠
					compressPath = compressPath.replace("\\", "/");
					while (compressPath.startsWith("/"))
						compressPath = compressPath.substring(1);
					if (compressPath.length() > 0
							&& !compressPath.endsWith("/"))
						compressPath += "/";
				}
				// 压缩不包含根目录
				if (file.isDirectory() && !includeRoot) {
					File[] _files = null;
					if (fileFilter != null)
						_files = file.listFiles(fileFilter);
					else
						_files = file.listFiles();
					for (File _file : _files)
						iteratorFiles(archiveOutputStream, _file, compressPath);
				} else
					iteratorFiles(archiveOutputStream, file, compressPath);
			}
		} finally {
			if (archiveOutputStream != null) {
				archiveOutputStream.finish();
				archiveOutputStream.close();
			}
		}
	}

	/**
	 * 迭代处理文件
	 * 
	 * @param archiveOutputStream
	 * @param file
	 * @param compressPath
	 * @throws IOException
	 */
	private void iteratorFiles(ArchiveOutputStream archiveOutputStream,
			File file, String compressPath) throws IOException {
		// 添加到压缩文件
		compressToCompressedFile(archiveOutputStream, file, compressPath);
		// 若为目录，遍历目录内文件执行压缩
		if (file.isDirectory()) {
			File[] _files = null;
			if (fileFilter != null)
				_files = file.listFiles(fileFilter);
			else
				_files = file.listFiles();
			for (File _file : _files) {
				String _compressPath = compressPath + file.getName() + "/";
				iteratorFiles(archiveOutputStream, _file, _compressPath);
			}
		}
	}

	/**
	 * 执行单文件压缩
	 * 
	 * @param archiveOutputStream
	 * @param file
	 * @param compressPath
	 * @throws IOException
	 */
	private void compressToCompressedFile(
			ArchiveOutputStream archiveOutputStream, File file,
			String compressPath) throws IOException {
		// 写入压缩文件输出流
		// 创建压缩文件实体
		String entryName = compressPath + file.getName();
		ArchiveEntry entry = archiveOutputStream.createArchiveEntry(file,
				entryName);
		archiveOutputStream.putArchiveEntry(entry);
		try {
			// 当不为文件夹，需要进行流复制
			if (!file.isDirectory()) {
				// 创建文件输入流
				InputStream inputStream = null;
				try {
					inputStream = new BufferedInputStream(new FileInputStream(file));
					// 进行流内容复制
					IOUtils.copy(inputStream, archiveOutputStream);
				} finally {
					// 关闭输入流
					if (inputStream != null)
						inputStream.close();
				}
			}
		} finally {
			// 关闭实体写入流
			archiveOutputStream.closeArchiveEntry();
		}
	}

}
