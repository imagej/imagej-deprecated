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

import net.imglib2.ops.operation.complex.binary.ComplexAdd;
import net.imglib2.ops.operation.complex.binary.ComplexMultiply;
import net.imglib2.ops.operation.complex.binary.ComplexPower;
import net.imglib2.type.numeric.ComplexType;
import net.imglib2.type.numeric.complex.ComplexDoubleType;

//Handbook of Mathematics and Computational Science, Harris & Stocker, Springer, 2006

/**
 * Sets an output complex number to the inverse hyperbolic sine of an input
 * complex number.
 * 
 * @author Barry DeZonia
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public final class ComplexArcsinh<I extends ComplexType<I>, O extends ComplexType<O>>
	implements ComplexUnaryOperation<I,O>
{
	private final ComplexMultiply<I,I,ComplexDoubleType>
		mulFunc = new ComplexMultiply<I,I,ComplexDoubleType>();
	private final ComplexAdd<ComplexDoubleType,ComplexDoubleType,ComplexDoubleType>
		addFunc1 = new ComplexAdd<ComplexDoubleType, ComplexDoubleType, ComplexDoubleType>();
	private final ComplexPower<ComplexDoubleType,ComplexDoubleType,ComplexDoubleType>
		powFunc = new ComplexPower<ComplexDoubleType, ComplexDoubleType, ComplexDoubleType>();
	private final ComplexAdd<I,ComplexDoubleType,ComplexDoubleType>
		addFunc2 = new ComplexAdd<I, ComplexDoubleType, ComplexDoubleType>();
	private final ComplexLog<ComplexDoubleType,O>
		logFunc = new ComplexLog<ComplexDoubleType,O>();

	private static final ComplexDoubleType ONE = new ComplexDoubleType(1,0);
	private static final ComplexDoubleType ONE_HALF = new ComplexDoubleType(0.5,0);

	private final ComplexDoubleType zSquared = new ComplexDoubleType();
	private final ComplexDoubleType miniSum = new ComplexDoubleType();
	private final ComplexDoubleType root = new ComplexDoubleType();
	private final ComplexDoubleType sum = new ComplexDoubleType();

	@Override
	public O compute(I z, O output) {
		mulFunc.compute(z, z, zSquared);
		addFunc1.compute(zSquared, ONE, miniSum);
		powFunc.compute(miniSum, ONE_HALF, root);
		addFunc2.compute(z, root, sum);
		logFunc.compute(sum, output);
		return output;
	}

	@Override
	public ComplexArcsinh<I,O> copy() {
		return new ComplexArcsinh<I,O>();
	}

}
