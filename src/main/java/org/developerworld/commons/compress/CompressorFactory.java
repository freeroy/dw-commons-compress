package org.developerworld.commons.compress;

import java.util.HashMap;
import java.util.Map;

import org.developerworld.commons.compress.ar.ArArchiveOutputStreamBuilder;
import org.developerworld.commons.compress.bzip2.Bzip2CompressorOutputStreamBuilder;
import org.developerworld.commons.compress.cpio.CpioArchiveOutputStreamBuilder;
import org.developerworld.commons.compress.dump.DumpArchiveOutputStreamBuilder;
import org.developerworld.commons.compress.gzip.GzipCompressorOutputStreamBuilder;
import org.developerworld.commons.compress.jar.JarArchiveOutputStreamBuilder;
import org.developerworld.commons.compress.pack200.Pack200CompressorOutputStreamBuilder;
import org.developerworld.commons.compress.tar.TarArchiveOutputStreamBuilder;
import org.developerworld.commons.compress.xz.XzCompressorOutputStreamBuilder;
import org.developerworld.commons.compress.zip.ZipArchiveOutputStreamBuilder;

/**
 * 压缩器工厂
 * 
 * @author Roy Huang
 * @version 20121109
 * 
 */
public class CompressorFactory {

	private static Map<SupportType, ArchiveOutputStreamBuilder> builders = new HashMap<SupportType, ArchiveOutputStreamBuilder>();

	static {
		builders.put(SupportType.AR, new ArArchiveOutputStreamBuilder());
		builders.put(SupportType.CPIO, new CpioArchiveOutputStreamBuilder());
		builders.put(SupportType.DUMP, new DumpArchiveOutputStreamBuilder());
		builders.put(SupportType.JAR, new JarArchiveOutputStreamBuilder());
		builders.put(SupportType.TAR, new TarArchiveOutputStreamBuilder());
		builders.put(SupportType.ZIP, new ZipArchiveOutputStreamBuilder());
	}

	/**
	 * 创建压缩器
	 * 
	 * @param type
	 * @return
	 */
	public static Compressor buildCompressor(SupportType type) {
		// 特殊类型特殊构造
		if (type.equals(SupportType.TAR_GZIP))
			return new MultipleCompressor(new TarArchiveOutputStreamBuilder(),
					new GzipCompressorOutputStreamBuilder());
		else if (type.equals(SupportType.TAR_BZIP2))
			return new MultipleCompressor(new TarArchiveOutputStreamBuilder(),
					new Bzip2CompressorOutputStreamBuilder());
		else if (type.equals(SupportType.TAR_XZ))
			return new MultipleCompressor(new TarArchiveOutputStreamBuilder(),
					new XzCompressorOutputStreamBuilder());
		else if (type.equals(SupportType.JAR_PACK200))
			return new MultipleCompressor(new JarArchiveOutputStreamBuilder(),
					new Pack200CompressorOutputStreamBuilder());
		else if (!builders.containsKey(type))
			throw new IllegalArgumentException("unsupport this type:" + type);
		return new SimpleCompressor(builders.get(type));
	}

}
