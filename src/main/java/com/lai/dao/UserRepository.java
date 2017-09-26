package com.lai.dao;

import com.lai.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lailai on 2017/9/25.
 * 增加缓存配置注解
 * @Cacheable: 函数的返回值将被加入缓存,同时在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问
 * @CacheConfig(cacheNames = "users")：配置了该数据访问对象中返回的内容将存储于名为users的缓存对象中
 */
@Transactional
@Service
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User,Long>{

    //定义了一个规则,#p0 或者 #a0 都是第一个参数
    @Cacheable(key = "#p0",condition = "#p0.length()<10")
    User findByName(String name);


//    @CachePut(key = "#p0",condition = "#p0.length()<10")
    @Query(value = "update User u set u.name=?1,u.age=?2 where u.id=?3",nativeQuery = true)
    @Modifying
    int updateUserNameAndAgeById(String name, int age, long id);

    @CachePut(key = "#p0.name",condition = "#p0.name.length()<10")
    User save(User user);
}
