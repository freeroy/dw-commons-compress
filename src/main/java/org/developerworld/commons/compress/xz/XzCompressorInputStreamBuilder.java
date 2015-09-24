package org.developerworld.commons.compress.xz;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.developerworld.commons.compress.CompressorInputStreamBuilder;

public class XzCompressorInputStreamBuilder implements
		CompressorInputStreamBuilder {

	public CompressorInputStream buildCompressorInputStream(
			InputStream inputStream) throws FileNotFoundException,
			CompressorException {
		return new CompressorStreamFactory().createCompressorInputStream(
				CompressorStreamFactory.XZ, inputStream);
	}

}
