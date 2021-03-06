/*
 * Copyright (c) 2016 Petr Rysavy <rysavpe1@fel.cvut.cz>
 *
 * This file is part of the project readsIDA2016, which is available on 
 * <https://github.com/petrrysavy/readsIDA2016/>.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with This program.  If not, see <http ://www.gnu.org/licenses/>.
 */
package cz.cvut.fel.ida.reads.readsIDA2016.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Petr Ryšavý
 */
public class MathUtilsTest {

    @Test
    public void testMin_Int() {
        assertThat(MathUtils.min(1, 2, 3), is(equalTo(1)));
        assertThat(MathUtils.min(1, 3, 2), is(equalTo(1)));
        assertThat(MathUtils.min(2, 1, 3), is(equalTo(1)));
        assertThat(MathUtils.min(2, 3, 1), is(equalTo(1)));
        assertThat(MathUtils.min(3, 2, 1), is(equalTo(1)));
        assertThat(MathUtils.min(3, 1, 2), is(equalTo(1)));
    }

    @Test
    public void testMin_Double() {
        assertThat(MathUtils.min(1.0, 2.0, 3.0), is(equalTo(1.0)));
        assertThat(MathUtils.min(1.0, 3.0, 2.0), is(equalTo(1.0)));
        assertThat(MathUtils.min(2.0, 1.0, 3.0), is(equalTo(1.0)));
        assertThat(MathUtils.min(2.0, 3.0, 1.0), is(equalTo(1.0)));
        assertThat(MathUtils.min(3.0, 2.0, 1.0), is(equalTo(1.0)));
        assertThat(MathUtils.min(3.0, 1.0, 2.0), is(equalTo(1.0)));
    }

    @Test
    public void testAverage() {
        assertThat(MathUtils.average(0.0, 0.0), is(equalTo(0.0)));
        assertThat(MathUtils.average(1.0, 1.0), is(equalTo(1.0)));
        assertThat(MathUtils.average(1.0, 0.0), is(equalTo(0.5)));
        assertThat(MathUtils.average(0.0, 1.0), is(equalTo(0.5)));
        assertThat(MathUtils.average(0.5291677427, 0.1145972106), is(equalTo(0.32188247665)));
        assertThat(MathUtils.average(0.1145972106, 0.5291677427), is(equalTo(0.32188247665)));
    }
}
