package org.developerworld.commons.compress.gzip;

import java.io.OutputStream;

import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.developerworld.commons.compress.CompressorOutputStreamBuilder;

public class GzipCompressorOutputStreamBuilder implements
		CompressorOutputStreamBuilder {

	public CompressorOutputStream buildCompressorOutputStream(
			OutputStream outputStream) throws CompressorException {
		return new CompressorStreamFactory().createCompressorOutputStream(
				CompressorStreamFactory.GZIP, outputStream);
	}

}
