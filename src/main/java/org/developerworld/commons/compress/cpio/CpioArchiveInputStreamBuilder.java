package org.developerworld.commons.compress.cpio;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.developerworld.commons.compress.ArchiveInputStreamBuilder;

/**
 * cpio 解压文件输入流创建器
 * 
 * @author Roy Huang
 * @version 20121108
 * 
 */
public class CpioArchiveInputStreamBuilder implements ArchiveInputStreamBuilder {

	public ArchiveInputStream buildArchiveInputStream(InputStream inputStream)
			throws FileNotFoundException, ArchiveException {
		return new ArchiveStreamFactory().createArchiveInputStream(
				ArchiveStreamFactory.CPIO, inputStream);
	}

}
