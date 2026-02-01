package kz.alnur.vehicle_rental_api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleDAO dao = new VehicleDAO();

    @GetMapping
    public List<Vehicle> getAll() throws SQLException {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getById(@PathVariable int id) throws SQLException {
        Vehicle v = dao.findById(id);
        if (v == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(v);
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) throws SQLException {
        return dao.create(vehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePrice(@PathVariable int id, @RequestBody Vehicle data) throws SQLException {
        boolean ok = dao.updatePrice(id, data.getPricePerDay());
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws SQLException {
        boolean ok = dao.delete(id);
        if (!ok) return ResponseEntity.notFound().build();
        return ResponseEntity.ok("Deleted");
    }
}
