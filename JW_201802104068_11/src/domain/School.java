//201802104068高睿超
package domain;

import java.io.Serializable;

public final class School implements Comparable<School>, Serializable {
    private Integer id;
    private String no;
    private String description;
    private String remarks;
    /*{
        this.id = IdService.getId();
    }*/
    public School(Integer id, String description, String no, String remarks){
        this(description, no, remarks);
        this.id = id;
    }
    public School(String description, String no, String remarks){
        super();
        this.description = description;
        this.no = no;
        this.remarks = remarks;
    }

    public Integer getId(){return id;}
    public void setId(Integer id){this.id = id;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}
    public String getNo(){return no;}
    public void setNo(String no){this.no = no;}
    public String getRemarks(){return remarks;}
    public void setRemarks(String remarks){this.remarks = remarks;}
    @Override
    public int compareTo(School o) {
        return 0;
    }
}
