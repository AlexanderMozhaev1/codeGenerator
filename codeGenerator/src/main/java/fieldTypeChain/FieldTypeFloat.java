package fieldTypeChain;

public class FieldTypeFloat extends FieldType{
    @Override
    public String convert(String type) {
        if("дробное".equals(type))return "float";
        return checkNext(type);
    }
}
