package system.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "USERS")
@DynamicUpdate
@Data
public class Users extends Base {

    @Column(name = "FULL_NAME")
	private String fullName;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

}

