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

import java.util.ArrayList;
import java.util.List;

/**
* A {@link Condition} that aggregates a list of other Conditions as a group.
* This Condition is true when any of the other Conditions are true.
* 
* @author Barry DeZonia
*
* @deprecated Use net.imagej.ops instead.
*/
@Deprecated
public class UnionCondition<T> implements Condition<T> {
	private final Condition<T> condition;
	
	public UnionCondition(List<Condition<T>> conditions) {
		if (conditions.size() == 0)
			throw new IllegalArgumentException("no conditions provided");
		else if (conditions.size() == 1)
			condition = conditions.get(0);
		else {
			OrCondition<T> or =
					new OrCondition<T>(conditions.get(0), conditions.get(1));
			for (int i = 2; i < conditions.size(); i++)
				or = new OrCondition<T>(or, conditions.get(i));
			condition = or;
		}
	}

	@Override
	public boolean isTrue(T val) {
		return condition.isTrue(val);
	}

	@Override
	public UnionCondition<T> copy() {
		final List<Condition<T>> conditions = new ArrayList<Condition<T>>();
		conditions.add(condition.copy());
		return new UnionCondition<T>(conditions);
	}
}
