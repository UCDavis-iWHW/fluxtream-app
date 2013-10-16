package com.fluxtream.utils.caching;

import com.fluxtream.connectors.ObjectType;

/**
 * User: candide
 * Date: 16/10/13
 * Time: 01:38
 */
public class AbstractCacheKey {
    public long apiKeyId;
    public long guestId;
    public int api;
    public int objectTypeValue;

    public AbstractCacheKey(final long apiKeyId, final long guestId, final int api, final ObjectType objectType) {
        this.apiKeyId = apiKeyId;
        this.guestId = guestId;
        this.api = api;
        this.objectTypeValue = objectType==null? Integer.MIN_VALUE : objectType.value();
    }
}
