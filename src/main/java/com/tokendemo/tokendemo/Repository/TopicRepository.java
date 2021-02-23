package com.tokendemo.tokendemo.Repository;

import com.tokendemo.tokendemo.Entities.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Integer> {
}
