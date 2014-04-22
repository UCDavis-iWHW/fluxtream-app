package org.fluxtream.connectors.fluxtream_capture;

import org.fluxtream.core.OutsideTimeBoundariesException;
import org.fluxtream.core.TimeInterval;
import org.fluxtream.core.connectors.vos.AbstractInstantFacetVO;
import org.fluxtream.core.domain.GuestSettings;

/**
 * <p>
 * <code>FluxtreamCaptureObservationFacetVO</code> does something...
 * </p>
 *
 * @author Chris Bartley (bartley@cmu.edu)
 */
public class FluxtreamCaptureObservationFacetVO extends AbstractInstantFacetVO<FluxtreamCaptureObservationFacet> {

    @Override
    protected void fromFacet(final FluxtreamCaptureObservationFacet facet, final TimeInterval timeInterval, final GuestSettings settings) throws OutsideTimeBoundariesException {
    }
}
