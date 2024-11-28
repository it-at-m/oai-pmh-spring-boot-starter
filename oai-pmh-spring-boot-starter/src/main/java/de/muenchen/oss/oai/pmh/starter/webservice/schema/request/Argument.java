package de.muenchen.oss.oai.pmh.starter.webservice.schema.request;

public enum Argument {

    IDENTIFIER("identifier"),
    FROM("from"),
    UNTIL("until"),
    METADATA_PREFIX("metadataPrefix"),
    SET("set"),
    RESUMPTION_TOKEN("resumptionToken"),
    VERB(
            "verb"),
    COMPLETE("complete");

    private final String value;

    Argument(String v) {
        value = v;
    }

    public static Argument fromValue(String v) {
        for (Argument c : Argument.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }
}
