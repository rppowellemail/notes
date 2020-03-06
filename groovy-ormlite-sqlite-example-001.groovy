/*
References:

https://www.baeldung.com/ormlite
*/

@Grapes([
  @GrabConfig(systemClassLoader=true),
  @Grab(group='org.xerial', module='sqlite-jdbc', version='3.27.2.1'),
  @Grab(group='com.j256.ormlite', module='ormlite-jdbc', version='5.0')
])

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.table.DatabaseTable;
import java.sql.SQLException;

@DatabaseTable(tableName="members")
public class Member {
  @DatabaseField(generatedId=true)
  long memberId;

  @DatabaseField(canBeNull=false)
  String name;
}

@DatabaseTable(tableName="items")
public class Item {
  @DatabaseField(generatedId=true)
  long itemId;

  @DatabaseField(canBeNull=false)
  String title;
}

public class OrmliteSqlite {
    private static JdbcPooledConnectionSource connectionSource;

    private static Dao<Item, Long> itemDao;

    private static String datetimestamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss-SSS").withZone(ZoneId.systemDefault()).format(Instant.now())
    }

    public void setup() throws SQLException {
        //connectionSource = new JdbcPooledConnectionSource("jdbc:sqlite:mem:myDb");
        connectionSource = new JdbcPooledConnectionSource("jdbc:sqlite:ormlitesqlite-${datetimestamp()}.sqlite3");
        TableUtils.createTableIfNotExists(connectionSource, Item.class);

        itemDao = DaoManager.createDao(connectionSource, Item.class);
    }

    public void addItem() {
        Item item = new Item()
        item.title="Test01"
        itemDao.create(item)
    }
}

o = new OrmliteSqlite()
o.setup()
o.addItem()

println "Done"
