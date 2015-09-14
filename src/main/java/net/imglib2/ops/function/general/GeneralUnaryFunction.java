/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2015 Board of Regents of the University of
 * Wisconsin-Madison, University of Konstanz and Brian Northan.
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

package net.imglib2.ops.function.general;

import net.imglib2.ops.function.Function;
import net.imglib2.ops.operation.UnaryOperation;
import net.imglib2.type.numeric.ComplexType;

/**
 * Mutates the output of another {@link Function} into an output type.
 * The mutation is done using a {@link UnaryOperation}.
 *  
 * @author Barry DeZonia
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public class GeneralUnaryFunction<INPUT, C extends ComplexType<C>,
		OUTPUT extends ComplexType<OUTPUT>>
	implements Function<INPUT, OUTPUT>
{
	// -- instance variables --
	
	private final Function<INPUT, C> f1;
	private final C temp;
	private final UnaryOperation<C, OUTPUT> operation;
	private final OUTPUT type;

	// -- constructor --
	
	public GeneralUnaryFunction(Function<INPUT, C> f1,
			UnaryOperation<C, OUTPUT> operation, OUTPUT type) {
		this.type = type;
		this.f1 = f1;
		this.temp = f1.createOutput();
		this.operation = operation;
	}

	// -- Function methods --
	
	@Override
	public void compute(INPUT input, OUTPUT output) {
		f1.compute(input, temp);
		operation.compute(temp, output);
	}

	@Override
	public GeneralUnaryFunction<INPUT, C, OUTPUT> copy()
	{
		return new GeneralUnaryFunction<INPUT, C, OUTPUT>(
			f1.copy(), operation.copy(), type.createVariable());
	}

	@Override
	public OUTPUT createOutput() {
		return type.createVariable();
	}
}
