package org.fluxtream.connectors.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.fluxtream.connectors.location.LocationFacet;
import org.fluxtream.domain.DefaultPhotoFacetFinderStrategy;
import org.fluxtream.domain.PhotoFacetFinderStrategy;
import org.fluxtream.facets.extractors.AbstractFacetExtractor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ObjectTypeSpec {

	public String name();
	public int value();
	public Class<? extends AbstractFacetExtractor> extractor() default AbstractFacetExtractor.class;
	boolean parallel() default false;
	boolean isImageType() default false;
	public String prettyname();
    boolean isDateBased() default false;
    public Class<? extends PhotoFacetFinderStrategy> photoFacetFinderStrategy() default DefaultPhotoFacetFinderStrategy.class;
    public LocationFacet.Source locationFacetSource() default LocationFacet.Source.NONE;
    public boolean isMixedType() default false;
    // is this a user-facing facet or just data that's necessary to maintain state in the context of API synchronization
    public boolean clientFacet() default true;
    public String visibleClause() default "";

}