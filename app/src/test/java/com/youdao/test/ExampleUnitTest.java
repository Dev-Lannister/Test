package com.youdao.test;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    public static void main(String args[]) {
        com.youdao.test.model.bean.TestProtobuf.Word.Builder builder = com.youdao.test.model.bean.TestProtobuf.Word.newBuilder();
        builder.setText("test");
        builder.setInterpretation("测试");
        builder.setSentence("test is a test");
        com.youdao.test.model.bean.TestProtobuf.Word word = builder.build();
        System.out.println(word.getSentence());
    }
}