package com.suadh.code401taskmaster.taskmasterapp.taskmaster;
import com.suadh.code401taskmaster.taskmasterapp.taskmaster.Taskmaster;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableScan
public interface TaskmasterRepository extends CrudRepository<Taskmaster, UUID>{
    Optional<Taskmaster> findById(UUID id);
    List<Taskmaster> findByAssignee(String  assignee);
}

