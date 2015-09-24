package org.developerworld.commons.compress.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.developerworld.commons.compress.Decompressor;
import org.developerworld.commons.compress.DecompressorFactory;
import org.developerworld.commons.compress.SupportType;

/**
 * 解压缩工具类
 * 
 * @author Roy Huang
 * @version 20121112
 * 
 */
public class DecompressUtils {

	/**
	 * 解压缩zip文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressZip(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressZip(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩zip文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressZip(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.ZIP);
	}

	/**
	 * 解压缩ar文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressAr(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressAr(srcFile.getPath(), srcFile.getPath());
	}

	/**
	 * 解压缩ar文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressAr(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.AR);
	}

	/**
	 * 解压缩cpio文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressCpio(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressCpio(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩cpio文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressCpio(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.CPIO);
	}

	/**
	 * 解压缩dump文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressDump(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressDump(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩dump文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressDump(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.DUMP);
	}

	/**
	 * 解压缩jar文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressJar(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressJar(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩jar文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressJar(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.JAR);
	}

	/**
	 * 解压缩jar_pack200文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressJarPack200(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressJarPack200(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩jar_pack200文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressJarPack200(String srcFilePath,
			String destFilePath) throws IOException, ArchiveException,
			CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.JAR_PACK200);
	}

	/**
	 * 解压缩Tar文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressTar(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressTar(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩Tar文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressTar(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.TAR);
	}

	/**
	 * 解压缩Tar_gzip文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressTarGzip(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressTarGzip(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩Tar_gzip文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressTarGzip(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.TAR_GZIP);
	}

	/**
	 * 解压缩Tar_bzip2文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressTarBzip2(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressTarBzip2(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩Tar_bzip2文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressTarBzip2(String srcFilePath,
			String destFilePath) throws IOException, ArchiveException,
			CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.TAR_BZIP2);
	}

	/**
	 * 解压缩Tar_bzip2文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressTarXz(File srcFile, File destFile)
			throws IOException, ArchiveException, CompressorException {
		decompressTarXz(srcFile.getPath(), destFile.getPath());
	}

	/**
	 * 解压缩Tar_bzip2文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	public static void decompressTarXz(String srcFilePath, String destFilePath)
			throws IOException, ArchiveException, CompressorException {
		decompressToArchive(srcFilePath, destFilePath, SupportType.TAR_XZ);
	}

	/**
	 * 执行解压缩
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @param supportType
	 * @throws IOException
	 * @throws ArchiveException
	 * @throws CompressorException
	 */
	private static void decompressToArchive(String srcFilePath,
			String destFilePath, SupportType supportType) throws IOException,
			ArchiveException, CompressorException {
		Decompressor decompressor = DecompressorFactory
				.buildDecompressor(supportType);
		decompressor.addFilePath(srcFilePath);
		decompressor.setOutputFilePath(destFilePath);
		decompressor.decompress();
	}

	/**
	 * 解压缩bzip2文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void decompressBzip2(String srcFilePath, String destFilePath)
			throws CompressorException, IOException {
		decompressBzip2(new File(srcFilePath), new File(destFilePath));
	}

	/**
	 * 解压缩bzip2文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void decompressBzip2(File srcFile, File destFile)
			throws CompressorException, IOException {
		decompressToArchive2(srcFile, destFile, CompressorStreamFactory.BZIP2);
	}

	/**
	 * 解压缩GZIP文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void decompressGzip(String srcFilePath, String destFilePath)
			throws CompressorException, IOException {
		decompressGzip(new File(srcFilePath), new File(destFilePath));
	}

	/**
	 * 解压缩GZIP文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void decompressGzip(File srcFile, File destFile)
			throws CompressorException, IOException {
		decompressToArchive2(srcFile, destFile, CompressorStreamFactory.GZIP);
	}

	/**
	 * 解压缩PACK200文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void decompressPack200(String srcFilePath, String destFilePath)
			throws CompressorException, IOException {
		decompressPack200(new File(srcFilePath), new File(destFilePath));
	}

	/**
	 * 解压缩PACK200文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void decompressPack200(File srcFile, File destFile)
			throws CompressorException, IOException {
		decompressToArchive2(srcFile, destFile, CompressorStreamFactory.PACK200);
	}

	/**
	 * 解压缩xz文件
	 * 
	 * @param srcFilePath
	 * @param destFilePath
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void decompressXz(String srcFilePath, String destFilePath)
			throws CompressorException, IOException {
		decompressXz(new File(srcFilePath), new File(destFilePath));
	}

	/**
	 * 解压缩xz文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws CompressorException
	 * @throws IOException
	 */
	public static void decompressXz(File srcFile, File destFile)
			throws CompressorException, IOException {
		decompressToArchive2(srcFile, destFile, CompressorStreamFactory.XZ);
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
	private static void decompressToArchive2(File srcFile, File destFile,
			String type) throws CompressorException, IOException {
		if (!srcFile.isFile() && !destFile.isFile())
			throw new IllegalArgumentException(
					"srcFile and destFile must is file type!");
		CompressorInputStream compressorInputStream = null;
		OutputStream os = null;
		try {
			compressorInputStream = new CompressorStreamFactory()
					.createCompressorInputStream(type, new BufferedInputStream(
							new FileInputStream(srcFile)));
			os = new BufferedOutputStream(new FileOutputStream(destFile));
			IOUtils.copy(compressorInputStream, os);
		} finally {
			try {
				if (compressorInputStream != null)
					compressorInputStream.close();
			} finally {
				if (os != null)
					os.close();
			}
		}
	}
}
