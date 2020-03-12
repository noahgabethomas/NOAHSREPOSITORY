package com.revature.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.revature.beans.Status;

@Repository
public interface StatusDAO extends JpaRepository<Status, Integer>{}
