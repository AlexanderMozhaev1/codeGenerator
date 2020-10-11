package fieldTypeChain;

public class FieldTypeBoolean extends FieldType{
    @Override
    public String convert(String type) {
        if("булевское".equals(type)) return "boolean";
        return checkNext(type);
    }
}
