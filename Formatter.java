public class Formatter {

    public static String formatContent(String content) {
        content = content.replaceAll(",", ", ");

        content = content.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ");

        content = content.replaceAll("\\[", "[ ").replaceAll("\\]", " ]");

        content = content.replaceAll("(?<=\\S)(\\+|\\-|\\*|\\/|\\=|>|<|>=|<=|==|&&|\\|\\|)(?=\\S)", " $1 ");
        content = content.replaceAll("(?<=\\S)(\\&)(?=\\S)", " $1 ");
        content = content.replaceAll("(?<=\\S)(\\|)(?=\\S)", " $1 ");

        content = content.replaceAll("\\{", "\n{\n").replaceAll("\\}", "\n}\n");

        content = content.replaceAll(";", ";\n");

        content = content.replaceAll("\\)\\s*;\n", ");");

        content = content.replaceAll("(?m)^\\t", "    ");

        content = content.replaceAll("([^ ]) {2}", "$1 ");

        content = applyIndentation(content);

        return content;
    }

    private static String applyIndentation(String content) {
        String[] lines = content.split("\n");
        StringBuilder formattedContent = new StringBuilder();
        int indentLevel = 0;

        for (String line : lines) {
            line = line.trim();
            if (line.equals("}")) {
                indentLevel--;
            }
            for (int i = 0; i < indentLevel; i++) {
                formattedContent.append("    ");
            }
            formattedContent.append(line).append("\n");
            if (line.equals("{")) {
                indentLevel++;
            }
        }
        return formattedContent.toString();
    }
}
