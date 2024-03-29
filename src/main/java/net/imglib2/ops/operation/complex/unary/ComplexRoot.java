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

package net.imglib2.ops.operation.complex.unary;

import net.imglib2.ops.util.ComplexHelper;
import net.imglib2.type.numeric.ComplexType;

//Handbook of Mathematics and Computational Science, Harris & Stocker, Springer, 2006

/**
 * Sets an output complex number to the nth root of an input complex
 * number. N is specified in the constructor.
 * 
 * @author Barry DeZonia
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public final class ComplexRoot<I extends ComplexType<I>, O extends ComplexType<O>>
	implements ComplexUnaryOperation<I,O>
{
	private final int power;

	public ComplexRoot(int power) {
		if (power < 1)
			throw new IllegalArgumentException(
					"ComplexRoot requires a positive number of roots");
		this.power = power;
	}

	@Override
	public O compute(I z, O output) {
		double modulus = ComplexHelper.getModulus(z);
		double argument = ComplexHelper.getArgument(z);
		double princpArg = ComplexHelper.getPrincipleArgument(argument);
		double r = Math.pow(modulus, 1.0 / power);
		double theta = princpArg / power;
		ComplexHelper.setPolar(output, r, theta);
		return output;
	}

	@Override
	public ComplexRoot<I,O> copy() {
		return new ComplexRoot<I,O>(power);
	}
}
