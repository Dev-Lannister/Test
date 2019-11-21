package com.youdao.test.model.source.local;

import com.youdao.test.model.bean.BondBean;
import com.youdao.test.model.source.TestDataSource;

import java.util.ArrayList;
import java.util.List;

public class LocalTestDataSource implements TestDataSource {

    @Override
    public List<BondBean> getBondList() {
        List<BondBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            BondBean bondBean = new BondBean();
            bondBean.setName("name = " + i);
            list.add(bondBean);
        }
        return list;
    }

}
