package africa.semicolon.lumexpress.data.repositories;

import africa.semicolon.lumexpress.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long aLong);
    List<Customer> findAll();
}
