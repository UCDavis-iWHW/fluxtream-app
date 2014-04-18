package org.fluxtream.connectors.fluxtream_capture;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.fluxtream.connectors.annotations.ObjectTypeSpec;
import org.fluxtream.connectors.mymee.MyMeePhotoFacetFinderStrategy;
import org.fluxtream.connectors.mymee.MymeeObservationFacetExtractor;
import org.fluxtream.domain.AbstractFacet;

/**
 *
 * @author Candide Kemmler (candide@fluxtream.com)
 */
@Entity(name="Facet_FluxtreamCaptureObservation")
@ObjectTypeSpec(name = "observation", value = 2, isImageType=true, parallel=false, prettyname = "Observation", photoFacetFinderStrategy=MyMeePhotoFacetFinderStrategy.class)
@NamedQueries({
})

// Most of the fields are optional;  non-optional fields are labeled as NotNull

public class FluxtreamCaptureObservationFacet extends AbstractFacet {

    public long creationTime;
    public Integer topicID;
    public Integer timezoneOffset;
    public Integer value;

    public FluxtreamCaptureObservationFacet() {
        super();
    }

    public FluxtreamCaptureObservationFacet(final long apiKeyId) {
        super(apiKeyId);
    }

    @Override
    protected void makeFullTextIndexable() {
    }

    // Returns the channel name used by datastore and in datastore-related URLs
    public String getChannelName() {
        // Datastore channel names have all characters that aren't alphanumeric or underscores replaced with underscores
        //return name.replaceAll("[^0-9a-zA-Z_]+", "_");
        return "";
    }
}
