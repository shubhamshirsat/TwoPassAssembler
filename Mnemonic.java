public class Mnemonic {

    String mnemonic = "";
    String opcode = "";
    String statement = "";
    int length;

    public Mnemonic(String mnemonic, String opcode, String statement,int length)
    {
        this.mnemonic = mnemonic;
        this.opcode = opcode;
        this.statement = statement;
        this.length = length;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String getOpcode() {
        return opcode;
    }

    public String getStatement(){
        return statement;
    }

    public int getLength() {
        return length;
    }
}
