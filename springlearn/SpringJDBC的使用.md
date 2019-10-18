## SpringJDBC

一般在DAO中使用，比如：
```
@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
```

也就是JDBC的模板方法