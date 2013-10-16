package com.fluxtream.utils.caching;

import java.util.List;
import com.fluxtream.connectors.ObjectType;

/**
 * User: candide
 * Date: 15/10/13
 * Time: 23:27
 */
public class DatesCacheKey extends AbstractCacheKey {

    public final List<String> dates;

    public DatesCacheKey(final long apiKeyId, final long guestId, final int api, final ObjectType objectType, List<String> dates) {
        super(apiKeyId, guestId, api, objectType);
        this.dates = dates;
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof DatesCacheKey))
            return false;
        DatesCacheKey other = (DatesCacheKey) o;
        boolean b = other.apiKeyId==apiKeyId;
        boolean b2 = objectTypeValue == other.objectTypeValue;
        boolean b3 = other.dates.size()==dates.size();
        if (!(b && b2 && b3)) return false;
        for (String date : dates)
            if (!other.dates.contains(date))
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 37 + (int)(apiKeyId ^ (apiKeyId >>> 32));
        hash = hash * 37 + objectTypeValue;
        for (String date : dates)
            hash = hash * 37 + date.hashCode();
        return hash;
    }
}
