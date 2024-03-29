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

package net.imglib2.ops.operation.real.binary;

import net.imglib2.type.numeric.RealType;

// NB - this method required by IJ2 for IJ1 compatibility

/**
 * A real operation that sets the real component of an output. The output value
 * is determined based upon input 2. If input 2 is zero then output is assigned
 * from input 1. Otherwise output is assigned from input 2.
 * 
 * @author Barry DeZonia
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public final class RealCopyZeroTransparent<
		I1 extends RealType<I1>,
		I2 extends RealType<I2>,
		O extends RealType<O>>
	implements RealBinaryOperation<I1, I2, O>
{
	@Override
	public O compute(I1 x1, I2 x2, O output) {
		if (x2.getRealDouble() == 0)
			output.setReal(x1.getRealDouble());
		else
			output.setReal(x2.getRealDouble());
		return output;
	}

	@Override
	public RealCopyZeroTransparent<I1,I2,O> copy() {
		return new RealCopyZeroTransparent<I1,I2,O>();
	}
}
