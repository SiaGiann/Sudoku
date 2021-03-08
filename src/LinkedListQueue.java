import java.util.LinkedList;

/**
 * Κλάση LinkedListQueue για την Δομή δεδομένων 4
 * Κατασκευάζει ουρά υλοποιημένη με την κλάση LinkedList της Java
 * και υλοποιεί τη διεπαφή QueueOrStack, εκθέτοντας τις κοινές μεθόδους:
 *  - pop για εξαγωγή από την αρχή της ουράς (dequeue)
 *  - push για εισαγωγή στην απόληξη της ουράς (enqueue)
 *  - και isEmpty για έλεγχο αν η ουρά είναι άδεια
 */
public class LinkedListQueue implements QueueOrStack {
    private LinkedList<Sudoku> elements = new LinkedList<>();

    public LinkedListQueue() {
        System.out.println("Method 4: Queue implemented with LinkedList");
    }

    // μέθοδος dequeue
    public Sudoku pop() {
        if (elements.isEmpty()) {
            return null;
        }

        return elements.removeLast();
    }

    // μέθοδος enqueue
    public void push(Sudoku element) {
        elements.addFirst(new Sudoku(element.getBoard()));
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}

