import java.util.Iterator;

/**
 * Interface for DNA/strand experiments
 */
public interface IDnaStrand extends Iterable<Character>{
	default IDnaStrand cutAndSplice(String enzyme, String splicee) {
		int pos = 0;
		int start = 0;
		String search = this.toString();
		boolean first = true;
		IDnaStrand ret = null;

		while ((pos = search.indexOf(enzyme, start)) >= 0) {
			if (first) {
				ret = getInstance(search.substring(start, pos));
				first = false;
			} else {
				ret.append(search.substring(start, pos));

			}
			start = pos + enzyme.length();
			ret.append(splicee);
		}

		if (start < search.length()) {
			if (ret == null) {
				ret = getInstance("");
			} else {
				ret.append(search.substring(start));
			}
		}
		return ret;
	}
	
	public long size();

	
	public void initialize(String source);

	
	public IDnaStrand getInstance(String source);
	
	
	default public String strandInfo() {
		return this.getClass().getName();
	}

	
	public IDnaStrand append(String dna);

	
	public IDnaStrand reverse();

	
	public int getAppendCount();
	
	
	public char charAt(int index);
	
	
	default Iterator<Character> iterator(){
		return new CharDnaIterator(this);
	}
	
}
