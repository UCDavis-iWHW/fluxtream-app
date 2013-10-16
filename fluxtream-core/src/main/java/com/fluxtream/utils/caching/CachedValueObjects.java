package com.fluxtream.utils.caching;

import java.util.Collection;
import com.fluxtream.connectors.ObjectType;
import com.fluxtream.connectors.vos.AbstractFacetVO;
import com.fluxtream.domain.AbstractFacet;

/**
 * User: candide
 * Date: 15/10/13
 * Time: 23:27
 */

public class CachedValueObjects {

    public long apiKeyId;
    public int api;
    public long guestId;
    public int objectTypeValue;
    public Collection<AbstractFacetVO<AbstractFacet>> valueObjects;

    public CachedValueObjects(final long apiKeyId, final long guestId, final int api, final ObjectType objectType, final Collection<AbstractFacetVO<AbstractFacet>> valueObjects) {
        this.apiKeyId = apiKeyId;
        this.api = api;
        this.objectTypeValue = objectType==null
                             ? Integer.MIN_VALUE
                             : objectType.value();
        this.guestId = guestId;
        this.valueObjects = valueObjects;
    }

}
