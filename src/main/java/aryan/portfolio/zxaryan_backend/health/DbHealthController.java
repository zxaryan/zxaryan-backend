package aryan.portfolio.zxaryan_backend.health;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/public")
public class DbHealthController {

    private final JdbcTemplate jdbc;

    public DbHealthController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @GetMapping("/db-ping")
    public Map<String, Object> dbPing() {
        Integer one = jdbc.queryForObject("SELECT 1", Integer.class);
        return Map.of(
            "db", "connected",
            "result", one,
            "database", jdbc.queryForObject("SELECT current_database()", String.class),
            "user", jdbc.queryForObject("SELECT current_user", String.class),
            "version", jdbc.queryForObject("SELECT version()", String.class)
        );
    }
}