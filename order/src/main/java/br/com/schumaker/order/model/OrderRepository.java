package br.com.schumaker.order.model;

import br.com.schumaker.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Order o set o.status = :status where o = :order")
    void updateStatus(Status status, Order order);

    @Query(value = "select o from Order o LEFT JOIN FETCH o.items where o.id = :id")
    Order byIdWithItems(Long id);
}
