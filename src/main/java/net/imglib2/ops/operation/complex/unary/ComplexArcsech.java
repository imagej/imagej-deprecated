/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2009 - 2016 Board of Regents of the University of
 * Wisconsin-Madison, Broad Institute of MIT and Harvard, Max Planck Institute
 * of Molecular Cell Biology and Genetics, and University of Konstanz.
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

package net.imglib2.ops.operation.complex.unary;

import net.imglib2.type.numeric.ComplexType;
import net.imglib2.type.numeric.complex.ComplexDoubleType;

// Formula taken from MATLAB documentation

/**
 * Sets an output complex number to the inverse hyperbolic secant of an
 * input complex number.
 * 
 * @author Barry DeZonia
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public final class ComplexArcsech<I extends ComplexType<I>, O extends ComplexType<O>>
	implements ComplexUnaryOperation<I,O>
{
	private final ComplexReciprocal<I,ComplexDoubleType>
		recipFunc = new ComplexReciprocal<I,ComplexDoubleType>();
	private final ComplexArccosh<ComplexDoubleType,O>
		arccoshFunc = new ComplexArccosh<ComplexDoubleType,O>();

	private final ComplexDoubleType recipZ = new ComplexDoubleType();

	@Override
	public O compute(I z, O output) {
		recipFunc.compute(z, recipZ);
		arccoshFunc.compute(recipZ, output);
		return output;
	}

	@Override
	public ComplexArcsech<I,O> copy() {
		return new ComplexArcsech<I,O>();
	}

}
