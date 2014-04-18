package org.fluxtream.connectors.fluxtream_capture;

import org.fluxtream.OutsideTimeBoundariesException;
import org.fluxtream.TimeInterval;
import org.fluxtream.connectors.bodymedia.BodymediaBurnFacet;
import org.fluxtream.connectors.fluxtream_capture.FluxtreamCaptureObservationFacet;
import org.fluxtream.connectors.vos.AbstractInstantFacetVO;
import org.fluxtream.domain.GuestSettings;

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
