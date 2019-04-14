package com.layman.tool.dao;

import com.layman.tool.entity._Class;
import org.springframework.data.repository.CrudRepository;

/**
 * @InterfaceName ClassRepository
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/12 21:21
 * @Version 3.0
 **/
public interface ClassRepository extends CrudRepository<_Class, String> {
}
