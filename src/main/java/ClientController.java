package kz.alnur.vehicle_rental_api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientDAO dao = new ClientDAO();

    @GetMapping
    public List<Client> getAll() throws SQLException {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable int id) throws SQLException {
        Client c = dao.findById(id);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }

    @PostMapping
    public Client create(@RequestBody Client client) throws SQLException {
        return dao.create(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateName(@PathVariable int id, @RequestBody Client data) throws SQLException {
        boolean ok = dao.updateName(id, data.getName());
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
