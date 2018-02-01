package list;

public class LockDListNode extends DListNode{

    private boolean isLocked;

    LockDListNode(Object item){
        super(item);
        isLocked = false;
    }

    LockDListNode(Object item, DListNode prev, DListNode next){
        super(item, prev, next);
        isLocked = false;
    }

    public boolean isLocked(){
        return isLocked;
    }

    public void setLocked(){
        isLocked = true;
    }
}
