package org.fheragui.dao;

import org.fheragui.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaDAO extends JpaRepository<Persona, Integer> {

}
