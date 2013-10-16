package com.fluxtream.utils;

import java.util.Collection;
import java.util.List;
import com.fluxtream.TimeInterval;
import com.fluxtream.connectors.Connector;
import com.fluxtream.connectors.ObjectType;
import com.fluxtream.connectors.vos.AbstractFacetVO;
import com.fluxtream.domain.AbstractFacet;
import com.fluxtream.domain.ApiKey;
import com.fluxtream.services.GuestService;
import com.fluxtream.utils.caching.CachedValueObjects;
import com.fluxtream.utils.caching.DatesCacheKey;
import com.fluxtream.utils.caching.TimeIntervalCacheKey;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.SearchAttribute;
import net.sf.ehcache.config.Searchable;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: candide
 * Date: 14/10/13
 * Time: 23:36
 */
@Component
public class CacheManagerProxy {

    CacheManager cacheManager = CacheManager.getInstance();

    @Autowired
    GuestService guestService;

    private Cache getCache() {
        Cache cache = cacheManager.getCache("facets");
        if (cache==null) {
            System.out.println("INITIALIZING FACETS CACHE!!!");
            CacheConfiguration cacheCfg = new CacheConfiguration("facets", 100000);
            cacheCfg.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU);
            cacheCfg.diskPersistent(false);
            cacheCfg.setEternal(true);
            Searchable searchable = new Searchable();
            cacheCfg.addSearchable(searchable);

            searchable.addSearchAttribute(new SearchAttribute().name("apiKeyId").expression("value.apiKeyId"));
            searchable.addSearchAttribute(new SearchAttribute().name("guestId").expression("value.guestId"));
            searchable.addSearchAttribute(new SearchAttribute().name("api").expression("value.api"));
            searchable.addSearchAttribute(new SearchAttribute().name("objectTypeValue").expression("value.objectTypeValue"));

            cache = new Cache(cacheCfg);
            cacheManager.addCache(cache);
        }
        return cache;
    }

    public Collection<AbstractFacetVO<AbstractFacet>> getFacets(long apiKeyId, ObjectType objectType, TimeInterval timeInterval) {
        Cache cache = getCache();
        if (cache==null) return null;
        final Element element = cache.get(new TimeIntervalCacheKey(apiKeyId, objectType, timeInterval));
        if (element!=null) {
            final CachedValueObjects objectValue = (CachedValueObjects)element.getObjectValue();
            if (objectValue.valueObjects !=null) {
                System.out.println("cache hit! " + apiKeyId + "/" + objectType);
                return objectValue.valueObjects;
            } else
                System.out.println("cache miss " + apiKeyId + "/" + objectType);
        }
        return null;
    }

    public Collection<AbstractFacetVO<AbstractFacet>> getFacets(long apiKeyId, ObjectType objectType, List<String> dates) {
        Cache cache = getCache();
        if (cache==null) return null;
        final Element element = cache.get(new DatesCacheKey(apiKeyId, objectType, dates));
        if (element!=null) {
            final CachedValueObjects objectValue = (CachedValueObjects)element.getObjectValue();
            if (objectValue.valueObjects != null) {
                System.out.println("cache hit! " + apiKeyId + "/" + objectType);
                return objectValue.valueObjects;
            }
            else {
                System.out.println("cache miss " + apiKeyId + "/" + objectType);
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

    public void invalidateFacets(long guestId) {
        Cache cache = getCache();
        if (cache==null) return;
        Attribute<Long> guestIdAtt = cache.getSearchAttribute("guestId");
        final Query query = cache.createQuery().includeKeys().addCriteria(guestIdAtt.eq(guestId));
        final Results cacheQueryResults = query.execute();
        final List<Result> all = cacheQueryResults.all();
        for (Result result : all)
            cache.remove(result.getKey());
    }

    public void invalidateFacets(long apiKeyId, ObjectType objectType) {
        Cache cache = getCache();
        if (cache==null) return;
        Attribute<Long> apiKeyIdAtt = cache.getSearchAttribute("apiKeyId");
        Attribute<Integer> objectTypeAtt = cache.getSearchAttribute("objectTypeValue");
        int objectTypeValue = objectType==null
                            ? Integer.MIN_VALUE
                            : objectType.value();
        final Query query = cache.createQuery().includeKeys().addCriteria(apiKeyIdAtt.eq(apiKeyId)).addCriteria(objectTypeAtt.eq(objectTypeValue));
        final Results cacheQueryResults = query.execute();
        final List<Result> all = cacheQueryResults.all();
        System.out.println("removing " + all.size() + " cached value object collections");
        for (Result result : all)
            cache.remove(result.getKey());
    }

    public void cacheFacets(final ApiKey apiKey, final ObjectType objectType, final Collection<AbstractFacetVO<AbstractFacet>> facets, final TimeInterval timeInterval) {
        Cache cache = getCache();
        final TimeIntervalCacheKey key = new TimeIntervalCacheKey(apiKey.getId(), objectType, timeInterval);
        if (cache.get(key)==null) {
            Element cacheElement = new Element(key, new CachedValueObjects(apiKey.getId(), apiKey.getGuestId(), apiKey.getConnector().value(), objectType,  facets));
            cache.put(cacheElement);
        }
    }

    public void cacheFacets(final ApiKey apiKey, final ObjectType objectType, final Collection<AbstractFacetVO<AbstractFacet>> facets, final List<String> dates) {
        Cache cache = getCache();
        final DatesCacheKey key = new DatesCacheKey(apiKey.getId(), objectType, dates);
        if (cache.get(key)==null) {
            Element cacheElement = new Element(key, new CachedValueObjects(apiKey.getId(), apiKey.getGuestId(), apiKey.getConnector().value(), objectType, facets));
            cache.put(cacheElement);
        }
    }

}
