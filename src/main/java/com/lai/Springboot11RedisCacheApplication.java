package com.lai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * EhCache是进程内的缓存框架，在集群模式下时，各应用服务器之间的缓存都是独立的，
 * 因此在不同服务器的进程间会存在缓存不一致的情况。即使EhCache提供了集群环境下的缓存同步策略，
 * 但是同步依然需要一定的时间，短暂的缓存不一致依然存在
 *
 * 在一些要求高一致性（任何数据变化都能及时的被查询到）的系统和应用中，就不能再使用EhCache来解决了，
 * 这个时候使用Redis集中式缓存是个不错的选择
 *
 * 问题：Redis的缓存独立存在于我们的Spring应用之外，我们对数据库中数据做了更新操作之后，
 * 没有通知Redis去更新相应的内容，因此我们取到了缓存中未修改的数据，导致了数据库与缓存中数据的不一致
 *
 * 解决问题：在使用缓存的时候，要注意缓存的生命周期，合理使用几个缓存注解来做好缓存的更新、删除
 * 在更新的时候，通过@CachePut让数据同步更新到缓存中
 */
@SpringBootApplication
@EnableCaching
public class Springboot11RedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot11RedisCacheApplication.class, args);
	}
}
