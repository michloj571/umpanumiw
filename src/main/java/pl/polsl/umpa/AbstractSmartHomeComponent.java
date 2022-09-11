package pl.polsl.umpa;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractSmartHomeComponent {
    private Date recordDate;

    public AbstractSmartHomeComponent(Date recordDate){
        this.recordDate = recordDate;
    }
}
