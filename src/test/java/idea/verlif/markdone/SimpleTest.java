package idea.verlif.markdone;

import idea.verlif.markdone.builder.Inline.InlineItem;
import idea.verlif.markdone.builder.block.CodeItem;
import idea.verlif.markdone.builder.block.OrderedListItem;
import idea.verlif.markdone.builder.block.QuoteItem;
import idea.verlif.markdone.builder.block.TableItem;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void codeBlockTest() {
        CodeItem builder = new CodeItem(1, "java");
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
        QuoteItem builder = new QuoteItem(1);
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
        OrderedListItem builder = new OrderedListItem(1);
        builder.start()
                .indexFrom(4)
                .content("123")
                .content("321")
                .build();
        System.out.println(builder);
    }

    @Test
    public void TableBlockBuilder() {
        TableItem builder = new TableItem();
        builder.start()
                .titles("1", "2")
                .title("3", TableItem.FLOW.CENTER)
                .title("4", TableItem.FLOW.RIGHT)
                .values("v1", "v2", "v3", "v4")
                .values("vv1", "vv2", "vv3", "vv4")
                .repeatTitle()
                .values("vv1", "vv2", "vv3", "vv4")
                .build();
        System.out.println(builder);
    }

    @Test
    public void SimpleLineTest() {
        InlineItem inlineItem = new InlineItem()
                .bold("粗体").newLine()
                .italic("斜体").newLine()
                .code("内嵌代码").newLine()
                .url("https://github.com/").newLine()
                .img("图片", "https://avatars.githubusercontent.com/u/39126497?s=40&v=4", "我的头像").newLine()
                .src("Verlif", "https://github.com/", "github").newLine()
                .delete("删除线").newLine()
                .emoji("smile").newLine()
                .warpedTag("strong", "strong tag");
        System.out.println(inlineItem.build());
    }

    @Test
    public void test() {
        MarkDone markDone = new MarkDone();
        MarkDone.Editor editor = markDone.getEditor();
        markDone.append(editor.reference("github", "https://github.com"))
                .append(editor.reference("github", "g1", "https://github.com"))
                .append(editor.reference("Bing", "https://bing.com"))
                .append(editor.footnote("这个啊，哈哈哈哈哈", "挺好的"))
                .append(editor.reference("github", "g2", "https://github.com"))
                .append(editor.reference("github", "https://github.com"))
                .append(editor.footnote("这个啊，哈哈哈哈哈", "23", "挺好的"));
        System.out.println(markDone.build());
    }
}
