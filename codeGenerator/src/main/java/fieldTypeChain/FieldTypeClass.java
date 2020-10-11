package fieldTypeChain;

public class FieldTypeClass extends FieldType{
    @Override
    public String convert(String type) {
        if(type.matches("[A-Z].*[A-Za-z0-9].*")) return type;
        throw new IllegalArgumentException("Unknown type: " + type);
    }
}
