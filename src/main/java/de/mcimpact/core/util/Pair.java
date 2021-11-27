package de.mcimpact.core.util;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.StringJoiner;


/**
 * @param <E>
 * @author tdr@mcimpact.de
 */
public class Pair<E> implements Serializable {

    private final E first;
    private final E second;


    public Pair(E first, E second) {
        this.first = first;
        this.second = second;
    }

    public E getFirst() {
        return first;
    }

    public E getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?> pair = (Pair<?>) o;
        return Objects.equal(first, pair.first) && Objects.equal(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first, second);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pair.class.getSimpleName() + "[", "]")
                .add("first=" + first)
                .add("second=" + second)
                .toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Pair<E>(first, second);
    }
}
