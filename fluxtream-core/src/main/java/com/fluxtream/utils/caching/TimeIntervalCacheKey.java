package com.fluxtream.utils.caching;

import com.fluxtream.TimeInterval;
import com.fluxtream.connectors.ObjectType;

/**
 * User: candide
 * Date: 15/10/13
 * Time: 23:27
 */
public class TimeIntervalCacheKey {

    private final long apiKeyId;
    private final ObjectType objectType;
    private final TimeInterval timeInterval;

    public TimeIntervalCacheKey(final long apiKeyId, final ObjectType objectType, TimeInterval timeInterval) {
        this.apiKeyId = apiKeyId;
        this.objectType = objectType;
        this.timeInterval = timeInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof TimeIntervalCacheKey))
            return false;
        TimeIntervalCacheKey other = (TimeIntervalCacheKey) o;
        boolean b = other.apiKeyId==apiKeyId;
        boolean b2 = other.objectType.value()==objectType.value();
        boolean b3 = other.timeInterval.equals(timeInterval);
        return b && b2 && b3;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 37 + (int)(apiKeyId ^ (apiKeyId >>> 32));
        hash = hash * 37 + objectType.value();
        hash = hash * 37 + timeInterval.hashCode();
        return hash;
    }
}
