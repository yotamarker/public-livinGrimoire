package chobit;
// data class.
public class DCStrPair<T> {
	public T key;
	public T value;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return (String) key + (String) value;
	}
}
