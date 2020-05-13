package com.eranda;

import com.eranda.paper.A3;
import com.eranda.paper.A4;
import com.eranda.paper.Type;

import java.util.HashMap;
import java.util.Map;

public class CopierMemory {

    private Map<PaperType, Type> paperCopies = new HashMap<PaperType, Type>();

    public CopierMemory() {
        this.keepCopy();
    }

    public Type getLastScan(PaperType paperType) throws CloneNotSupportedException {

        return (Type) paperCopies.get(paperType).clone();
    }

    private void keepCopy() {

        A3 a3 = new A3();
        a3.setText("Last scanned text to A3");
        a3.setBreadthInMillimeters(329);
        a3.setLengthInMillimeters(483);

        A4 a4 = new A4();
        a4.setText("Last scanned text to A4");
        a4.setBreadthInMillimeters(210);
        a4.setLengthInMillimeters(297);

        paperCopies.put(PaperType.A3, a3);
        paperCopies.put(PaperType.A4, a4);
    }
}


