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

package net.imglib2.ops.condition;

import net.imglib2.RealRandomAccess;
import net.imglib2.roi.RegionOfInterest;
import net.imglib2.type.logic.BitType;

/**
 * A {@link Condition} that returns true when a given coordinate's first two
 * axis values are part of a {@link RegionOfInterest}'s XY region.
 *   
 * @author Barry DeZonia
 *
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public class UVInsideRoiCondition implements Condition<long[]> {

	// -- instance variables --
	
	private final RegionOfInterest roi;
	private final RealRandomAccess<BitType> accessor;
	
	// -- constructor --
	
	public UVInsideRoiCondition(RegionOfInterest roi) {
		this.roi = roi;
		this.accessor = roi.realRandomAccess();
	}
	
	// -- Condition methods --
	
	@Override
	public boolean isTrue(long[] val) {
		accessor.setPosition(val[0],0); // U == index 0
		accessor.setPosition(val[1],1); // V == index 1
		return accessor.get().get();
	}

	@Override
	public UVInsideRoiCondition copy() {
		return new UVInsideRoiCondition(roi);
	}
	
}
