# MarkDone

**MarkDone**是一个`Markdown`文件编辑工具

但它并非是一个编辑器，而是一个构建器。**MarkDone**的目的是为了帮助需要在程序中输出`Markdown`，**MarkDone是一个Markdown构建器**。

## 特点

- 链式构建，所见即所得
- 独立组件，每个组件可以单独使用
- 自由模式，支持任意形式的样式内嵌

## 举例

```java

```

## 支持的元素

| 元素 | 对应组件 |
| :---: | :---: |
| 标题 | `InlineItem.heading(int, String)` |
| 粗体 | `InlineItem.bold(String)` |
| 斜体 | `InlineItem.italic(String)` |
| 删除线 | `InlineItem.delete(String)` |
| 行内代码 | `InlineItem.code(String)` |
| 链接 | `InlineItem.url(String)` |
| 资源链接 | `InlineItem.src(String, String [, String])` |
| 图片 | `InlineItem.img(String, String [, String])` |
| 代码块 | `CodeBlockItem()` |
| 有序列表 | `OrderedListBlockItem()` |
| 无序列表 | `DisorderedListBlockItem()` |
| 分割线 | `HorizontalRuleItem()` |
| 引用块 | `QuoteBlockItem()` |
| 表格 | `TableBlockItem()` |
| 任务列表 | `TodoBlockItem()` |
| 脚注 | `MarkDone.tag(String, String)` |
