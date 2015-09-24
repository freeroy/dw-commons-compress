package org.developerworld.commons.compress.cpio;

import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.developerworld.commons.compress.ArchiveOutputStreamBuilder;

/**
 * cpio 压缩文件输出流创建器
 * @author Roy Huang
 * @version 20121108
 *
 */
public class CpioArchiveOutputStreamBuilder implements ArchiveOutputStreamBuilder {

	public ArchiveOutputStream buildArchiveOutputStream(
			OutputStream outputStream) throws ArchiveException {
		return new ArchiveStreamFactory().createArchiveOutputStream(
				ArchiveStreamFactory.CPIO, outputStream);
	}

}
