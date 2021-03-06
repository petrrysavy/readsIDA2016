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
package cz.cvut.fel.ida.reads.readsIDA2016.model;

import java.util.Arrays;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * @author Petr Ryšavý
 */
public class HashMultisetTest {

    private HashMultiset<Integer> multiset;

    @Before
    public void init() {
        multiset = new HashMultiset<>();
        multiset.add(0);
        multiset.add(1);
        multiset.add(0);
        multiset.add(2);
        multiset.add(4);
        multiset.add(1);
        multiset.add(0);
        multiset.add(4);
    }

    @Test
    public void constructEmptySet() {
        multiset = new HashMultiset<>();
        assertThat(multiset.size(), is(equalTo(0)));
        assertThat(multiset.contains(0), is(false));
        assertThat(multiset.count(0), is(equalTo(0)));
        assertThat(multiset.iterator().hasNext(), is(false));
    }

    @Test
    public void constructViaCollection() {
        HashMultiset<Integer> multiset2 = new HashMultiset<>(Arrays.asList(0, 1, 0, 2, 4, 1, 0, 4));
        assertThat(multiset2, is(equalTo(multiset)));
    }

    @Test
    public void constructViaVarargs() {
        HashMultiset<Integer> multiset2 = new HashMultiset<>(0, 1, 0, 2, 4, 1, 0, 4);
        assertThat(multiset2, is(equalTo(multiset)));
    }

    @Test
    public void testIterator() {
        assertThat(multiset, containsInAnyOrder(0, 0, 0, 1, 1, 2, 4, 4));
    }

    @Test
    public void testCount() {
        assertThat(multiset.count(0), is(equalTo(3)));
        assertThat(multiset.count(1), is(equalTo(2)));
        assertThat(multiset.count(2), is(equalTo(1)));
        assertThat(multiset.count(3), is(equalTo(0)));
        assertThat(multiset.count(4), is(equalTo(2)));
    }

    @Test
    public void testContains() {
        assertThat(multiset.contains(0), is(true));
        assertThat(multiset.contains(1), is(true));
        assertThat(multiset.contains(2), is(true));
        assertThat(multiset.contains(3), is(false));
        assertThat(multiset.contains(4), is(true));
    }

    @Test
    public void testSize() {
        assertThat(multiset.size(), is(equalTo(8)));
    }

    @Test
    public void testClear() {
        multiset.clear();
        assertThat(multiset.size(), is(equalTo(0)));
        assertThat(multiset.iterator().hasNext(), is(false));
    }

    @Test
    public void testAdd() {
        multiset.add(5);
        assertThat(multiset, containsInAnyOrder(0, 0, 0, 1, 1, 2, 4, 4, 5));
    }

    @Test
    public void testAddCount() {
        multiset.add(5, 10);
        assertThat(multiset.count(5), is(equalTo(10)));
    }

    @Test
    public void testToSet() {
        assertThat(multiset.toSet(), is(equalTo(new HashSet<>(Arrays.asList(0, 1, 2, 4)))));
    }

    @Test
    public void testRemove() {
        multiset.remove(2);
        multiset.remove(0);
        multiset.remove(5);
        assertThat(multiset.size(), is(equalTo(6)));
        assertThat(multiset.count(0), is(equalTo(2)));
        assertThat(multiset.count(2), is(equalTo(0)));
        assertThat(multiset, containsInAnyOrder(0, 0, 1, 1, 4, 4));
    }

    @Test
    public void testUnion() {
        multiset.clear();
        multiset.add(1, 3);
        multiset.add(2, 5);
        multiset.add(0);
        Multiset<Integer> multiset2 = new HashMultiset<>();
        multiset2.add(3);
        multiset2.add(2, 5);
        assertThat(multiset.size(), is(equalTo(9)));
        assertThat(multiset2.size(), is(equalTo(6)));
        Multiset<Integer> union = multiset.union(multiset2);
        assertThat(union.size(), is(equalTo(15)));
        assertThat(union.count(-1), is(equalTo(0)));
        assertThat(union.count(0), is(equalTo(1)));
        assertThat(union.count(1), is(equalTo(3)));
        assertThat(union.count(2), is(equalTo(10)));
        assertThat(union.count(3), is(equalTo(1)));
        assertThat(union.count(4), is(equalTo(0)));
    }

    @Test
    public void testClone() {
        Multiset<Integer> clone = multiset.clone();
        assertThat(clone, is(equalTo(multiset)));
    }
}
