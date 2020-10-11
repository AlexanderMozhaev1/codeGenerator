package fieldTypeChain;

public class FieldTypeLocalDate extends FieldType{
    @Override
    public String convert(String type) {
        if("дата".equals(type)) return "LocalDate";
        return checkNext(type);
    }
}
