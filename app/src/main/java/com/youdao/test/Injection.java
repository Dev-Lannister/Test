package com.youdao.test;

import com.youdao.test.model.source.TestRepository;

public class Injection {

    public static TestRepository provideTestRepository() {
        return new TestRepository();
    }
}
