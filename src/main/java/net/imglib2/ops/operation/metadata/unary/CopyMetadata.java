/*
 * #%L
 * ImageJ2 software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2009 - 2023 ImageJ2 developers.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package net.imglib2.ops.operation.metadata.unary;

import net.imagej.ImgPlusMetadata;
import net.imglib2.ops.operation.UnaryOperation;

/**
 * @author Christian Dietz (University of Konstanz)
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public class CopyMetadata implements UnaryOperation< ImgPlusMetadata, ImgPlusMetadata >
{

	private UnaryOperation< ImgPlusMetadata, ImgPlusMetadata >[] ops;

	public CopyMetadata( UnaryOperation< ImgPlusMetadata, ImgPlusMetadata >... ops )
	{
		this.ops = ops;
	}

	@SuppressWarnings( "unchecked" )
	public CopyMetadata()
	{
		ops = new UnaryOperation[ 4 ];
		ops[ 0 ] = new CopyCalibratedSpace< ImgPlusMetadata >();
		ops[ 1 ] = new CopyImageMetadata< ImgPlusMetadata >();
		ops[ 2 ] = new CopyNamed< ImgPlusMetadata >();
		ops[ 3 ] = new CopySourced< ImgPlusMetadata >();
	}

	@Override
	public ImgPlusMetadata compute( ImgPlusMetadata input, ImgPlusMetadata output )
	{
		for ( UnaryOperation< ImgPlusMetadata, ImgPlusMetadata > op : ops )
		{
			op.compute( input, output );
		}

		return output;
	}

	@Override
	public UnaryOperation< ImgPlusMetadata, ImgPlusMetadata > copy()
	{
		return new CopyMetadata();
	}

}
