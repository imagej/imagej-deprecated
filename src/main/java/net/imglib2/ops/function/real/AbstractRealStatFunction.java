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
import net.imglib2.ops.pointset.PointSet;
import net.imglib2.type.numeric.RealType;

/**
 * Abstract base class used by statistical function classes
 * 
 * @author Barry DeZonia
 * @param <T>
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public abstract class AbstractRealStatFunction<T extends RealType<T>> implements
	Function<PointSet, T>
{
	// -- instance variables --
	
	protected final Function<long[], T> otherFunc;
	private StatCalculator<T> calculator;
	
	// -- constructor --
	
	public AbstractRealStatFunction(Function<long[],T> otherFunc) {
		this.otherFunc = otherFunc;
		this.calculator = null;
	}
	
	// -- public api --

	@Override
	public void compute(PointSet input, T output) {
		if (calculator == null) {
			calculator = new StatCalculator<T>(otherFunc, input);
		}
		else calculator.reset(otherFunc, input);
		double value = value(calculator);
		output.setReal(value);
	}

	@Override
	public T createOutput() {
		return otherFunc.createOutput();
	}

	// -- protected api --

	abstract protected double value(StatCalculator<T> calc);

}
