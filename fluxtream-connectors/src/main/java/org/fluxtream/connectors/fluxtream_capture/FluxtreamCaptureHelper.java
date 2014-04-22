package org.fluxtream.connectors.fluxtream_capture;

import org.fluxtream.core.utils.JPAUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chernushenko
 * Date: 07/04/14
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
@Component
@Transactional(readOnly = true)
public class FluxtreamCaptureHelper {

    @PersistenceContext
    EntityManager em;

    @Transactional(readOnly = true)
    public List<FluxtreamCaptureTopic> getTopics(Long guestId){
        List<FluxtreamCaptureTopic> topics = JPAUtils.find(em, FluxtreamCaptureTopic.class,"FluxtreamCaptureTopic.byGuestId",guestId);

        return topics;
    }
}
