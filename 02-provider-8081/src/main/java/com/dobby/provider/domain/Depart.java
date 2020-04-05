package com.dobby.provider.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * 默认情况下，Hibernate 对所有对象的查询采用了延迟加载策略，
 * 由 Hibernate 延迟初 始化器 hibernateLazyInitializer 完成。
 * 其首先会将 javasist 动态代理对象先传递给了 Jackson，
 * 在访问详情时再做查询。但 Jackson 在拿到代理对象后马上就要将其转换为 JSON 而不等待 真正的查询结果，
 * 但此时该对象仅仅是一个动态代理对象，没有真正的数据，所以会报错。
 */
@Data
@Entity
@Table(name = "t_departs")
// 指定忽略的属性
// hibernate 在获取对象的时候，使用了懒加载，转换成json的时候会出现错误（getById 出错，listAll没有出错）
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Depart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String dbase;

}
