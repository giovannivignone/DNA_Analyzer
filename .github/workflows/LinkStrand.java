public class LinkStrand implements IDnaStrand {
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myLocalIndex;
    private Node myCurrent;
    private int myIndex;

    private class Node {
        String info;
        Node next;

        public Node(String s) {
            info = s;
            next = null;
        }
    }


    public LinkStrand(String str) {
        initialize(str);
    }

    public LinkStrand() {
        this("");
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
        Node temp = new Node(source);
        myFirst = temp;
        myLast = temp;
        mySize = source.length();
        myLocalIndex = 0;
        myCurrent = myFirst;
        myIndex = 0;
        myAppends = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        Node temporary = new Node(dna);
        myLast.next = temporary;
        myLast = temporary;
        mySize += dna.length();
        myAppends++;
        return this;
    }

    private StringBuilder r(String str) {
        StringBuilder word = new StringBuilder(str);
        StringBuilder rev = word.reverse();
        return rev;
    }

    @Override
    public IDnaStrand reverse() {
        LinkStrand link = new LinkStrand();
        Node list = myFirst;
        while (list != null) {
            Node rev = new Node(r(list.info).toString());
            rev.next = link.myFirst;
            link.myFirst = rev;
            link.mySize += rev.info.length();
            link.myAppends++;
            list = list.next;
        }
        return link;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node list = myFirst;
        while (!(list == null)) {
            str.append(list.info);
            list = list.next;
        }
        return str.toString();
    }

    @Override
    public char charAt(int index) {
        if (index >= mySize || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (myIndex>index){
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        while (myIndex != index) {
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex >= myCurrent.info.length()) {
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);
    }

}
