import fieldTypeChain.FieldType;

import java.util.List;
import java.util.stream.Collectors;

public class ClassSourceFromExcel implements ClassSource {
    private final String name;
    private final List<Field> fields;
    private final FieldType fieldType;

    public ClassSourceFromExcel(String name, List<Field> fields, FieldType fieldType) {
        this.name = name;
        this.fields = fields;
        this.fieldType = fieldType;
    }

    @Override
    public String generationCode() {
        StringBuilder stringClass = new StringBuilder("public class " + name + " {");
        stringClass.append(generationField());
        stringClass.append(generationConstructor());
        stringClass.append(generationGetSet());
        stringClass.append("\n}");
        return new String(stringClass);
    }

    private StringBuilder generationField() {
        StringBuilder stringField = new StringBuilder("");
        for (Field field : fields) {
            if (field.getDescription().length() != 0) {
                String description = "\n\t/** " + field.getDescription() + " */";
                stringField.append(description);
            }
            StringBuilder type = getTypeFromField(field);
            String name = field.getName();
            stringField.append("\n\tprivate " + type + " " + name + ";");
        }
        return stringField;
    }

    private StringBuilder getTypeFromField(Field field) {
        StringBuilder type;
        try {
            type = new StringBuilder(fieldType.convert(field.getType()));
            if (field.getMultiplicityMax() != '1') {
                type = new StringBuilder("Collection<" + type + ">");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Class: " + name + " contains an unknown type: " + field.getType());
        }
        return type;
    }

    private StringBuilder generationConstructor() {
        List<Field> listFields = fields.stream()
                .filter(f -> f.getMultiplicityMin() == '1')
                .collect(Collectors.toList());
        if (listFields.size() == 0) {
            return new StringBuilder("");
        }
        StringBuilder stringConstructor = new StringBuilder("\n\n\tpublic " +name+ "(");
        StringBuilder stringBody = new StringBuilder("{");
        for (Field field : listFields) {
            StringBuilder type = getTypeFromField(field);
            String name = field.getName();
            stringConstructor.append(type + " " + name + ", ");
            stringBody.append("\n\t\tthis." + name + " = " + name + ";");
        }
        stringConstructor.replace(stringConstructor.length() - 2, stringConstructor.length() - 1, ")");
        stringConstructor.append(stringBody + "\n\t}");
        return stringConstructor;
    }

    private StringBuilder generationGetSet() {
        StringBuilder stringGetSet = new StringBuilder("");
        for (Field field : fields) {
            StringBuilder type = getTypeFromField(field);
            String name = field.getName();
            stringGetSet.append("\n\n\tpublic " + type + " get" +
                    name.substring(0, 1).toUpperCase() + name.substring(1)
                    + "() {\n\t\treturn " + name + ";\n\t}");
            stringGetSet.append("\n\n\tpublic void set" +
                    name.substring(0, 1).toUpperCase() + name.substring(1)
                    + "(" + type + " " + name+") {\n\t\tthis." + name + " = " + name +";\n\t}");
        }
        return stringGetSet;
    }

    @Override
    public String getName() {
        return name;
    }
}
