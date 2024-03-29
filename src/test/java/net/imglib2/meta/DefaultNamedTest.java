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

package net.imglib2.meta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests {@link DefaultNamed}.
 * 
 * @author Barry DeZonia
 */
public class DefaultNamedTest extends AbstractMetaTest {

	private DefaultNamed named;

	@Test
	public void test1() {
		named = new DefaultNamed();
		assertEquals("", named.getName());
		named.setName("FWEEB");
		assertEquals("FWEEB", named.getName());
	}

	@Test
	public void test2() {
		named = new DefaultNamed("ZUBBY");
		assertEquals("ZUBBY", named.getName());
		named.setName("FWEEB");
		assertEquals("FWEEB", named.getName());
	}

	@Test
	public void test3() {
		DefaultNamed other = new DefaultNamed();
		named = new DefaultNamed(other);
		assertEquals("", named.getName());
		assertEquals("", other.getName());
		other = new DefaultNamed("KARCHY");
		named = new DefaultNamed(other);
		assertEquals("KARCHY", named.getName());
		assertEquals("KARCHY", other.getName());
		named.setName("FWEEB");
		assertEquals("FWEEB", named.getName());
		assertEquals("KARCHY", other.getName());
	}
}
