package com.dobby.consumer.service;

import com.dobby.consumer.bean.Depart;
import com.google.common.collect.Lists;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author liguoqing
 */
@Slf4j
@Component
public class DepartFallbackFactory implements FallbackFactory<DepartServiceClient> {

    @Override
    public DepartServiceClient create(Throwable throwable) {
        log.error("》》》》》》》DepartServiceClient 服务容错回退");
        return new DepartServiceClient() {
            @Override
            public boolean saveDepart(Depart depart) {
                log.info(">>>>>>>>>> 执行保存单个Depart的服务容错处理");
                return false;
            }

            @Override
            public boolean deleteDepartById(Integer id) {
                log.info(">>>>>>>>>> 执行删除单个Depart的服务容错处理");
                return false;
            }

            @Override
            public boolean updateDepart(Depart depart) {
                log.info(">>>>>>>>>> 执行更新单个Depart的服务容错处理");
                return false;
            }

            @Override
            public Depart getDepartById(Integer id) {
                log.info(">>>>>>>>>> 执行查询单个Depart的服务容错处理");
                Depart depart = new Depart();
                depart.setId(id);
                depart.setName("no this depart");
                depart.setDbase("NO THIS INFO");
                return depart;
            }

            @Override
            public List<Depart> listAllDeparts() {
                log.info(">>>>>>>>>> 执行查询所有Depart的服务容错处理");
                return Lists.newArrayList();
            }
        };
    }

}
