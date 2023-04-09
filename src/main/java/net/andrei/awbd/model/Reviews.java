package net.andrei.awbd.model;


import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "review")
    private String review;

    @Column(name = "rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

}
