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

package net.imglib2.ops.operation.iterableinterval.unary.multilevelthresholder;

import java.util.Arrays;

/**
 * @author Markus Friedrich (University of Konstanz)
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public class ThresholdValueCollection
{

	public ThresholdValueCollection( int numberOfLevels )
	{
		m_thresholdValues = new double[ numberOfLevels - 1 ];
		m_numberOfLevels = numberOfLevels;
	}

	public double get( int i )
	{
		return m_thresholdValues[ i ];
	}

	public void set( int i, double value )
	{
		m_thresholdValues[ i ] = value;
	}

	public int getNumberOfLevels()
	{
		return m_numberOfLevels;
	}

	public double[] getSortedVector()
	{
		double[] sortedCopy = m_thresholdValues.clone();
		Arrays.sort( sortedCopy );
		return sortedCopy;
	}

	public void scale( int numBins, double min, double max )
	{
		double factor = ( Math.abs( max - min ) / numBins );
		for ( int i = 0; i < m_thresholdValues.length; i++ )
		{
			m_thresholdValues[ i ] = m_thresholdValues[ i ] * factor;
		}
	}

	private double[] m_thresholdValues;

	private int m_numberOfLevels;
}
