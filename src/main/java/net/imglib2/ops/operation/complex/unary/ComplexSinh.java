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

import net.imglib2.ops.operation.complex.binary.ComplexDivide;
import net.imglib2.ops.operation.complex.binary.ComplexMultiply;
import net.imglib2.ops.operation.complex.binary.ComplexSubtract;
import net.imglib2.type.numeric.ComplexType;
import net.imglib2.type.numeric.complex.ComplexDoubleType;

//Handbook of Mathematics and Computational Science, Harris & Stocker, Springer, 2006

/**
 * Sets an output complex number to the hyperbolic sine of an input complex
 * number.
 * 
 * @author Barry DeZonia
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public final class ComplexSinh<I extends ComplexType<I>, O extends ComplexType<O>>
	implements ComplexUnaryOperation<I,O>
{
	private final ComplexExp<I,ComplexDoubleType>
		expFunc1 = new ComplexExp<I,ComplexDoubleType>();
	private final ComplexMultiply<I,ComplexDoubleType,ComplexDoubleType>
		mulFunc = new ComplexMultiply<I,ComplexDoubleType,ComplexDoubleType>();
	private final ComplexExp<ComplexDoubleType,ComplexDoubleType>
		expFunc2 = new ComplexExp<ComplexDoubleType,ComplexDoubleType>();
	private final ComplexSubtract<ComplexDoubleType,ComplexDoubleType, ComplexDoubleType>
		diffFunc = new ComplexSubtract<ComplexDoubleType,ComplexDoubleType,ComplexDoubleType>();
	private final ComplexDivide<ComplexDoubleType,ComplexDoubleType,O>
		divFunc = new ComplexDivide<ComplexDoubleType,ComplexDoubleType,O>();

	private static final ComplexDoubleType TWO = new ComplexDoubleType(2,0);
	private static final ComplexDoubleType MINUS_ONE = new ComplexDoubleType(-1,0);

	private final ComplexDoubleType minusZ = new ComplexDoubleType();
	private final ComplexDoubleType expZ = new ComplexDoubleType();
	private final ComplexDoubleType expMinusZ = new ComplexDoubleType();
	private final ComplexDoubleType diff = new ComplexDoubleType();

	@Override
	public O compute(I z, O output) {
		expFunc1.compute(z, expZ);
		mulFunc.compute(z, MINUS_ONE, minusZ);
		expFunc2.compute(minusZ, expMinusZ);
		diffFunc.compute(expZ, expMinusZ, diff);
		divFunc.compute(diff, TWO, output);
		return output;
	}

	@Override
	public ComplexSinh<I,O> copy() {
		return new ComplexSinh<I,O>();
	}

}
