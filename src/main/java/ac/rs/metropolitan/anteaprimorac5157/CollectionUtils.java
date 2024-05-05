package ac.rs.metropolitan.anteaprimorac5157;

import java.util.Collection;
import java.util.Iterator;

public class CollectionUtils {
    // Kopirano iz Apacheovog CollectionUtils-a kako bi se izbjeglo uvođenje glomazne zavisnosti
    public static <T> boolean equals(Collection<T> a, Collection<T> b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.size() != b.size()) {
            return false;
        }
        Iterator<T> aIterator = a.iterator();
        Iterator<T> bIterator = b.iterator();
        while (aIterator.hasNext()) {
            T aElement = aIterator.next();
            T bElement = bIterator.next();
            if (aElement == null && bElement == null) {
                continue;
            }
            if (aElement == null || bElement == null) {
                return false;
            }
            if (!aElement.equals(bElement)) {
                return false;
            }
        }
        return true;
    }
}
