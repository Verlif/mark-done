package idea.verlif.markdone;

import idea.verlif.markdone.builder.Inline.InlineItem;
import idea.verlif.markdone.builder.Inline.SimpleLineItem;
import idea.verlif.markdone.builder.Inline.SimpleStyle;
import idea.verlif.markdone.builder.block.*;
import org.junit.Test;

public class MainTest {

    @Test
    public void test() {
        MarkDone markDone = new MarkDone();
        markDone.append(new InlineItem().heading(1, "MarkDone"))
                .append(new SimpleLineItem()
                        .line("MarkDone是一个Markdown文件编辑工具")
                        .append("但它并非是一个编辑器，而是一个构建器。MarkDone的目的是为了帮助需要在程序中输出Markdown，")
                        .style(SimpleStyle.BOLD, "MarkDone")
                        .style(SimpleStyle.CODE, "Markdown")
                        .append("MarkDone是一个Markdown构建器", SimpleStyle.BOLD).line("。").build())
                .append(new InlineItem()
                        .bold("MarkDone").output("是一个").code("Markdown").output("文件编辑工具").newLine()
                        .output("但它并非是一个编辑器，而是一个构建器。").bold("MarkDone").output("的目的是为了帮助需要在程序中输出Markdown，").bold("MarkDone是一个Markdown构建器").output("。").newLine())
                .append(new InlineItem().heading(2, "特点"))
                .append(new DisorderedListBlockItem().start()
                        .content("链式构建，所见即所得")
                        .content("独立组件，每个组件可以单独使用")
                        .content("自由模式，支持任意形式的样式内嵌")
                        .build())
                .append(new InlineItem().heading(2, "举例"))
                .append(new CodeBlockItem("java").start()
                        // TODO: 等待复制
                        .content("").build())
                .append(new InlineItem().heading(2, "支持的元素"))
                .append(new TableBlockItem().start()
                        .title("元素", TableBlockItem.FLOW.CENTER)
                        .title("对应组件", TableBlockItem.FLOW.CENTER)
                        .values("标题", new InlineItem().code("InlineItem.heading(int, String)"))
                        .values("粗体", new InlineItem().code("InlineItem.bold(String)"))
                        .values("斜体", new InlineItem().code("InlineItem.italic(String)"))
                        .values("删除线", new InlineItem().code("InlineItem.delete(String)"))
                        .values("行内代码", new InlineItem().code("InlineItem.code(String)"))
                        .values("链接", new InlineItem().code("InlineItem.url(String)"))
                        .values("资源链接", new InlineItem().code("InlineItem.src(String, String [, String])"))
                        .values("图片", new InlineItem().code("InlineItem.img(String, String [, String])"))
                        .values("代码块", new InlineItem().code("CodeBlockItem()"))
                        .values("有序列表", new InlineItem().code("OrderedListBlockItem()"))
                        .values("无序列表", new InlineItem().code("DisorderedListBlockItem()"))
                        .values("分割线", new InlineItem().code("HorizontalRuleItem()"))
                        .values("引用块", new InlineItem().code("QuoteBlockItem()"))
                        .values("表格", new InlineItem().code("TableBlockItem()"))
                        .values("任务列表", new InlineItem().code("TodoBlockItem()"))
                        .values("脚注", new InlineItem().code("MarkDone.tag(String, String)"))
                        .build());
        System.out.println(markDone.build());
        System.out.println("---------结束---------");
    }

    @Test
    public void allTest() {
        MarkDone markDone = new MarkDone();
        MarkDone.Editor editor = markDone.getEditor();
        markDone.append(new InlineItem().heading(1, "这个是测试"))
                .append(new InlineItem().heading(2, "简单的内容构建测试"))
                .append(new InlineItem()
                        .bold("粗体").newLine()
                        .italic("斜体").newLine()
                        .code("内嵌代码").newLine()
                        .url("https://github.com/").newLine()
                        .img("图片", "https://avatars.githubusercontent.com/u/39126497?s=40&v=4", "我的头像").newLine()
                        .src("Verlif", "https://github.com/", "github").newLine()
                        .delete("删除线").newLine()
                        .warpedTag("strong", "strong tag").newLine())
                .append(new InlineItem().heading(2, "块级元素构建测试"))
                .append(new InlineItem().heading(3, "代码块"))
                .append(new CodeBlockItem("java").start()
                        .content("System.out.println(\"Hello World!\");").build())
                .append(new InlineItem().heading(3, "无序列表"))
                .append(new DisorderedListBlockItem().start()
                        .content("第一行")
                        .content("第二行")
                        .content(new DisorderedListBlockItem(2).start()
                                .content("第1行")
                                .content("第2行").build())
                        .content("第三行").build())
                .append(new InlineItem().heading(3, "有序列表"))
                .append(new OrderedListBlockItem().start()
                        .content("第一行")
                        .content("第二行")
                        .content(new OrderedListBlockItem(2).start()
                                .content("第1行")
                                .content("第2行").build())
                        .content("第三行").build())
                .append(new InlineItem().heading(3, "引用块"))
                .append(new QuoteBlockItem().start()
                        .content("第一行")
                        .content("第二行")
                        .content(new QuoteBlockItem(2).start()
                                .content("第1行")
                                .content("第2行").build())
                        .content("第三行")
                        .content(new OrderedListBlockItem(2).start()
                                .content("第一条")
                                .content("第二条").build())
                        .content("第四行").build())
                .append(new InlineItem().heading(3, "表格"))
                .append(new TableBlockItem().start()
                        .titles("1", "2", "3")
                        .title("4", TableBlockItem.FLOW.RIGHT)
                        .values("v1", editor.footnote("v2", "1", "v2表示了v2，你明白吧"), "v3", "v4")
                        .values("vv1", "vv2", "vv3", "vv4")
                        .repeatTitle()
                        .values("vv1", "vv2", "vv3", "vv4").build())
                .append(new InlineItem().heading(3, "任务列表"))
                .append(new TodoBlockItem().start()
                        .done("已完成")
                        .undo("未完成")
                        .content(new TodoBlockItem(2).start()
                                .done("已完成")
                                .undo("未完成").build())
                        .undo("未完成列表")
                        .content(new DisorderedListBlockItem(2).start()
                                .content("第一条")
                                .content("第二条").build()).build());
        System.out.println(markDone.build());
    }
}
