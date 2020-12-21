package com.deng.blog.util;


import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * created by deng on 2020-12-21
 **/
public class MarkdownUtil {

    /*
    *
    *
    * */
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return  renderer.render(document);
    }

}
