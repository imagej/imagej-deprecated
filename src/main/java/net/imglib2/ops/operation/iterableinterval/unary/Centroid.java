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

package net.imglib2.ops.operation.iterableinterval.unary;

import net.imglib2.Cursor;
import net.imglib2.IterableInterval;
import net.imglib2.ops.operation.UnaryOperation;
import net.imglib2.type.Type;

/**
 * @author Felix Schoenenberger (University of Konstanz)
 * @author Christian Dietz (University of Konstanz)
 * @deprecated Use net.imagej.ops instead.
 */
@Deprecated
public final class Centroid
                implements
                UnaryOperation<IterableInterval<? extends Type<?>>, double[]> {

        @Override
        public final double[] compute(
                        final IterableInterval<? extends Type<?>> op,
                        final double[] r) {

                if (r.length != op.numDimensions()) {
                        throw new IllegalArgumentException(
                                        "Number of dimensions in result array do not fit (Centroid)");
                }

                Cursor<? extends Type<?>> c = op.cursor();

                while (c.hasNext()) {
                        c.fwd();
                        for (int i = 0; i < r.length; i++) {
                                r[i] += c.getDoublePosition(i);
                        }
                }

                for (int i = 0; i < r.length; i++) {
                        r[i] /= op.size();
                }

                return r;
        }

        @Override
        public UnaryOperation<IterableInterval<? extends Type<?>>, double[]> copy() {
                return new Centroid();
        }
}
