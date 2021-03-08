/**
 * Κοινή διεπαφή QueueOrStack,
 * για την εύκολη χρήση των 4 δομών δεδομένων
 * που υλοποιούν στοίβες ή λίστες.
 */
public interface QueueOrStack {
	Sudoku pop();
	void push(Sudoku element);
	boolean isEmpty();
}
