package org.developerworld.commons.compress.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.developerworld.commons.compress.Compressor;
import org.developerworld.commons.compress.CompressorFactory;
import org.developerworld.commons.compress.SupportType;

/**
 * 压缩工具类
 * 
 * @author Roy Huang
 * @version 20121109
 * 
 */
public class CompressUtils {

	/**
	 * 压缩zip文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToZip(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToZip(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩zip文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToZip(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.ZIP);
	}

	/**
	 * 压缩ar文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToAr(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToAr(srcFile.getPath(), srcFile.getPath());
	}

	/**
	 * 压缩ar文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToAr(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.AR);
	}

	/**
	 * 压缩cpio文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToCpio(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToCpio(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩cpio文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToCpio(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.CPIO);
	}

	/**
	 * 压缩dump文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToDump(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToDump(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩dump文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToDump(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.DUMP);
	}

	/**
	 * 压缩jar文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToJar(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToJar(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩jar文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToJar(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.JAR);
	}

	/**
	 * 压缩jar_pack200文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToJarPack200(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToJarPack200(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩jar_pack200文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToJarPack200(String srcFilePath,
			String destFilePath) throws IOException, ArchiveException,
			CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.JAR_PACK200);
	}

	/**
	 * 压缩Tar文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToTar(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToTar(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩Tar文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToTar(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.TAR);
	}

	/**
	 * 压缩tar_gzip文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToTarGzip(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToTarGzip(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩tar_gzip文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToTarGzip(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.TAR_GZIP);
	}

	/**
	 * 压缩tar_bzip2文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToTarBzip2(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToTarBzip2(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩tar_bzip2文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToTarBzip2(String srcFilePath,
			String destFilePath) throws IOException, ArchiveException,
			CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.TAR_BZIP2);
	}

	/**
	 * 压缩tar_xz文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToTarXz(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		compressToTarXz(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 压缩tar_xz文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void compressToTarXz(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		compressToArchive(srcFilePath, destFilePath, SupportType.TAR_XZ);
	}

	/**
	 * 执行压缩
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @param supportType
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	private static void compressToArchive(String srcFilePath,
			String destFilePath, SupportType supportType) throws IOException,
			ArchiveException, CompressorException {
		Compressor compressor = CompressorFactory.buildCompressor(supportType);
		compressor.addFilePath(srcFilePath);
		compressor.setOutputFilePath(destFilePath);
		compressor.compress();
	}

	/**
	 * 压缩bzip2文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void compressToBzip2(String srcFilePath, String destFilePath)
			throws CompressorException, IOException {
		compressToBzip2(new File(srcFilePath), new File(destFilePath));
	}

	/**
	 * 压缩bzip2文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void compressToBzip2(File srcFile, File destFile)
			throws CompressorException, IOException {
		compressToArchive2(srcFile, destFile, CompressorStreamFactory.BZIP2);
	}

	/**
	 * 压缩GZIP文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void compressToGzip(String srcFilePath, String destFilePath)
			throws CompressorException, IOException {
		compressToGzip(new File(srcFilePath), new File(destFilePath));
	}

	/**
	 * 压缩GZIP文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void compressToGzip(File srcFile, File destFile)
			throws CompressorException, IOException {
		compressToArchive2(srcFile, destFile, CompressorStreamFactory.GZIP);
	}

	/**
	 * 压缩PACK200文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void compressToPack200(String srcFilePath, String destFilePath)
			throws CompressorException, IOException {
		compressToPack200(new File(srcFilePath), new File(destFilePath));
	}

	/**
	 * 压缩PACK200文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void compressToPack200(File srcFile, File destFile)
			throws CompressorException, IOException {
		compressToArchive2(srcFile, destFile, CompressorStreamFactory.PACK200);
	}

	/**
	 * 压缩xz文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void compressToXz(String srcFilePath, String destFilePath)
			throws CompressorException, IOException {
		compressToXz(new File(srcFilePath), new File(destFilePath));
	}

	/**
	 * 压缩xz文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void compressToXz(File srcFile, File destFile)
			throws CompressorException, IOException {
		compressToArchive2(srcFile, destFile, CompressorStreamFactory.XZ);
	}

	/**
	 * 执行文件压缩
	 * 
	 * @param srcFile
	 * @param destFile
	 * @param type
	 * @throws CompressorException
	 * @throws IOException
	 */
	private static void compressToArchive2(File srcFile, File destFile,
			String type) throws CompressorException, IOException {
		if (!srcFile.isFile() && !destFile.isFile())
			throw new IllegalArgumentException(
					"srcFile and destFile must is file type!");
		CompressorOutputStream compressorOutputStream = null;
		InputStream is = null;
		try {
			is = new BufferedInputStream(new FileInputStream(srcFile));
			compressorOutputStream = new CompressorStreamFactory()
					.createCompressorOutputStream(type,
							new BufferedOutputStream(new FileOutputStream(
									destFile)));
			IOUtils.copy(is, compressorOutputStream);
		} finally {
			try {
				if (is != null)
					is.close();
			} finally {
				if (compressorOutputStream != null)
					compressorOutputStream.close();
			}
		}
	}
}
