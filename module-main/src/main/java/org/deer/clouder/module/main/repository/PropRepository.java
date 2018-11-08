package org.deer.clouder.module.main.repository;

import org.deer.clouder.module.main.dto.Prop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "prop")
public interface PropRepository extends MongoRepository<Prop, String> {

}
