package org.fluxtream.services.impl;

import java.util.List;
import org.fluxtream.domain.UpdateWorkerTask;

/**
 * User: candide
 * Date: 14/08/14
 * Time: 15:54
 */
interface WorkerDispatchService {

    public List<UpdateWorkerTask> claimTasksForDispatch(int availableThreads, String serverUUID);

    public void unclaimTask(long taskId);

}
