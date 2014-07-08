package org.fluxtream.core.connectors.vos;

import org.fluxtream.core.OutsideTimeBoundariesException;
import org.fluxtream.core.TimeInterval;
import org.fluxtream.core.domain.AbstractLocalTimeFacet;
import org.fluxtream.core.domain.GuestSettings;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

/**
 *
 * @author Candide Kemmler (candide@fluxtream.com)
 */
public abstract class AbstractLocalTimeInstantFacetVO<T extends AbstractLocalTimeFacet> extends
                                             AbstractFacetVO<T> implements Comparable<AbstractInstantFacetVO<T>> {

    public boolean localTime;
    public String eventStart;

    @Override
    public void extractValues(T facet, TimeInterval timeInterval, GuestSettings settings) throws OutsideTimeBoundariesException {
        super.extractValues(facet, timeInterval, settings);
        this.date = facet.date;
        this.eventStart = ISODateTimeFormat.basicDateTime().withZone(DateTimeZone.forTimeZone(timeInterval.getTimeZone(facet.date))).print(facet.start);
        localTime = true;
    }

    @Override
    public int compareTo(final AbstractInstantFacetVO<T> other) {
        final DateTime thisStart = ISODateTimeFormat.basicDate().parseDateTime(this.eventStart);
        DateTime otherStart = ISODateTimeFormat.basicDate().parseDateTime(other.eventStart);
        return thisStart.isAfter(otherStart) ? 1 : -1;
    }

}