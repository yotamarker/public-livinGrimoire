package chobit;
import java.util.Hashtable;

/* the actual action
 * for example go left, jump right
 * see outerCerabellum class for more detail
 * */
public class PrimoCera {
	private Hashtable<Integer, String> numAction = new Hashtable<>();
	private Hashtable<String, Integer> keyvalNum = new Hashtable<>();

	public void saveAction(String str, int num) {
		keyvalNum.put(str, num);
	}
	public int getFinalActionCode() {
		return numAction.size();
	}
}
