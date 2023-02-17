package idea.verlif.markdone;

import idea.verlif.markdone.builder.block.CodeBlockItem;
import idea.verlif.markdone.builder.block.OrderedListBlockItem;
import idea.verlif.markdone.builder.block.QuoteBlockItem;
import idea.verlif.markdone.builder.block.TableBlockItem;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void codeBlockTest() {
        CodeBlockItem builder = new CodeBlockItem(1, "java");
        builder.start()
                .content("    public CodeBlockBuilder code(String code) {\n" +
                        "        String[] strings = code.split(\"\\n\");\n" +
                        "        for (String string : strings) {\n" +
                        "            line(string);\n" +
                        "        }\n" +
                        "        return this;\n" +
                        "    }")
                .build();
        System.out.println(builder);
    }

    @Test
    public void quoteBlockTest() {
        QuoteBlockItem builder = new QuoteBlockItem(1);
        builder.start()
                .content("    public CodeBlockBuilder code(String code) {\n" +
                        "        String[] strings = code.split(\"\\n\");\n" +
                        "        for (String string : strings) {\n" +
                        "            line(string);\n" +
                        "        }\n")
                .content(
                        "        return this;\n" +
                        "    }")
                .build();
        System.out.println(builder);
    }

    @Test
    public void orderedBlockBuilder() {
        OrderedListBlockItem builder = new OrderedListBlockItem(1);
        builder.start()
                .indexFrom(4)
                .content("123")
                .content("321")
                .build();
        System.out.println(builder);
    }

    @Test
    public void TableBlockBuilder() {
        TableBlockItem builder = new TableBlockItem(1);
        builder.start()
                .titles("1", "2", "3")
                .title("4", TableBlockItem.FLOW.RIGHT)
                .values("v1", "v2", "v3", "v4")
                .values("vv1", "vv2", "vv3", "vv4")
                .repeatTitle()
                .values("vv1", "vv2", "vv3", "vv4")
                .build();
        System.out.println(builder);
    }
}
