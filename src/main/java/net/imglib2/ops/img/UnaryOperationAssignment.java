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

package net.imglib2.ops.img;

import net.imglib2.Cursor;
import net.imglib2.IterableInterval;
import net.imglib2.ops.operation.UnaryOperation;
import net.imglib2.type.Type;

/**
 * @author Christian Dietz (University of Konstanz)
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public class UnaryOperationAssignment< T extends Type< T >, V extends Type< V >> implements UnaryOperation< IterableInterval< T >, IterableInterval< V >>
{

	private final UnaryOperation< T, V > m_op;

	public UnaryOperationAssignment( final UnaryOperation< T, V > op )
	{
		m_op = op;
	}

	@Override
	public IterableInterval< V > compute( IterableInterval< T > input, IterableInterval< V > output )
	{

		if ( !input.iterationOrder().equals( output.iterationOrder() ) ) { throw new IllegalArgumentException( "Intervals in UnaryOperationAssignment are not compatible: different dimensions." ); }

		final Cursor< T > inCursor = input.cursor();
		final Cursor< V > outCursor = output.cursor();
		while ( inCursor.hasNext() )
		{
			inCursor.fwd();
			outCursor.fwd();
			m_op.compute( inCursor.get(), outCursor.get() );
		}

		return output;
	}

	@Override
	public UnaryOperation< IterableInterval< T >, IterableInterval< V >> copy()
	{
		return new UnaryOperationAssignment< T, V >( m_op.copy() );
	}
}
