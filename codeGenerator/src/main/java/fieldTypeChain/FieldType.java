package fieldTypeChain;

public abstract class FieldType {
    private FieldType next;

    public FieldType linkWith(FieldType next) {
        this.next = next;
        return next;
    }

    public abstract String convert(String type);

    protected String checkNext(String type) {
        if(type == null){
            throw new IllegalArgumentException("Unknown type: " + type);
        }
        try {
            return next.convert(type);
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
