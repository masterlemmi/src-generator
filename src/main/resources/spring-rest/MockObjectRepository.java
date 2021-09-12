package com.taeza.tools.common.util.srcmaker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MockObjectRepository extends JpaRepository<MockObject, Long> {

}
