package com.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.domain.User.class.getName());
            createCache(cm, com.domain.Authority.class.getName());
            createCache(cm, com.domain.User.class.getName() + ".authorities");
            createCache(cm, com.domain.Profile.class.getName());
            createCache(cm, com.domain.Tour.class.getName());
            createCache(cm, com.domain.Tourcategory.class.getName());
            createCache(cm, com.domain.Tourgroup.class.getName());
            createCache(cm, com.domain.Evenement.class.getName());
            createCache(cm, com.domain.Evenement.class.getName() + ".tours");
            createCache(cm, com.domain.Evenement.class.getName() + ".tourgroups");
            createCache(cm, com.domain.Evenement.class.getName() + ".tourcategories");
            createCache(cm, com.domain.Evenement.class.getName() + ".likes");
            createCache(cm, com.domain.Evenement.class.getName() + ".ratings");
            createCache(cm, com.domain.Evenement.class.getName() + ".reviews");
            createCache(cm, com.domain.Evenement.class.getName() + ".follows");
            createCache(cm, com.domain.Evenement.class.getName() + ".bookings");
            createCache(cm, com.domain.Evenement.class.getName() + ".paymentactions");
            createCache(cm, com.domain.Product.class.getName());
            createCache(cm, com.domain.Product.class.getName() + ".tours");
            createCache(cm, com.domain.Product.class.getName() + ".evenements");
            createCache(cm, com.domain.Product.class.getName() + ".tourgroups");
            createCache(cm, com.domain.Product.class.getName() + ".tourcategories");
            createCache(cm, com.domain.Product.class.getName() + ".likes");
            createCache(cm, com.domain.Product.class.getName() + ".follows");
            createCache(cm, com.domain.Product.class.getName() + ".ratings");
            createCache(cm, com.domain.Product.class.getName() + ".reviews");
            createCache(cm, com.domain.Product.class.getName() + ".paymentactions");
            createCache(cm, com.domain.Productdetails.class.getName());
            createCache(cm, com.domain.Category.class.getName());
            createCache(cm, com.domain.Category.class.getName() + ".products");
            createCache(cm, com.domain.Category.class.getName() + ".evenements");
            createCache(cm, com.domain.Category.class.getName() + ".tours");
            createCache(cm, com.domain.Category.class.getName() + ".likes");
            createCache(cm, com.domain.Category.class.getName() + ".follows");
            createCache(cm, com.domain.Category.class.getName() + ".ratings");
            createCache(cm, com.domain.Category.class.getName() + ".reviews");
            createCache(cm, com.domain.Category.class.getName() + ".categories");
            createCache(cm, com.domain.Category.class.getName() + ".tourgroups");
            createCache(cm, com.domain.Category.class.getName() + ".tourcategories");
            createCache(cm, com.domain.Category.class.getName() + ".bookings");
            createCache(cm, com.domain.Category.class.getName() + ".carts");
            createCache(cm, com.domain.Category.class.getName() + ".paymentcategories");
            createCache(cm, com.domain.Category.class.getName() + ".currencies");
            createCache(cm, com.domain.Category.class.getName() + ".shippings");
            createCache(cm, com.domain.Categorydetails.class.getName());
            createCache(cm, com.domain.Files.class.getName());
            createCache(cm, com.domain.Follows.class.getName());
            createCache(cm, com.domain.Likes.class.getName());
            createCache(cm, com.domain.Ratings.class.getName());
            createCache(cm, com.domain.Reviews.class.getName());
            createCache(cm, com.domain.Paymentmethod.class.getName());
            createCache(cm, com.domain.Paymentmethod.class.getName() + ".paymentactions");
            createCache(cm, com.domain.Paymentmethod.class.getName() + ".paymentcategories");
            createCache(cm, com.domain.Paymentcategory.class.getName());
            createCache(cm, com.domain.Paymentaction.class.getName());
            createCache(cm, com.domain.Cart.class.getName());
            createCache(cm, com.domain.Cart.class.getName() + ".cartproducts");
            createCache(cm, com.domain.Cartproducts.class.getName());
            createCache(cm, com.domain.Booking.class.getName());
            createCache(cm, com.domain.Booking.class.getName() + ".bookingproducts");
            createCache(cm, com.domain.Bookingdetails.class.getName());
            createCache(cm, com.domain.Bookingproducts.class.getName());
            createCache(cm, com.domain.Bookingview.class.getName());
            createCache(cm, com.domain.Location.class.getName());
            createCache(cm, com.domain.Shipping.class.getName());
            createCache(cm, com.domain.Notification.class.getName());
            createCache(cm, com.domain.Currency.class.getName());
            createCache(cm, com.domain.Cherche.class.getName());
            createCache(cm, com.domain.Actus.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
