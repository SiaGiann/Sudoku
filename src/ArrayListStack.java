import java.util.ArrayList;

/**
 * Κλάση ArrayListStack για την Δομή δεδομένων 1
 * Κατασκευάζει στοίβα υλοποιημένη με την κλάση ArrayList της Java
 * και υλοποιεί τη διεπαφή QueueOrStack, εκθέτοντας τις κοινές μεθόδους:
 *  - pop για εξαγωγή από την κορυφή της στοίβας (pop)
 *  - push για εισαγωγή στην κορυφή της στοίβας (push)
 *  - και isEmpty για έλεγχο αν η στοίβα είναι άδεια
 */
public class ArrayListStack implements QueueOrStack {
    private ArrayList<Sudoku> elements = new ArrayList<>();

    public ArrayListStack() {
        System.out.println("Method 1: Stack implemented with ArrayList");
    }

    public Sudoku pop() {
        if (elements.isEmpty()) {
            return null;
        }

        Sudoku top = elements.get(elements.size() - 1);
        elements.remove(elements.size() - 1);

        return top;
    }

    public void push(Sudoku element) {
        elements.add(element);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}

