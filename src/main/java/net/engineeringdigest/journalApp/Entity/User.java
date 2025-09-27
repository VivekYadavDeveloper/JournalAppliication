package net.engineeringdigest.journalApp.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
/*Equivalent to*/
@Data /*@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.*/
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)/*Its Make Username Unique For Every User's, So That It Will Help To Filter The Data*/
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();


}
