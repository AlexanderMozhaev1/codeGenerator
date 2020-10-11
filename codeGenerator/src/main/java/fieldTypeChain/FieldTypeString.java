package fieldTypeChain;

public class FieldTypeString extends FieldType {
    @Override
    public String convert(String type) {
        if("строка".equals(type))return "String";
        return checkNext(type);
    }
}
