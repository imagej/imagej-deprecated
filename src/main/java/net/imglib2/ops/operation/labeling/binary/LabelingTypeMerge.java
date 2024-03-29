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

package net.imglib2.ops.operation.labeling.binary;

import java.util.ArrayList;
import java.util.List;

import net.imglib2.labeling.LabelingType;
import net.imglib2.ops.operation.BinaryOperation;

/**
 * LabelingTypes are merged.
 * 
 * @author Christian Dietz (University of Konstanz)
 * 
 * @param <L>
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public class LabelingTypeMerge<L extends Comparable<L>> implements
		BinaryOperation<LabelingType<L>, LabelingType<L>, LabelingType<L>> {

	@Override
	public LabelingType<L> compute(LabelingType<L> input1,
			LabelingType<L> input2, LabelingType<L> output) {

		List<L> labelings = new ArrayList<L>(input1.getLabeling());
		labelings.addAll(input2.getLabeling());
		output.setLabeling(labelings);

		return output;

	}

	@Override
	public BinaryOperation<LabelingType<L>, LabelingType<L>, LabelingType<L>> copy() {
		return new LabelingTypeMerge<L>();
	}

}
