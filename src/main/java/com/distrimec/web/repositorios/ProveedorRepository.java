
package com.distrimec.web.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.distrimec.web.modelos.entidades.Proveedor;


@Repository

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    // Add any custom query methods here
}
