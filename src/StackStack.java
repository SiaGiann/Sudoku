import java.util.Stack;

/**
 * Κλάση StackStack για την Δομή δεδομένων 3
 * Κατασκευάζει στοίβα υλοποιημένη με την κλάση Stack της Java
 * και υλοποιεί τη διεπαφή QueueOrStack, εκθέτοντας τις κοινές μεθόδους:
 *  - pop για εξαγωγή από την κορυφή της στοίβας (pop)
 *  - push για εισαγωγή στην κορυφή της στοίβας (push)
 *  - και isEmpty για έλεγχο αν η στοίβα είναι άδεια
 */
public class StackStack implements QueueOrStack {
    private Stack<Sudoku> elements = new Stack<>();

    public StackStack() {
        System.out.println("Method 3: Stack implemented with Stack");
    }

    public Sudoku pop() {
        if (elements.isEmpty()) {
            return null;
        }

        return elements.pop();
    }

    public void push(Sudoku element) {
        elements.push(new Sudoku(element.getBoard()));
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
