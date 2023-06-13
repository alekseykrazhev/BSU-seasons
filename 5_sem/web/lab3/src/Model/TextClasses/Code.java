package Model.TextClasses;

/**
 * Class Code describing part of Text
 */
public class Code extends TextPart {

    /**
     * private field code
     */
    private final String code;

    /**
     * GetMethod for private field code
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Constructor with parameter
     */
    public Code(String code) {
        this.code = code;
    }

    /**
     * Override method toString()
     * @return code
     */
    @Override
    public String toString() {
        return code;
    }
}
