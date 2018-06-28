package pl.laskowski.marcin.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Marcin Laskowski.
 */

public class Region implements Iterable<Field> {

    private final Set<Field> fields;

    public Region(Collection<Field> fields) {
        this.fields = new HashSet<>(fields);
    }

    @Override
    public Iterator<Field> iterator() {
        return fields.iterator();
    }

    public boolean isEmpty() {
        return fields.isEmpty();
    }

    public Region intersect(Region other) {
        Region result = new Region(fields);
        result.fields.retainAll(other.fields);
        return result;
    }

    public Region subtract(Region other) {
        Region result = new Region(fields);
        result.fields.removeAll(other.fields);
        return result;
    }

    public boolean isValid() {
        Set<Integer> set = new HashSet<>();
        for (Field field : fields) {
            if (alreadyContainsFieldValue(field, set)) {
                return false;
            } else {
                set.add(field.value());
            }
        }
        return true;
    }

    private boolean alreadyContainsFieldValue(Field field, Set<Integer> numbers) {
        return !field.isEmpty() && numbers.contains(field.value());
    }

    public boolean containsFieldWithSamePosition(Field field) {
        for (Field f : fields) {
            if (f.haveSamePosition(field)) return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        return fields.equals(region.fields);
    }

    @Override
    public int hashCode() {
        return fields.hashCode();
    }

    public int size() {
        return fields.size();
    }

    public List<Field> fullFields() {
        return fields.stream()
                .filter(field -> !field.isEmpty())
                .collect(Collectors.toList());
    }

    public List<Field> emptyFields() {
        return fields.stream()
                .filter(Field::isEmpty)
                .collect(Collectors.toList());
    }

}
