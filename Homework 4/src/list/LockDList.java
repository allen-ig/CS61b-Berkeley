package list;

public class LockDList extends DList {

    public DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }

    public void lockNode(DListNode node){
        ((LockDListNode) node).setLocked();
    }

    public void remove(DListNode node){
        if (((LockDListNode) node).isLocked()) return;
        else super.remove(node);
    }
}
