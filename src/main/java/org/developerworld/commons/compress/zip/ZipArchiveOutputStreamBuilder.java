package org.developerworld.commons.compress.zip;

import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.developerworld.commons.compress.ArchiveOutputStreamBuilder;

/**
 * zip压缩文件输出流创建器
 * 
 * @author Roy Huang
 * @version 20121107
 * 
 */
public class ZipArchiveOutputStreamBuilder implements ArchiveOutputStreamBuilder{

	public ArchiveOutputStream buildArchiveOutputStream(
			OutputStream outputStream) throws ArchiveException {
		return new ArchiveStreamFactory().createArchiveOutputStream(
				ArchiveStreamFactory.ZIP, outputStream);
	}

}
