package Algorithm;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.F3F19601-8203-2471-DAF9-FC86A950C4E5]
// </editor-fold> 
public class Tree extends OrderedKAryTree {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.DA74B1C2-6723-E2C7-DB46-B909A6BABF21]
    // </editor-fold> 
    private Costs parent;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.136F2F40-0F02-3EB1-D190-585F08FCC0D4]
    // </editor-fold> 
    private Costs leftChild;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.D41E8EC2-0ED1-C8E8-9860-83ACEC8321F2]
    // </editor-fold> 
    private Costs rightChild;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3B343261-7E70-2FF6-B9B5-EFCF0E6F7061]
    // </editor-fold> 
    public Tree () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.6A8FA1D2-F33F-BFC9-07C0-07DA38609436]
    // </editor-fold> 
    public Costs getLeftChild () {
        return leftChild;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.80A26715-90F6-7AB9-B950-90F0EF25185B]
    // </editor-fold> 
    public void setLeftChild (Costs val) {
        this.leftChild = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.49BAAA5C-CD17-8D6A-9166-8FF55A2EE984]
    // </editor-fold> 
    public Costs getParent () {
        return parent;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.73642134-E799-5F8F-0B4A-E0C37C0264E9]
    // </editor-fold> 
    public void setParent (Costs val) {
        this.parent = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.28871F3E-4A92-8D2A-69E0-9606CB1525FE]
    // </editor-fold> 
    public Costs getRightChild () {
        return rightChild;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.2BAEAC02-FD82-1C74-DB48-740929C9121A]
    // </editor-fold> 
    public void setRightChild (Costs val) {
        this.rightChild = val;
    }

}

