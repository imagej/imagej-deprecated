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

package net.imglib2.ops.function.real;

import net.imglib2.ops.function.Function;
import net.imglib2.type.numeric.RealType;

/**
 * Computes the sum of squared deviations from the mean the values of another
 * function take over a region.
 * 
 * @author Barry DeZonia
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public class RealSumOfSquaredDeviationsFunction<T extends RealType<T>>
 extends
	AbstractRealStatFunction<T>
{
	// -- constructor --
	
	public RealSumOfSquaredDeviationsFunction(Function<long[],T> otherFunc)
	{
		super(otherFunc);
	}

	// -- abstract method overrides --

	@Override
	protected double value(StatCalculator<T> calc) {
		return calc.sumOfSquaredDeviations();
	}
	
	// -- Function methods --
	
	@Override
	public RealSumOfSquaredDeviationsFunction<T> copy() {
		return new RealSumOfSquaredDeviationsFunction<T>(otherFunc.copy());
	}

}
