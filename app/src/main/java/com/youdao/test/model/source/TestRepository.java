package com.youdao.test.model.source;

import com.youdao.test.model.bean.BondBean;
import com.youdao.test.model.source.local.LocalTestDataSource;

import java.util.List;

public class TestRepository implements TestDataSource {

    @Override
    public List<BondBean> getBondList() {
        return new LocalTestDataSource().getBondList();
    }

}
