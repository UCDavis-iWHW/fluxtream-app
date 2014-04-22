package org.fluxtream.connectors.fluxtream_capture;

import org.fluxtream.core.domain.AbstractEntity;
import org.hibernate.annotations.Index;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Created with IntelliJ IDEA.
 * User: chernushenko
 * Date: 07/04/14
 * Time: 09:47
 * To change this template use File | Settings | File Templates.
 */
@Entity(name="FluxtreamCaptureTopic")
@NamedQueries({
        @NamedQuery(name="FluxtreamCaptureTopic.byGuestId",
                    query="SELECT topic FROM FluxtreamCaptureTopic topic WHERE topic.guestId=?")
})
public class FluxtreamCaptureTopic extends AbstractEntity {

    public FluxtreamCaptureTopic(){}

    public long creationTime;

    @Index(name="guestId")
    public long guestId;

    public String name;
    public Integer type;
    public Integer step;
    public Integer defaultValue;
}
