package com.fluxtream;

import java.util.Map;
import java.util.TimeZone;
import com.fluxtream.utils.TimespanSegment;
import org.joda.time.DateTimeZone;

/**
 * User: candide
 * Date: 02/10/13
 * Time: 18:21
 */
public class TimezoneAwareTimeInterval implements TimeInterval {

    long start, end;
    TimeUnit timeUnit;
    TimezoneMap timezoneMap;
    Map<String, TimeZone> consensusTimezones;

    public TimezoneAwareTimeInterval(long start, long end,
                                     TimeUnit timeUnit,
                                     Map<String, TimeZone> consensusTimezones,
                                     TimezoneMap timezoneMap) {
        this.start = start;
        this.end = end;
        this.timeUnit = timeUnit;
        this.timezoneMap = timezoneMap;
        this.consensusTimezones = consensusTimezones;
    }

    @Override
    public TimeZone getMainTimeZone() {
        return timezoneMap.getMainTimezone().toTimeZone();
    }

    @Override
    public long getStart() { return start; };

    @Override
    public long getEnd() { return end; }

    @Override
    public TimeUnit getTimeUnit() { return timeUnit; }

    @Override
    public TimeZone getTimeZone(final long time) throws OutsideTimeBoundariesException {
        final TimespanSegment<DateTimeZone> dateTimeZoneTimespanSegment = timezoneMap.queryPoint(time);
        return dateTimeZoneTimespanSegment.getValue().toTimeZone();
    }

    @Override
    public TimeZone getTimeZone(final String date) throws OutsideTimeBoundariesException {
        return consensusTimezones.get(date);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TimezoneAwareTimeInterval))
            return false;
        TimezoneAwareTimeInterval timeInterval = (TimezoneAwareTimeInterval) o;
        if (timeInterval.getTimeUnit()!=getTimeUnit()) return false;
        final boolean b = timeInterval.getStart() == getStart() && timeInterval.getEnd() == getEnd();
        if (!b) return false;
        final boolean b1 = timeInterval.timezoneMap.equals(timezoneMap);
        if (!b1) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 37 + getTimeUnit().ordinal();
        hash = hash * 37 + (int)(start ^ (start >>> 32));
        hash = hash * 37 + (int)(end ^ (end >>> 32));
        hash = hash * 37 + timezoneMap.hashCode();
        return hash;
    }

}
