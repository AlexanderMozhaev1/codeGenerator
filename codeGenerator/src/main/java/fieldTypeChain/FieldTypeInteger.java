package fieldTypeChain;

public class FieldTypeInteger extends FieldType{
    @Override
    public String convert(String type) {
        if("число".equals(type) || "целое".equals(type)) return "int";
        return checkNext(type);
    }
}
