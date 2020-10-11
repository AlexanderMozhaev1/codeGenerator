package fieldTypeChain;

public class FieldTypeLocalDateTime extends FieldType{
    @Override
    public String convert(String type) {
        if("дата-время".equals(type) || "дата и время".equals(type)) return "LocalDateTime";
        return checkNext(type);
    }
}
