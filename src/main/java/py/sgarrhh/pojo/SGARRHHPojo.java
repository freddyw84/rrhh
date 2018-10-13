package py.sgarrhh.pojo;

import java.io.Serializable;

/**
 *
 * @author opalencia
 */
public class SGARRHHPojo implements Serializable {  

    private String gender;

    private String lastName;
    
    public SGARRHHPojo() {
    }

    public SGARRHHPojo(String gender, String lastName) {
        this.gender = gender;
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
     
            
}
