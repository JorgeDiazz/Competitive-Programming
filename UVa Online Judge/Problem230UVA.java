import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

class Problem230UVA {

	private static ArrayList<Book> books = new ArrayList<>();
	private static PriorityQueue<Book> returns = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String input;
		while (!(input = in.readLine()).equals("END")) {
			String[] bookData = input.split(" by ");
			books.add(new Book(bookData[0], bookData[1], false));
		}
		
		Collections.sort(books);

		cmdBlock: {
			while (true) {
				String[] cmdData = in.readLine().split(" ", 2);
				switch (cmdData[0]) {
				case "BORROW":
					getBook(cmdData[1]).setBorrowed(true);
					break;
				case "RETURN":
					returns.offer(getBook(cmdData[1]));
					break;
				case "SHELVE":
					printBooksCurrentState();
					break;
				default:
					break cmdBlock;
				}
			}
		}
	}

	private static Book getBook(String name) {
		for (Book book : books) {
			if (book.getName().equals(name))
				return book;
		}
		return null;
	}

	private static void printBooksCurrentState() {
		while (!returns.isEmpty()) {
			Book bookReturned = returns.poll();
			String prevBook = getBookBeforeTo(bookReturned);
			System.out.println(
					"Put " + bookReturned.getName() + " " + (prevBook == null ? "first" : "after " + prevBook));
		}
		System.out.println("END");
	}

	private static String getBookBeforeTo(Book bookReturned) {
		String prevBook = null;
		for (Book book : books) {
			if (book.equals(bookReturned)) {
				book.setBorrowed(false);
				break;
			}
			if (!book.isBorrowed())
				prevBook = book.getName();
		}
		return prevBook;
	}
}

class Book implements Comparable<Book> {
	private String name;
	private String authors;
	private boolean borrowed;

	public Book(String name, String autors, boolean borrowed) {
		this.name = name;
		this.authors = autors;
		this.borrowed = borrowed;
	}

	public String getName() {
		return name;
	}

	public boolean isBorrowed() {
		return borrowed;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	@Override
	public int compareTo(Book b2) {
		int compareValue = this.authors.compareTo(b2.authors);
		return compareValue != 0 ? compareValue : this.name.compareTo(b2.name);
	}
}
