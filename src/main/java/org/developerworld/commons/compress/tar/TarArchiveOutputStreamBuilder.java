package org.developerworld.commons.compress.tar;

import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.developerworld.commons.compress.ArchiveOutputStreamBuilder;

/**
 * TAR压缩文件输出流创建器
 * 
 * @author Roy Huang
 * @version 20121107
 * 
 */
public class TarArchiveOutputStreamBuilder implements
		ArchiveOutputStreamBuilder {

	public ArchiveOutputStream buildArchiveOutputStream(
			OutputStream outputStream) throws ArchiveException {
		return new ArchiveStreamFactory().createArchiveOutputStream(
				ArchiveStreamFactory.TAR, outputStream);
	}

}
