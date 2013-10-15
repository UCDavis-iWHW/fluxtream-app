package com.fluxtream.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import com.fluxtream.TimeInterval;
import com.fluxtream.connectors.Connector;
import com.fluxtream.connectors.ObjectType;
import com.fluxtream.connectors.vos.AbstractFacetVO;
import com.fluxtream.domain.AbstractFacet;
import com.fluxtream.services.GuestService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: candide
 * Date: 14/10/13
 * Time: 23:36
 */
@Component
public class CacheManagerProxy {

    CacheManager cacheManager = CacheManager.create();

    @Autowired
    GuestService guestService;

    private String getKey(final long apiKeyId, final ObjectType objectType) {
        if (objectType==null)
            return new StringBuilder(String.valueOf(apiKeyId)).toString();
        else
            return new StringBuilder(String.valueOf(apiKeyId)).append("-").append(objectType.getName()).toString();
    }

    private Cache createCache() {
        Cache testCache = new Cache(
                new CacheConfiguration("facets", 100000)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(true)
                        .diskPersistent(false));
        cacheManager.addCache(testCache);
        return testCache;
    }

    public Collection<AbstractFacetVO<AbstractFacet>> getFacets(long apiKeyId, ObjectType objectType, TimeInterval timeInterval) {
        Cache cache = cacheManager.getCache("facets");
        if (cache==null) return null;
        final Element element = cache.get(getKey(apiKeyId, objectType));
        if (element!=null) {
            final HashMap<TimeInterval, Collection<AbstractFacetVO<AbstractFacet>>> objectValue = (HashMap<TimeInterval, Collection<AbstractFacetVO<AbstractFacet>>>)element.getObjectValue();
            final Collection<AbstractFacetVO<AbstractFacet>> cachedFacets = objectValue.get(timeInterval);
            if (cachedFacets !=null) {
                System.out.println("cache hit!");
                return cachedFacets;
            }
        }
        return null;
    }

    public Collection<AbstractFacetVO<AbstractFacet>> getFacets(long apiKeyId, ObjectType objectType, List<String> dates) {
        Cache cache = cacheManager.getCache("facets");
        if (cache==null) return null;
        final Element element = cache.get(getKey(apiKeyId, objectType));
        if (element!=null) {
            final HashMap<String, Collection<AbstractFacetVO<AbstractFacet>>> objectValue = (HashMap<String, Collection<AbstractFacetVO<AbstractFacet>>>)element.getObjectValue();
            final Collection<AbstractFacetVO<AbstractFacet>> cachedFacets = objectValue.get(StringUtils.join(dates, ","));
            if (cachedFacets !=null) {
                System.out.println("cache hit!");
                return cachedFacets;
            }
        }
        return null;
    }

    public void invalidateFacets(Connector connector, long apiKeyId, int objectTypes) {
        final List<ObjectType> types = ObjectType.getObjectTypes(connector, objectTypes);
        for (ObjectType type : types) {
            invalidateFacets(apiKeyId, type);
        }
    }

    public void invalidateFacets(long apiKeyId, ObjectType objectType) {
        Cache cache = cacheManager.getCache("facets");
        if (cache==null) return;
        cache.remove(getKey(apiKeyId, objectType));
    }

    public void cacheFacets(final Long apiKeyId, final ObjectType objectType, final Collection<AbstractFacetVO<AbstractFacet>> facets, final TimeInterval timeInterval) {
        Cache cache = cacheManager.getCache("facets");
        if (cache==null)
            cache = createCache();
        final String key = getKey(apiKeyId, objectType);
        if (cache.get(key)==null) {
            Element cacheElement = new Element(key, new HashMap<TimeInterval, Collection<AbstractFacetVO<AbstractFacet>>>());
            cache.put(cacheElement);
        }
        Element cacheElement = cache.get(key);
        HashMap<TimeInterval,Collection<AbstractFacetVO<AbstractFacet>>> values = (HashMap<TimeInterval, Collection<AbstractFacetVO<AbstractFacet>>>)cacheElement.getObjectValue();
        values.put(timeInterval, facets);
    }

    public void cacheFacets(final Long apiKeyId, final ObjectType objectType, final Collection<AbstractFacetVO<AbstractFacet>> facets, final List<String> dates) {
        Cache cache = cacheManager.getCache("facets");
        if (cache==null)
            cache = createCache();
        final String key = getKey(apiKeyId, objectType);
        if (cache.get(key)==null) {
            Element cacheElement = new Element(key, new HashMap<String, Collection<AbstractFacetVO<AbstractFacet>>>());
            cache.put(cacheElement);
        }
        Element cacheElement = cache.get(key);
        HashMap<String,Collection<AbstractFacetVO<AbstractFacet>>> values = (HashMap<String, Collection<AbstractFacetVO<AbstractFacet>>>)cacheElement.getObjectValue();
        values.put(StringUtils.join(dates,","), facets);
    }

}
