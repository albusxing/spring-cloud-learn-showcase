package com.dobby.provider.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "t_departs")
// hibernate 在获取对象的时候，使用了懒加载，转换成json的时候会出现错误（getById 出错，listAll没有出错）
// 指定忽略的属性
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Depart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String dbase;

}
