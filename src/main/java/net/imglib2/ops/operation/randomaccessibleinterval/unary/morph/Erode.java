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

package net.imglib2.ops.operation.randomaccessibleinterval.unary.morph;

import net.imglib2.RandomAccessibleInterval;
import net.imglib2.ops.operation.UnaryOperation;
import net.imglib2.ops.types.ConnectedType;
import net.imglib2.outofbounds.OutOfBoundsFactory;
import net.imglib2.type.logic.BitType;

/**
 * @author Felix Schoenenberger (University of Konstanz)
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public final class Erode implements UnaryOperation< RandomAccessibleInterval< BitType >, RandomAccessibleInterval< BitType > >
{

	private final int m_neighbourhoodCount;

	private final ConnectedType m_type;

	private final BinaryOps m_binOps;

	private final OutOfBoundsFactory< BitType, RandomAccessibleInterval< BitType >> m_factory;

	/**
	 * @param type
	 * @param neighbourhoodCount
	 * @param iterations
	 *            number of iterations, at least 1
	 */
	public Erode( ConnectedType type, OutOfBoundsFactory< BitType, RandomAccessibleInterval< BitType > > factory, final int neighbourhoodCount )
	{
		m_neighbourhoodCount = neighbourhoodCount;
		m_type = type;
		m_binOps = new BinaryOps( m_factory = factory );
	}

	@Override
	public RandomAccessibleInterval< BitType > compute( RandomAccessibleInterval< BitType > op, RandomAccessibleInterval< BitType > r )
	{
		m_binOps.erode( m_type, r, op, m_neighbourhoodCount );
		return r;

	}

	@Override
	public UnaryOperation< RandomAccessibleInterval< BitType >, RandomAccessibleInterval< BitType > > copy()
	{
		return new Erode( m_type, m_factory, m_neighbourhoodCount );
	}
}
