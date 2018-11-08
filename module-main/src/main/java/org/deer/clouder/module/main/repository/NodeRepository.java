package org.deer.clouder.module.main.repository;

import org.deer.clouder.module.main.dto.Node;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "node")
public interface NodeRepository extends MongoRepository<Node, String> {

}
