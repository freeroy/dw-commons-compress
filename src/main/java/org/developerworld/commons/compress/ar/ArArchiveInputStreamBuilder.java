package org.developerworld.commons.compress.ar;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.developerworld.commons.compress.ArchiveInputStreamBuilder;

/**
 * ar 压缩输入流创建器
 * @author Roy Huang
 * @version 20121109
 *
 */
public class ArArchiveInputStreamBuilder implements ArchiveInputStreamBuilder{

	public ArchiveInputStream buildArchiveInputStream(InputStream inputStream)
			throws FileNotFoundException, ArchiveException {
		return new ArchiveStreamFactory().createArchiveInputStream(
				ArchiveStreamFactory.AR, inputStream);
	}

}
